package compilador.properties;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class CompilerProperties {
	private static final String APPLICATION_PROPERTIES = "application.properties";
	private Properties prop;
	public CompilerProperties() {
		try {
			this.prop = loadPropFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Properties loadPropFile() throws Exception {
		Properties prop = new Properties();
		String propFileName = APPLICATION_PROPERTIES;

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

		return prop;
	}
	
	public Properties getProp() {
		return this.prop;
	}
}
