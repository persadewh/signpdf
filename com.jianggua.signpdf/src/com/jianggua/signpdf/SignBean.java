package com.jianggua.signpdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * @author Ray Wei
 * @date 2018-1-23
 */
public class SignBean {

	static final Logger logger = LogManager.getLogger(SignBean.class);
	
	public static Map<String,String> dataMap = new HashMap<>();
	
	public static List<PageBean> pageBeanList = new ArrayList();
	
	public static void packageData(){
		logger.info("Package data");
		logger.info("Data size : " + dataMap.size());
		logger.info("Page size : " + pageBeanList.size());
		for(int i = 0 ; i < pageBeanList.size() ; i++){
			PageBean pageBean = pageBeanList.get(i);
			for(int j = 0 ; j < pageBean.getTextBeanList().size() ; j++){
				TextBean textBean = pageBean.getTextBeanList().get(j);
				if(dataMap.containsKey(textBean.getName())){
					textBean.setValue(dataMap.get(textBean.getName()));
					logger.info("Package data : " + textBean.getName() + "\t" + textBean.getValue());
				}
			}
		}
	}
	
	public static void doSign() throws IOException, DocumentException{
		PdfReader reader = new PdfReader(Parameter.getSource());
		
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(Parameter.getTarget()));
		
		if(null != reader){
			int num = reader.getNumberOfPages();
			for(int i = 1; i <= num ; i++ ){
				
				PdfContentByte content = stamper.getOverContent(i);
				
				Rectangle pageSize = reader.getPageSize(i);
				logger.info("Page : " + i + " ==> " + "width : " + pageSize.getWidth() + "\t height : " + pageSize.getHeight());
				PageBean pageBean = getPageBeanBySize(String.valueOf(pageSize.getWidth()), String.valueOf(pageSize.getHeight()));
				
				BaseFont bf = BaseFont.createFont(pageBean.getFont(), pageBean.getEncoding(), BaseFont.NOT_EMBEDDED);
				
				content.beginText();
				content.setFontAndSize(bf, Float.valueOf(pageBean.getFontSize()));
				content.setColorFill(Color.BLACK);
				
				for(int j = 0 ; j < pageBean.getTextBeanList().size(); j++){
					TextBean textBean = pageBean.getTextBeanList().get(j);
					if(!textBean.getValue().equals(""))
						content.showTextAligned(Element.ALIGN_CENTER, textBean.getValue(), Float.valueOf(textBean.getX()), Float.valueOf(textBean.getY()), 0);
				}
				
				content.endText();
				
				for(int j = 0 ; j < pageBean.getSignetBeanList().size(); j++){
					SignetBean signetBean = pageBean.getSignetBeanList().get(j);
					Image image = Image.getInstance(SignBean.class.getClass().getResource("/res/" + signetBean.getSrc()));
					image.setAbsolutePosition(Float.valueOf(signetBean.getX()), Float.valueOf(signetBean.getY()));
					image.setAlignment(Image.ALIGN_CENTER);
					
					content.addImage(image);
				}
				content.closePathStroke();
			}
		}
		
		stamper.setFormFlattening(true);
		stamper.close();
		logger.info("Success");
	}
	
	private static PageBean getPageBeanBySize(String width , String height){
		
		for(int i = 0 ; i < pageBeanList.size() ; i++){
			PageBean pageBean = pageBeanList.get(i);
			if(pageBean.getWidth().equals(width) && pageBean.getHeight().equals(height))
				return pageBean;
		}
		return null;
	}
	
}
