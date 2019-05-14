/**
 * @Description 
 * @author Ray Wei
 * @Date 2019-5-14
 */
package com.jianggua.pdf2image.test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.jianggua.pdf2image.Pdf2ImageDialog;
import com.jianggua.util.FontFix;

/**
 * @ClassName TestPdf2Image
 * @Description 
 * @author Ray Wei
 * @Date 2019-5-14
 */
public class TestPdf2Image {
	static final Logger logger = LogManager.getLogger(TestPdf2Image.class);
	/**
	 * @Description 
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		String strPdfFilePath = "D:\\myself\\新柴签字\\新柴签字功能点\\a0.pdf";
		
		//String strImageFilePath = Pdf2Image.pdf2image(strPdfFilePath, 0, 28.346f * 2.54f, 1);//3370,2383
		//String strImageFilePath = Pdf2Image.pdf2image(strPdfFilePath, 0, 28.346f * 2.54f, 2);//6740,4767
		String strImageFilePath = Pdf2Image.pdf2image(strPdfFilePath, 0, 28.346f * 2.54f, 3);//10111,7151
		
		logger.info(strImageFilePath);*/
		
		FontFix.initFont();
		try
	    {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;//
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        UIManager.put("RootPane.setupButtonVisible", false);
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		
		SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
            	Pdf2ImageDialog dlg = new Pdf2ImageDialog();
        		dlg.show();
            }
        });
	}

}
