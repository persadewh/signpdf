package com.jianggua.signpdf;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
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
	
	private static boolean isContinue = true;
	
	private static Options options = null;
	private static CommandLineParser parser = null;
	private static CommandLine cmd = null;
	
	private Parameter(){}
	
	
	/**
	 * Init Parameters:
	 * 1.-s The path of source pdf file for sign
	 * 2.-t The path of target pdf file with signed info
	 * 3.-c The path of customized configuration file(option)
	 * 4.-d The path of signed info file(option)
	 * 5.-h Show help info to user
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void initParameters(String[] args) throws ParseException{
		
		logger.info("Init Parameters Begin");
		
		options = new Options();
		
		options.addOption(Constants.HELP, Constants.LONGHELP, false, Constants.HELPDESC);
		
		Option sourceOpt = Option.builder(Constants.SOURCE)
				.longOpt(Constants.LONGSOURCE)
				.argName("file")
				.desc(Constants.SOURCEDESC)
				.required()
				.hasArg()
				.numberOfArgs(1)
				.build();
		
		Option targetOpt = Option.builder(Constants.TARGET)
				.longOpt(Constants.LONGTARGET)
				.argName("file")
				.desc(Constants.TARGETDESC)
				.required()
				.hasArg()
				.numberOfArgs(1)
				.build();
		
		Option configOpt = Option.builder(Constants.CONFIG)
				.longOpt(Constants.lONGCONFIG)
				.argName("file")
				.desc(Constants.CONFIGDESC)
				.hasArg()
				.numberOfArgs(1)
				.build();
		
		Option dataOpt = Option.builder(Constants.DATA)
				.longOpt(Constants.LONGDATA)
				.argName("file")
				.desc(Constants.DATADESC)
				.hasArg()
				.numberOfArgs(1)
				.build();
		
		
		options.addOption(sourceOpt);
		options.addOption(targetOpt);
		options.addOption(configOpt);
		options.addOption(dataOpt);
		
		parser = new DefaultParser();
		cmd = parser.parse(options, args);
		
		logger.info("Init Parameters End");
	}
	
	/**

	 * @param args
	 * @throws ParameterException
	 * @throws IOException 
	 * @return isOK - if isOK is false,the program will stop
	 */
	public static void checkParameters() throws ParameterException, IOException{
		
		logger.info("Check parameters Begin");
		
		if(cmd.hasOption(Constants.HELP)){
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("signpdf", options, true);
		}
		
		
//		if(null == args || args.length == 0)
//		{
//			isContinue = false;
//			throw new ParameterException("Parameter Error");
//		}
//		else{
//			for(String arg : args){
//				if(arg.toLowerCase().startsWith(Constants.SOURCE))
//					source = getParam(arg);
//				else if(arg.toLowerCase().startsWith(Constants.TARGET))
//					target = getParam(arg);
//				else if(arg.toLowerCase().startsWith(Constants.CONFIG))
//					config = getParam(arg);
//				else if(arg.toLowerCase().startsWith(Constants.DATA))
//					data = getParam(arg);
//				else if(arg.toLowerCase().startsWith(Constants.HELP))
//					help = true;
//			}
//			
//			if(help)
//			{
//				logger.info("Show help");
//				isContinue = false;
//				showHelp();
//			}
//			
//			if(!FileUtil.isFile(source)){
//				isContinue = false;
//				throw new ParameterException("The Parameter " + Constants.SOURCE + " is error");
//			}
//			else{
//				PDFTool.showPDFSize(source);
//			}
//			
//			if(null == target || !target.toLowerCase().endsWith(".pdf")){
//				isContinue = false;
//				throw new ParameterException("The Parameter " + Constants.TARGET + " is error");
//			}
//			
//			if(null != config){
//				if(!FileUtil.isFile(config)){
//					isContinue = false;
//					throw new ParameterException("The Parameter " + Constants.CONFIG + " is error");
//				}
//			}
//			
//			if(null != data){
//				if(!FileUtil.isFile(data)){
//					isContinue = false;
//					throw new ParameterException("The Parameter " + Constants.DATA + " is error");
//				}
//			}
//		}
		
		logger.info("Check parameters End");
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
	
	public static boolean isContinue() {
		return isContinue;
	}
}
