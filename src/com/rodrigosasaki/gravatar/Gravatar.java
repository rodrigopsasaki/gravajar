package com.rodrigosasaki.gravajar;

import static com.rodrigosasaki.gravajar.options.DefaultImage.GRAVATAR_ICON;
import static com.rodrigosasaki.gravajar.options.Rating.G;
import static com.rodrigosasaki.gravajar.options.SecurityType.NORMAL;
import static com.rodrigosasaki.gravajar.util.Hasher.hash;
import static com.rodrigosasaki.gravajar.util.Concatenator.concat;
import static com.rodrigosasaki.gravajar.util.IOUtils.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.rodrigosasaki.gravajar.options.DefaultImage;
import com.rodrigosasaki.gravajar.options.ProfileFormat;
import com.rodrigosasaki.gravajar.options.Rating;
import com.rodrigosasaki.gravajar.options.SecurityType;

public class Gravatar{
	
	private static final int DEFAULT_SIZE = 80;

	private int size = DEFAULT_SIZE;
	private DefaultImage defaultImage = GRAVATAR_ICON;
	private Rating rating = G;
	private SecurityType securityType = NORMAL;
	private boolean forceDefault;

	private String hash;

	public Gravatar(String email){
		if(email == null){
			throw new IllegalArgumentException("Email must not be null");
		}
		this.hash = hash(email.toLowerCase());
	}
	
	public static void main(String[] args){
		Gravatar g = new Gravatar("rodrigopsasaki@gmail.com");
		g.setSize(150);
		System.out.println(g.getAvatarUrl());
	}

	public int getSize(){
		return size;
	}

	public void setSize(int size){
		if (size < 1 || size > 2048){
			throw new IllegalArgumentException("Size must be a valid integer between 1 and 2048");
		}
		this.size = size;
	}

	public DefaultImage getDefaultImage(){
		return defaultImage;
	}

	public void setDefaultImage(DefaultImage defaultImage){
		if (defaultImage == null){
			throw new IllegalArgumentException("Default Image must not be null");
		}
		this.defaultImage = defaultImage;
	}

	public Rating getRating(){
		return rating;
	}

	public void setRating(Rating rating){
		if (rating == null){
			throw new IllegalArgumentException("Rating must not be null");
		}
		this.rating = rating;
	}

	public SecurityType getSecurityType(){
		return securityType;
	}

	public void setSecurityType(SecurityType securityType){
		if (securityType == null){
			throw new IllegalArgumentException("Security Type must not be null");
		}
		this.securityType = securityType;
	}
	
	public boolean isForceDefault(){
		return forceDefault;
	}

	public void setForceDefault(boolean forceDefault){
		this.forceDefault = forceDefault;
	}
	
	public String getAvatarUrl(){
		StringBuilder path = new StringBuilder(securityType.getUrl() + hash);
		addAdditionalParameters(path);
		return path.toString();
	}
	
	public String getProfileUrl(ProfileFormat format){
		return securityType.getProfileUrl() + hash + format.getExtension();
	}

	public byte[] getProfile(ProfileFormat format){
		try{
			URL url = new URL(getProfileUrl(format));
			url.openConnection();
			return inputStreamToByteArray(url.openStream());
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public byte[] getAvatar(){
		try{
			URL url = new URL(getAvatarUrl());
			return inputStreamToByteArray(url.openStream());
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}

	private void addAdditionalParameters(StringBuilder path){
		List<String> params = new ArrayList<String>();
		
		if(DEFAULT_SIZE != size){
			params.add("s=" + size);
		}
		
		if(!GRAVATAR_ICON.equals(defaultImage)){
			params.add("d=" + defaultImage.getValue());
			if(forceDefault){
				params.add("f=y");
			}
		}
		
		if(!G.equals(rating)){
			params.add("r=" + rating.getValue());
		}
		
		if(!params.isEmpty()){
			path.append("?");
			path.append(concat(params, "&"));
		}
		
	}

}
