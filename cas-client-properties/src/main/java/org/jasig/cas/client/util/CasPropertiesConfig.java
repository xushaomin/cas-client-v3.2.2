package org.jasig.cas.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xusm(2012-11-22)
 *
 */
public class CasPropertiesConfig {
	
	protected static final Log log = LogFactory.getLog(CasPropertiesConfig.class);
	
	private static final String CAS_PROPERTIES = "cas.properties";
	
	public static final String CAS_SERVER = "casServer";
	
	public static final String SERVER_NAME = "serverName";
	
	public static final String FILTER_EXCLUSIONS = "filterExclusions";
	
	private static Properties prop = null;
	
	
	static {
		load(ResourceUtil.getAsStream(CAS_PROPERTIES));
	}
	
	public CasPropertiesConfig(String name) {
				
	}
	
	private static void load(InputStream is){
		prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {			
			log.error("error happen when loading properties file:", e);
		}
	}
	
	public static String getString(String key){
		return prop.getProperty(key);
	}
	
}
