package com.jianggua.signpdf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jianggua.exception.EnvironmentException;

/**
 * @author Ray Wei
 * @date 2018-1-19
 */
public class Environment {

	static Logger logger = LogManager.getLogger(Environment.class);
	
	private static boolean isContinue = true;
	
	private Environment(){}
	
	public static void checkEnvironment() throws EnvironmentException{
		
	}
	
	public static final String userDir = System.getProperty("user.dir");
	
	public static final String tempDir = System.getProperty("java.io.tmpdir");

	public static boolean isContinue() {
		return isContinue;
	}
	
	
	
}
