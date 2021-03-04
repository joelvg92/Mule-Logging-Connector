package com.mule.custom.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public final class CustomLoggerConnection {

	private transient Logger logger;

	public CustomLoggerConnection() { }

	public void invalidate() { }

	public Logger getLogger(String category) {
		if (category != null) {
			logger = (Logger) LogManager.getLogger(category);
		} else {
			logger = (Logger) LogManager.getLogger("com.custom.logger");
		}
		return logger;
	}
}
