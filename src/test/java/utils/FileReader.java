package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileReader {
	
	public static Properties props;
	public static Properties data;
	
	public static void readConfig() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\config.properties");
		props = new Properties();
		props.load(fis);
	}
	
	public static void readData() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\data.properties");
		data = new Properties();
		data.load(fis);
	}
}
