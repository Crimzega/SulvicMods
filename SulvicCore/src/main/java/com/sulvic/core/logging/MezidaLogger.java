package com.sulvic.core.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

public class MezidaLogger{
	
	private final Logger logger;
	
	public MezidaLogger(Object logObj){ logger = LogManager.getLogger(logObj.toString()); }
	
	public MezidaLogger(Class<?> logClass){ logger = LogManager.getLogger(logClass); }
	
	public void catching(Level level, Throwable thrown){ logger.catching(level, thrown); }
	
	public void catching(Throwable thrown){ logger.catching(thrown); }
	
	public void debug(Marker marker, String message){ logger.debug(marker, message); }
	
	public void debug(String message){ logger.debug(message); }
	
	public void debug(String format, Object... args){ logger.debug(format, args); }
	
	public void error(Marker marker, String message){ logger.error(marker, message); }
	
	public void error(Object contents){ logger.error(contents); }
	
	public void error(String message){ logger.error(message); }
	
	public void error(String format, Object... args){ logger.error(format, args); }
	
	public void fatal(Marker marker, String message){ logger.fatal(marker, message); }
	
	public void fatal(Object contents){ logger.fatal(contents); }
	
	public void fatal(String message){ logger.fatal(message); }
	
	public void fatal(String format, Object... args){ logger.fatal(format, args); }
	
	public void format(Level level, String format, Object... args){ logger.printf(level, format, args); }
	
	public void format(Level level, Marker marker, String format, Object... args){ logger.printf(level, marker, format, args); }
	
	public void info(Marker marker, String message){ logger.info(marker, message); }
	
	public void info(Object contents){ logger.info(contents); }
	
	public void info(String message){ logger.info(message); }
	
	public void info(String format, Object... args){ logger.info(format, args); }
	
	public void log(Level level, Marker marker, String message){ logger.log(level, marker, message); }
	
	public void log(Level level, Object contents){ logger.log(level, contents); }
	
	public void log(Level level, String message){ logger.log(level, message); }
	
	public void log(Level level, String format, Object... args){ logger.log(level, format, args); }
	
	public void trace(Marker marker, String message){ logger.trace(marker, message); }
	
	public void trace(Object contents){ logger.trace(contents); }
	
	public void trace(String message){ logger.trace(message); }
	
	public void trace(String format, Object... args){ logger.trace(format, args); }
	
	public void warn(Marker marker, String message){ logger.warn(marker, message); }
	
	public void warn(Object contents){ logger.warn(contents); }
	
	public void warn(String message){ logger.warn(message); }
	
	public void warn(String format, Object... args){ logger.warn(format, args); }
	
}
