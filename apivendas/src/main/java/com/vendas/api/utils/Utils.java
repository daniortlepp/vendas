package com.vendas.api.utils;

import java.util.regex.Pattern;

public class Utils {
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	    return pattern.matcher(strNum).matches();
	}

}
