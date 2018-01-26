package com.jianggua.signpdf;

import com.jianggua.exception.EnvironmentException;

/**
 * @author Ray Wei
 * @date 2018-1-19
 */
public class Environment {

	private Environment(){}
	
	public static void checkEnvironment() throws EnvironmentException{
		
	}
	
	public static final String userDir = System.getProperty("user.dir");
	
	public static final String tempDir = System.getProperty("java.io.tmpdir");
	
}
