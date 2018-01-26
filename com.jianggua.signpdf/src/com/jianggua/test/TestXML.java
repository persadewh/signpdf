package com.jianggua.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Ray Wei
 * @date 2018-1-18
 */
public class TestXML {

	public static final String SIGN_CONFIGURATION = "sign.xml";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String filePath = System.getProperty("user.dir") + File.separatorChar + SIGN_CONFIGURATION;
		String filePath = TestXML.class.getClass().getResource("/sign.xml").getPath();
		
		File f = new File(filePath);
		System.out.println(f.getAbsolutePath());
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f); 
			
			NodeList rootList = doc.getElementsByTagName("sign");
			if(null != rootList && rootList.getLength() > 0)
			{
				Node root = rootList.item(0);
				NamedNodeMap attributes = root.getAttributes();
				if(null != attributes)
				{
					System.out.println(attributes.getNamedItem("description"));
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
