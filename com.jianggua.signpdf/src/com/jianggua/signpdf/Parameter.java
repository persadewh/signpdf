package com.jianggua.signpdf;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jianggua.exception.ParameterException;
import com.jianggua.util.FileUtil;

/**
 * @author Ray Wei
 * @date 2018-1-19
 */
public class Parameter {

	static final Logger logger = LogManager.getLogger(Parameter.class);
	
	private static String source = null;
	private static String target = null;
	private static String config = null;
	private static String data = null;
	private static boolean help = false;
	
	private Parameter(){}
	
	/**
	 * 1.-source The path of source pdf file for sign
	 * 2.-target The path of target pdf file with signed info
	 * 3.-config The path of customized config file(option)
	 * 4.-data The path of signed info file(option)
	 * 5.-help Show help info to user
	 * @param args
	 * @throws ParameterException
	 * @throws IOException 
	 * @return isOK - if isOK is false,the program will stop
	 */
	public static boolean checkParameters(String[] args) throws ParameterException, IOException{
		boolean isOK = true;
		
		logger.info("Checking parameters...");
		
		if(null == args || args.length == 0)
		{
			isOK = false;
			throw new ParameterException("Parameter Error");
		}
		else{
			for(String arg : args){
				if(arg.toLowerCase().startsWith(Constants.SOURCE))
					source = getParam(arg);
				else if(arg.toLowerCase().startsWith(Constants.TARGET))
					target = getParam(arg);
				else if(arg.toLowerCase().startsWith(Constants.CONFIGURATION))
					config = getParam(arg);
				else if(arg.toLowerCase().startsWith(Constants.DATA))
					data = getParam(arg);
				else if(arg.toLowerCase().startsWith(Constants.HELP))
					help = true;
			}
			
			if(help)
			{
				logger.info("Show help");
				isOK = false;
				showHelp();
				return isOK;
			}
			
			if(!FileUtil.isFile(source)){
				isOK = false;
				throw new ParameterException("The Parameter " + Constants.SOURCE + " is error");
			}
			else{
				PDFTool.showPDFSize(source);
			}
			
			if(null == target || !target.toLowerCase().endsWith(".pdf")){
				isOK = false;
				throw new ParameterException("The Parameter " + Constants.TARGET + " is error");
			}
			
			if(null != config){
				if(!FileUtil.isFile(config)){
					isOK = false;
					throw new ParameterException("The Parameter " + Constants.CONFIGURATION + " is error");
				}
			}
			
			if(null != data){
				if(!FileUtil.isFile(data)){
					isOK = false;
					throw new ParameterException("The Parameter " + Constants.DATA + " is error");
				}
			}
		}
		
		return isOK;
	}
	
	
	public static void showHelp(){
		System.out.println("***********************************************************");
		System.out.println("Sign PDF usage:");
		System.out.println("\t" + Constants.HELP + " Show help info to user");
		System.out.println("\t" + Constants.SOURCE + " The path of source pdf file for sign");
		System.out.println("\t" + Constants.TARGET + " The path of target pdf file with signed info");
		System.out.println("\t" + Constants.CONFIGURATION + " The path of customized config file(option)");
		System.out.println("\t" + Constants.DATA + " The path of signed info file(option)");
		System.out.println("***********************************************************");
	}
	
	
	
	/**
	 * -f=xxx or -f="xxx"
	 * return xxx or ""
	 * Get param value from command line.
	 * @param param
	 * @return
	 */
	public static String getParam(String param)
	{
		String[] temp = param.trim().split("=");
		if(temp!=null && temp.length==2)
		{
			if(temp[1].contains("\""))
				return temp[1].replace("\"", "").trim();
			else
				return temp[1].trim();
		}
		return "";
	}

	public static String getSource() {
		return source;
	}

	public static void setSource(String source) {
		Parameter.source = source;
	}

	public static String getTarget() {
		return target;
	}

	public static void setTarget(String target) {
		Parameter.target = target;
	}

	public static String getConfig() {
		return config;
	}

	public static void setConfig(String config) {
		Parameter.config = config;
	}

	public static String getData() {
		return data;
	}

	public static void setData(String data) {
		Parameter.data = data;
	}
}
