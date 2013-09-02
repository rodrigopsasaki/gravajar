package com.rodrigosasaki.gravajar.options;

public enum SecurityType{
	
	NORMAL("http://www.gravatar.com/"),
	SECURE("https://secure.gravatar.com/");
	
	private String url;
	
	private SecurityType(String url){
		this.url = url;
	}

	public String getUrl(){
		return url + "avatar/";
	}
	
	public String getProfileUrl(){
		return url;
	}
	
	public static SecurityType fromValue(String value){
		SecurityType type = null;
		for(SecurityType securityType : SecurityType.values()){
			if(securityType.getUrl().equals(value)){
				type = securityType;
				break;
			}
		}
		return type;
	}
	
}
