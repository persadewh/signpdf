package com.jianggua.signpdf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import com.jianggua.util.FileUtil;

public class DataBean {

	static Logger logger = LogManager.getLogger(DataBean.class);
	
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
