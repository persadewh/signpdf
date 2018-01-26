package com.jianggua.test;

import java.io.File;

/**
 * @author Ray Wei
 * @date 2018-1-19
 */
public class TestSignet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String signet = TestSignet.class.getClass().getResource("/res/A.jpg").getPath();
		File file = new File(signet);
		System.out.println(file.exists());
	}

}
