package com.rodrigosasaki.gravajar.util;

import java.util.List;

public class Concatenator{

	public static String concat(List<String> items, String divisor){
		StringBuilder result = new StringBuilder();
		
		for(String item : items){
			result.append(item);
			result.append(divisor);
		}
		result.deleteCharAt(result.lastIndexOf(divisor));
		return result.toString();
	}
}
