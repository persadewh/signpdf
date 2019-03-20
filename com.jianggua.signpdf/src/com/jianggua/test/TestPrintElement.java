package com.jianggua.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jianggua.signpdf.Parameter;
import com.jianggua.signpdf.SignBean;
import com.lowagie.rups.controller.PdfReaderController;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

public class TestPrintElement {

	static final Logger logger = LogManager.getLogger(SignBean.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strPdfPath = "D:\\myself\\新柴签字\\新柴签字功能点\\result.pdf";
	
		
		
		try {
			PdfReader reader = new PdfReader(strPdfPath);
			
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("D:\\3.pdf"));
			
			
			
			if(null != reader){
				int num = reader.getNumberOfPages();
				logger.info("The number of pages in pdf file is " + num);
				
				for(int i = 1; i <= num ; i++ ){
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
