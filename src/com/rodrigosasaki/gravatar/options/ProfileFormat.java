package com.rodrigosasaki.gravajar.options;

public enum ProfileFormat{

	JSON(".json"), XML(".xml"), PHP(".php"), VCF(".vcf"), QR(".qr");

	private String extension;

	private ProfileFormat(String extension){
		this.extension = extension;
	}

	public String getExtension(){
		return extension;
	}
	
	public static ProfileFormat fromExtension(String extension){
		ProfileFormat format = null;
		for(ProfileFormat profileFormat : values()){
			if(profileFormat.getExtension().equals(extension)){
				format = profileFormat;
				break;
			}
		}
		return format;
	}

}