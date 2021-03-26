package com.mule.custom.logger;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.values.OfValues;

import java.util.List;

public class CustomLogParameters {

	@Parameter
	@DisplayName("Level")
	@OfValues(StaticLogLevelValueProvider.class)
	@Optional(defaultValue = "INFO")
	private String level;

	@Parameter
	@DisplayName("Message")
	@Summary("Message to be logged")
	@Example("#[\"Converting payload from XML to JSON\"]")
	private String message;



	@Parameter
	@Optional
	@DisplayName("Payload")
	@Summary("Payload to be logged")
	@Example("#[payload]")
	private Object payload;

	@Parameter
	@Optional(defaultValue = "#[false]")
	@DisplayName("Enable pretty print")
	@Summary("If the payload needs to be printed in proper format")
	private boolean isPrettyPrint;

	@Parameter
	@DisplayName("Step")
	@Summary("Step")
	@Example("#[\"Log response\"]")
	@Optional
	private String step;

	@Parameter
	@Optional
	@DisplayName("Error Code")
	@Summary("Error Code to be logged")
	private String errorCode;


	@Parameter
	@Optional(defaultValue = "#[if ( error != null) ((error.errorType.namespace as String) ++ \":\" ++ (error.errorType.identifier as String) ++ \" | \" ++ (error.detailedDescription as String) ++ \" | \" ++ (error.description as String) ) else null]")
	@DisplayName("Exception Details")
	@Summary("Exception to be logged")
	@Example("#[if ( error != null) ((error.errorType.namespace as String) ++ \":\" ++ (error.errorType.identifier as String) ++ \" | \" ++ (error.detailedDescription as String) ++ \" | \" ++ (error.description as String) ) else null]")
	private String error;

	@Parameter
	@Optional
	@DisplayName("Correlation ID")
	@Summary("Correlation UUID")
	@Example("#[correlationId]")
	private String correlationId;

	@Parameter
	@Optional
	@DisplayName("Category")
	private String category;

	@Parameter
	@Optional
	@DisplayName("Additional Properties")
	private List<Properties> additionalProperties;

	public List<Properties> getAdditionalProperties() {
		return additionalProperties;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public String getMessage() {
		return message;
	}

	public Object getPayload() {
		return payload;
	}

	public String getError() {
		return error;
	}

	public String getLevel() {
		return level.toString();
	}

	public String getCategory() {
		return category;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getStep() {
		return step;
	}

	public boolean getPrettyPrint() {
		return isPrettyPrint;
	}

}
