package com.jianggua.signpdf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
	 * @param args
	 * 
	 * 1.Check the parameter from command
	 * 2.Check the local environment such as font,res,and so on
	 * 3.Read the configuration file for sign
	 * 4.Read data file
	 * 4.Do sign
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			boolean isOK = Parameter.checkParameters(args);
			if(!isOK)
				System.exit(0);
			
			Environment.checkEnvironment();
			
			Configuration config = Configuration.getInstance();
			try {
				if(null == Parameter.getConfig()){
					config.readConfigFile();
				}
				else
					config.readConfigFile(Parameter.getConfig());
				
				if(null != config.getConfigFile()){
					readData(Parameter.getData());
					if(SignBean.dataMap.size() > 0)
						SignBean.packageData();
					SignBean.doSign();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
			} catch (com.lowagie.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		} catch (ParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
		} catch (EnvironmentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
		}
		finally{
			System.exit(0);
		}
	}
	
	public static void readData(String dataFilePath) throws FileNotFoundException, DocumentException, UnsupportedEncodingException{
		logger.info("Read data");
		if(!FileUtil.isFile(dataFilePath))
			logger.info("Have no data file");
		else{
			StringBuilder sb = new StringBuilder();
			String line = "";
			BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(dataFilePath), "UTF-8"));
			try {
				while((line = br.readLine()) != null)
				{
					sb.append(line);
					logger.info(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			StringReader stringReader = new StringReader(sb.toString());
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(stringReader);
			if(null != document)
			{
				Element rootElement = document.getRootElement();
				if(null != rootElement)
				{
					logger.info(rootElement.getName());
					List datas = rootElement.elements("data");
					if(null != datas && datas.size() > 0){
						for(int i = 0 ; i < datas.size() ; i++){
							Element data = (Element)datas.get(i);
							logger.info("name : " + data.attributeValue("name") + "\t value : " + data.attributeValue("value"));
							SignBean.dataMap.put(data.attributeValue("name"), data.attributeValue("value"));
						}
					}
				}
			}
			stringReader.close();
			
		}
	}

}
