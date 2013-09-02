package com.rodrigosasaki.gravajar.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils{

	public static byte[] inputStreamToByteArray(InputStream input){
		BufferedInputStream bufferedIn = new BufferedInputStream(input, 512);
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOut = new BufferedOutputStream(byteArrayOut, 512);
		int c;
		try{
			while ((c = bufferedIn.read()) != -1){
				bufferedOut.write((char) c);
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			close(bufferedOut, byteArrayOut, bufferedIn);
		}
		return byteArrayOut.toByteArray();
	}
	
	public static void close(Closeable... closeables){
		try{
			for (Closeable closeable : closeables){
				if (closeable != null){
					closeable.close();
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
