package com.mule.custom.logger;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import java.util.Map;

/**
 * This class represents an extension configuration, values set in this class
 * are commonly used across multiple operations since they represent something
 * core from the extension.
 */
@Operations(CustomLoggerOperations.class)
@ConnectionProviders(CustomLoggerConnectionProvider.class)
public class CustomLoggerConfiguration {

	@Parameter
	@DisplayName("Program Name")
	@Summary("Name of the Mule Application")
	@Optional(defaultValue = "#[app.name]")
	private String programName;

	@Parameter
	@DisplayName("Mule Version")
	@Summary("Version of the Mule Application")
	@Optional(defaultValue = "#[mule.version]")
	private String appVersion;


	@Parameter
	@DisplayName("Software")
	@Summary("Software name")
	@Optional(defaultValue = "Mulesoft")
	private String software;

	@Parameter
	@Optional
	@DisplayName("Additional Properties")
	private Map<String, String> additionalProperties;

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public String getProgramName() {
		return programName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public String getSoftware() {
		return software;
	}
	
}
