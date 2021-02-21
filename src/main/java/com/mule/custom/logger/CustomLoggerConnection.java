package com.mule.custom.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public final class CustomLoggerConnection {

	private final Logger LOGGER = (Logger) LogManager.getLogger();

	public Logger getLOGGER() {
		return LOGGER;
	}

	public CustomLoggerConnection() { }

	public void invalidate() { }
}
