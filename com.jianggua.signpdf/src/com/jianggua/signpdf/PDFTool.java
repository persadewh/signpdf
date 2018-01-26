package com.jianggua.signpdf;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * @author Ray Wei
 * @date 2018-1-22
 */
public class PDFTool {
	
	static final Logger logger = LogManager.getLogger(PDFTool.class);
	
	/**
	 * show the size of pdf file
	 * 
	 * @param pdfPath
	 * @throws IOException
	 */
	public static void showPDFSize(String pdfPath) throws IOException{
		PdfReader reader = new PdfReader(pdfPath);
		if(null != reader){
			int num = reader.getNumberOfPages();
			logger.info("The number of pages in pdf file is " + num);
			PdfContentByte content = null;
			for(int i = 1; i <= num ; i++ ){
				Rectangle pageSize = reader.getPageSize(i);
				logger.info("Page : " + i + " ==> " + "width : " + pageSize.getWidth() + "\t height : " + pageSize.getHeight());
			}
		}
	}

	
}
