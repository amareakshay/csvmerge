package com.csvmerge;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@Slf4j
public class MainApp {
	private static final Logger mylog = LoggerFactory.getLogger(MainApp.class);
	
	private String inputFileName;
	private String outputFileName;
	private String errorFileName;
	
	public static void main(final String[] args) {
		MainApp myapp = new MainApp();
		myapp.getProperties();
		myapp.getLoggerProperties();
		mylog.info("App Successfully ended");
	}
	
	private void getProperties() {
		Properties properties = new Properties();
		InputStream inputProp = null;
		try {
		  inputProp = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
		  properties.load(inputProp);
		  inputFileName = properties.getProperty("inputFile");
		  outputFileName = properties.getProperty("outputFile");
		  errorFileName = properties.getProperty("errorFile");
		} catch (IOException ex) {
            ex.printStackTrace();
            cliTerminate("Error reading application properties");
        }
		
		
	}
	
	private void getLoggerProperties() {
		
		Properties preferences = new Properties();
		InputStream logProp = null;
		try {
		    logProp = ClassLoader.getSystemClassLoader().getResourceAsStream("log4j.properties");
		    preferences.load(logProp);
//		    LogManager.getLogManager().readConfiguration(logProp);
		    PropertyConfigurator.configure(logProp);
		    System.out.println("Here####");
		} catch (IOException ex)
		{
		    System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured (console output only)");
		}
		mylog.info("starting myApp");
}
	
	
	private void cliTerminate(final String message) {
        mylog.error(message);
//        System.exit(1);
    }

}
