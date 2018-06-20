/**
 * 
 */
package com.fairy.security.core.support;

/**
 * 
 * @author Administrator
 *
 */
public class SimpleResponse {
	
	private Object content;
	

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	public SimpleResponse(Object content){
		this.content = content;
	}
}
