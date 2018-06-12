package com.fairy.security.core.properties;

public class ImageCodeProperties extends SmsCodeProperties {
	
	private int width = 100;
	
	private int height = 30;
	
	private int line = 10;
	
	public ImageCodeProperties(){
		setLength(4);
		setUrl("/authentication/form");
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}




}
