/**
 * @Description 
 * @author Ray Wei
 * @Date 2019-5-14
 */
package com.jianggua.pdf2image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.jianggua.util.FileUtil;

/**
 * @ClassName Pdf2Image
 * @Description 
 * @author Ray Wei
 * @Date 2019-5-14
 */
public class Pdf2Image {

	static final Logger logger = LogManager.getLogger(Pdf2Image.class);
	
	private Pdf2Image(){}
	
	/**
	 * @Description 
	 * pdf2image
	 * @param strPdfFilePath
	 * @param iPageIndex:start from 0
	 * @param dpi: 1dpi=28.346 * 2.54
	 * @return
	 */
	public static String pdf2image(String strPdfFilePath, int iPageIndex, float dpi, int scale){
		String strImageFilePath = null;
		
		File pdfFile = new File(strPdfFilePath);
		PDDocument pdfDoc = null;
		try {
			pdfDoc = PDDocument.load(pdfFile);
			PDFRenderer renderer = new PDFRenderer(pdfDoc);
			int iPageCount = pdfDoc.getNumberOfPages();
			if(iPageCount >= iPageIndex){
				strImageFilePath = strPdfFilePath.substring(0, strPdfFilePath.lastIndexOf(".pdf")) + "_x" + scale + ".png";
				BufferedImage image = renderer.renderImageWithDPI(iPageIndex, dpi * scale);
				logger.info(image.getWidth() + "," + image.getHeight());
				ImageIO.write(image, "PNG", new File(strImageFilePath));
				if(!FileUtil.isFile(strImageFilePath)){
					strImageFilePath = null;
				}
			}
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if(null != pdfDoc){
				try {
					pdfDoc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
		return strImageFilePath;
	}
	
}
