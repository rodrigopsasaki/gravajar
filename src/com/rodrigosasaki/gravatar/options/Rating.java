package com.rodrigosasaki.gravajar.options;

public enum Rating{
	
	G("g"),
	PG("pg"),
	R("r"),
	X("x");
	
	private String value;
	
	private Rating(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}
	
	public static Rating fromValue(String value){
		Rating valueRating = null;
		for(Rating rating : values()){
			if(rating.getValue().equals(value)){
				valueRating = rating;
				break;
			}
		}
		return valueRating;
	}
	
}
