package com.jianggua.test;

import java.awt.GraphicsEnvironment;

/**
 * 
 * @author 	Ray Wei
 * @date	2018-01-18
 * Getting valid fonts from local machine
 */
public class TestFont {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = e.getAvailableFontFamilyNames();
		for(int i = 0; i < fontNames.length ; i++){
			System.out.println(fontNames[i]);
		}
	}

}
