package com.jianggua.signpdf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray Wei
 * @date 2018-1-23
 */
public class PageBean {

	private String width = null;
	private String height = null;
	private String font = null;
	private String fontSize = "10";
	private String encoding = null;
	
	private List<TextBean> textBeanList = new ArrayList();
	
	private List<SignetBean> signetBeanList = new ArrayList();

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public List<TextBean> getTextBeanList() {
		return textBeanList;
	}

	public void setTextBeanList(List<TextBean> textBeanList) {
		this.textBeanList = textBeanList;
	}

	public List<SignetBean> getSignetBeanList() {
		return signetBeanList;
	}

	public void setSignetBeanList(List<SignetBean> signetBeanList) {
		this.signetBeanList = signetBeanList;
	}
	
	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
}
