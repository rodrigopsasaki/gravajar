package com.rodrigosasaki.gravajar.options;

public enum DefaultImage{

	GRAVATAR_ICON("404"),
	HTTP_404("404"),
	MYSTERY_MAN("mm"),
	IDENTICON("identicon"),
	MONSTER_ID("monsterid"),
	WAVATAR("wavatar"),
	RETRO("retro"),
	BLANK("blank");
	
	private String value;
	
	private DefaultImage(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}
	
	public static DefaultImage fromValue(String value){
		DefaultImage image = null;
		for(DefaultImage defaultImage : values()){
			if(defaultImage.getValue().equals(value)){
				image = defaultImage;
				break;
			}
		}
		return image;
	}
	
}
