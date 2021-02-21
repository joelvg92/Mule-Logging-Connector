package com.mule.custom.logger;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mule.runtime.api.metadata.DataType;
import org.mule.runtime.api.transformation.TransformationService;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Level;

import java.util.LinkedHashMap;
import java.util.Map;

import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import static org.mule.runtime.api.metadata.DataType.TEXT_STRING;

import javax.inject.Inject;

/**
 * This class is a container for custom logging operations, every public method
 * in this class will be taken as an custom logging extension operation.
 */
public class CustomLoggerOperations {

	@Inject
	private TransformationService transformationService;

	/**
	 * Example of an operation that uses the configuration and a connection instance
	 * to perform some action.
	 */
	@MediaType(value = ANY, strict = false)
	@DisplayName("GAF Custom Logger")
	public void customFileLogger(@Config CustomLoggerConfiguration configuration,
			@Connection CustomLoggerConnection connection,
			@ParameterGroup(name = "Log Details") CustomLogParameters logParameters,			
			CompletionCallback<Void, Void> callback) {

	    try
	    {

		Logger logger = connection.getLOGGER();


		Map<String, Object> logMsg = new LinkedHashMap<String, Object>();
		//App context
		CustomLoggerUtils.validateKey(logMsg,configuration.getProgramName(),"program");
		CustomLoggerUtils.validateKey(logMsg,configuration.getSoftware(),"software");
		CustomLoggerUtils.validateKey(logMsg,configuration.getAppVersion(),"software_version");
		if(configuration.getAdditionalProperties()!=null && !configuration.getAdditionalProperties().isEmpty()){
			CustomLoggerUtils.validateMap(configuration.getAdditionalProperties(),logMsg);
		}


		//Message context
		CustomLoggerUtils.validateKey(logMsg,logParameters.getLevel(),"level");
		CustomLoggerUtils.validateKey(logMsg,logParameters.getCorrelationId(),"correlationId");
		CustomLoggerUtils.validateKey(logMsg,logParameters.getMessage(),"message");
		try {
			if (logParameters.getPayload() != null) {
				String stringifyPayload = (String) transformationService.transform(logParameters.getPayload(), DataType.fromType(logParameters.getPayload().getClass()), TEXT_STRING);
				logMsg.put("payload", stringifyPayload.replaceAll(" ", ""));
			}
		}catch (Exception e){
			logMsg.put("payload","Fail to parse the payload");
		}
		CustomLoggerUtils.validateKey(logMsg,logParameters.getStep(),"step");
		CustomLoggerUtils.validateKey(logMsg,logParameters.getError(),"error_description");
		CustomLoggerUtils.validateKey(logMsg,logParameters.getErrorCode(),"error_code");
		CustomLoggerUtils.validateKey(logMsg,logParameters.getCategory(),"category");
		if(logParameters.getAdditionalProperties()!=null && !logParameters.getAdditionalProperties().isEmpty()){
			CustomLoggerUtils.validateMap(logParameters.getAdditionalProperties(),logMsg);
		}
		
		//ObjectMessage obj = new ObjectMessage(logMsg);
		String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(logMsg);
		logger.log(getLogLevel(logParameters.getLevel()), json);
		callback.success(Result.<Void,Void>builder().build());
		
	    }
	    catch(Exception e)
	    {
	    	callback.error(e);
	    }
	}

	private static Level getLogLevel(String level) {
		Level returnLevel;

		switch (level) {
		case "INFO":
			returnLevel = Level.INFO;
			break;
		case "DEBUG":
			returnLevel = Level.DEBUG;
			break;
		case "TRACE":
			returnLevel = Level.TRACE;
			break;
		case "WARN":
			returnLevel = Level.WARN;
			break;
		case "ERROR":
			returnLevel = Level.ERROR;
			break;
		case "FATAL":
			returnLevel = Level.FATAL;
			break;
		default:
			returnLevel = Level.INFO;
		}
		return returnLevel;

	}

}
