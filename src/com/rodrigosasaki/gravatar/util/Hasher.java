package com.rodrigosasaki.gravajar.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher{

	private static char[] hexDigits = "0123456789abcdef".toCharArray();

	private static String hex(byte[] digest){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++){
			sb.append(hexDigits[(digest[i] >> 4) & 0xF]);
			sb.append(hexDigits[digest[i] & 0xF]);
		}
		return sb.toString();
	}

	public static String hash(String message){
		String hash = null;
		try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			hash = hex(digest.digest(message.getBytes()));
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return hash;
	}
}
