package com.fairy.security.core.validate.code;

import java.time.LocalDateTime;

public class ValidateCode {

    // 验证码
    private String code = null;
    
    private LocalDateTime expireTime = LocalDateTime.now().plusSeconds(300);
    
    
    
    public ValidateCode(String code, LocalDateTime expireTime) {
		super();
		this.code = code;
		this.expireTime = expireTime;
	}
    
    public ValidateCode(String code, int expireIn) {
		super();
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}




	public LocalDateTime getExpireTime() {
		return expireTime;
	}



	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}



	public boolean isExpired(){
    	return expireTime.isBefore(LocalDateTime.now());
    }
}