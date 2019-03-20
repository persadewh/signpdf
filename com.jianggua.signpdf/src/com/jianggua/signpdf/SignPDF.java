package com.jianggua.signpdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jianggua.exception.EnvironmentException;
import com.jianggua.exception.ParameterException;
import com.jianggua.util.FileUtil;

/**
 * @author Ray Wei
 * @date 2018-1-19
 */
public class SignPDF {

	static final Logger logger = LogManager.getLogger(SignPDF.class);
	
	/**
	 * 1.Init and check the command parameters
	 * 2.Check the environment such as font,res,and so on
	 * 3.Read the configuration file
	 * 4.Read data file
	 * 4.Do sign
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Parameter.initParameters(args);
			Parameter.checkParameters();
			if(!Parameter.isContinue())
				System.exit(0);
			
			Environment.checkEnvironments();
			if(!Environment.isContinue())
				System.exit(0);
			
			Configuration config = Configuration.getInstance();
			
			if(null == Parameter.getConfig()){
				logger.info("Read default configuration");
				config.readConfigFile();
			}
			else{
				logger.info("Read custom configuration");
				config.readConfigFile(Parameter.getConfig());
			}
			
			if(null != config.getConfigFile()){
				DataBean.readData(Parameter.getData());
				if(SignBean.dataMap.size() > 0)
					SignBean.packageData();
				SignBean.doSign();
			}
		} catch (ParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
			deleteFile(Parameter.getTarget());
		} catch (EnvironmentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
			deleteFile(Parameter.getTarget());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
			deleteFile(Parameter.getTarget());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
			deleteFile(Parameter.getTarget());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			deleteFile(Parameter.getTarget());
		} catch (com.lowagie.text.DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			deleteFile(Parameter.getTarget());
		}
		finally{
			System.exit(0);
		}
	}

	private static void deleteFile(String path){
		File file = new File(path);
		if(file.exists()){
			logger.info("Removing " + path);
			if(file.getAbsoluteFile().delete()){
				logger.info("Remove success");
			}else{
				logger.info("Remove failure ");
			}
		}
	}
	
}
