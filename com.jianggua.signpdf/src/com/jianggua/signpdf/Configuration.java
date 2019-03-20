package com.jianggua.signpdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jianggua.test.TestXML;

/**
 * @author Ray Wei
 * @date 2018-1-19
 */
public class Configuration {

	static final Logger logger = LogManager.getLogger(Configuration.class);
	
	//private final static String defaultConfigFilePath = Configuration.class.getClass().getResource("/config.xml").getPath();
	private static String defaultConfigFilePath = Environment.tempDir + File.separatorChar + "config.xml";
	
	private final static InputStream defaultConfigInputStream = Configuration.class.getClass().getResourceAsStream("/config.xml");
	
	private static File configFile = null;
	
	private static volatile Configuration config = null;
	
	private Configuration(){}
	
	public static synchronized Configuration getInstance(){
		synchronized (Configuration.class) {
			if(null == config)
			{
				logger.info("The Configuration is null,so init it");
				config = new Configuration();
			}
		}
		if(null == config)
			logger.error("The Configuration is NULL");
		
		return config;
	}
	
	public void readConfigFile() throws DocumentException, IOException{
		
		File file = new File(defaultConfigFilePath);
		FileOutputStream fos = new FileOutputStream(file);
		byte[] b = new byte[2048];
		int len = 0;
		while( (len = defaultConfigInputStream.read(b)) != -1){
			fos.write(b,0,len);
			fos.flush();
		}
		fos.close();
		readConfigFile(defaultConfigFilePath);
	}
	
	public void readConfigFile(File xmlFile) throws DocumentException, IOException{
		xmlFile = checkConfigFile(xmlFile);
		if(null != xmlFile)
		{
			if(xmlFile.exists())
			{
				configFile = xmlFile;
				logger.info(xmlFile.getAbsolutePath());
				
				StringBuilder sb = new StringBuilder();
				String line = "";
				
				try {
					BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile), "UTF-8"));
					while((line = br.readLine()) != null)
					{
						sb.append(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
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
						
						List pages = rootElement.elements("page");
						if(null != pages && pages.size() > 0){
							for(int i = 0 ; i < pages.size() ; i++){
								Element page = (Element)pages.get(i);
								PageBean onePage = new PageBean();
								onePage.setWidth(page.attributeValue("width"));
								onePage.setHeight(page.attributeValue("height"));
								//get font and check it
								onePage.setFont(readFontFile(page.attributeValue("font")));
								
								onePage.setFontSize(page.attributeValue("fontsize"));
								onePage.setEncoding(page.attributeValue("encoding"));
								
								if(null != page.attributeValue("deviation")){
									if(page.attributeValue("deviation").equals("")){
										//pass;
									}else{
										onePage.setDeviation(page.attributeValue("deviation"));
									}	
								}
								
								List texts = page.elements("text");
								if(null != texts && texts.size() > 0)
								{
									for(int j = 0 ; j < texts.size() ; j++){
										Element text = (Element)texts.get(j);
										TextBean textBean = new TextBean();
										textBean.setName(text.attributeValue("name"));
										textBean.setValue(text.attributeValue("value") == null ? "" : text.attributeValue("value"));
										textBean.setX(text.attributeValue("x"));
										textBean.setY(text.attributeValue("y"));
										onePage.getTextBeanList().add(textBean);
									}
								}
								List signets = page.elements("signet");
								if(null != signets && signets.size() > 0){
									for(int j = 0 ; j < signets.size() ; j++){
										Element signet = (Element)signets.get(j);
										SignetBean signetBean = new SignetBean();
										signetBean.setSrc(signet.attributeValue("src"));
										signetBean.setX(signet.attributeValue("x"));
										signetBean.setY(signet.attributeValue("y"));
										signetBean.setWidth(signet.attributeValue("width"));
										signetBean.setHeight(signet.attributeValue("height"));
										onePage.getSignetBeanList().add(signetBean);
									}
								}

								SignBean.pageBeanList.add(onePage);
							}
						}
					}
				}
				stringReader.close();
			}
			else
				throw new FileNotFoundException();
		}
		else
			throw new FileNotFoundException();
	}
	
	public void readConfigFile(String xmlFilePath) throws DocumentException, IOException{
		xmlFilePath = checkConfigFile(xmlFilePath);
		File xmlFile = new File(xmlFilePath);
		readConfigFile(xmlFile);
	}
	
	private String checkConfigFile(String xmlFilePath){
		File xmlFile = new File(xmlFilePath);
		return checkConfigFile(xmlFile).getAbsolutePath();
	}
	
	private File checkConfigFile(File xmlFile){
		if(null != xmlFile)
		{
			if(!xmlFile.exists())
			{
				xmlFile = new File(defaultConfigFilePath);
			}
		}
		return xmlFile;
	}

	public File getConfigFile() {
		return configFile;
	}
	
	public String readFontFile(String fontName) throws DocumentException, IOException{
		File file = new File(Environment.tempDir + File.separatorChar + fontName);
		InputStream fontStream = Configuration.class.getClass().getResourceAsStream("/fonts/" + fontName);
		FileOutputStream fos = new FileOutputStream(file);
		byte[] b = new byte[2048];
		int len = 0;
		while( (len = fontStream.read(b)) != -1){
			fos.write(b,0,len);
			fos.flush();
		}
		fos.close();
		
		if(null == file || !file.exists() || !file.isFile())
			throw new IOException("No font file");
		return file.getAbsolutePath();
	}

}
