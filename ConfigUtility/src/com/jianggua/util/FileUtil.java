package com.jianggua.util;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {

	static final Logger logger = LogManager.getLogger(FileUtil.class);
	
	public static boolean fileChannelCopy(File source, File target){
		
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		
		try {
			fi = new FileInputStream(source);
			fo = new FileOutputStream(target);
			in = fi.getChannel();
			out = fo.getChannel();
			in.transferTo(0, in.size(), out);
			logger.info("Copy successful");
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("File Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException");
		}
		finally{
			try {
				if(fi != null)
				{
					fi.close();
					fi = null;
				}
				if(fo != null)
				{
					fo.close();
					fo = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean fileChannelCopy(String source, String target){
		return fileChannelCopy(new File(source),new File(target));
	}
	
	public static boolean isFile(String filePath){
		if(null != filePath && !"".equals(filePath)){
			File file = new File(filePath);
			if(null != file){
				if(file.exists()){
					return file.isFile();
				}
			}
		}
		return false;
	}
		
	public static boolean isDir(String dirPath){
		if(null != dirPath && !"".equals(dirPath)){
			File dir = new File(dirPath);
			if(null != dir){
				if(dir.exists()){
					return dir.isDirectory();
				}
			}
		}
		return false;
	}
	
	public static String chooseFile(Component parent, String strPref, String ext, String strTitle){
		String strChooseFilePath = null;
		
		String lastFilePath = Preferences.userRoot().node(FileUtil.class.toString()).get(strPref, "");
		
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "(*.pdf)";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if(f.getName().endsWith(".pdf") || f.isDirectory())
					return true;
				return false;
			}
		});
		
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle(strTitle);
		fc.setApproveButtonText("OK");
		if(null != lastFilePath && !lastFilePath.equals("")){
			fc.setSelectedFile(new File(lastFilePath));
		}
		
		int userOption = fc.showOpenDialog(parent);
		
		if(userOption == JFileChooser.APPROVE_OPTION){
			File selectedFile = fc.getSelectedFile();
			if(null != selectedFile){
				strChooseFilePath = selectedFile.getAbsolutePath();
				Preferences.userRoot().node(FileUtil.class.toString()).put(strPref, strChooseFilePath);
			}
		}
		
		return strChooseFilePath;
	}
	
}
