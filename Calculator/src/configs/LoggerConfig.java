package configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class LoggerConfig {
	public static String LOG_PROPERTIES_FILE = "./log4j.properties";
	
	private static Properties logProperty = new Properties();
	private static String logFile;

	@SuppressWarnings("static-access")
	public LoggerConfig(String logFile) {
		this.logFile = logFile;
	}
	
	public void init() {
		try {
			logProperty.load(new FileInputStream(logFile));
			PropertyConfigurator.configure(logProperty);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
