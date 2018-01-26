package com.jianggua.test;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import com.jianggua.exception.ParameterException;
import com.jianggua.signpdf.Parameter;

public class TestParameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Parameter.initParameters(args);
			Parameter.checkParameters();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
