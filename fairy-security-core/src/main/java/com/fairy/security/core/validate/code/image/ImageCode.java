package com.fairy.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import com.fairy.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5279440935013444577L;
	// 验证码图片Buffer
    private BufferedImage buffImg = null;
    
    
    public ImageCode(String code, BufferedImage buffImg, LocalDateTime expireTime) {
		super(code, expireTime);
		this.buffImg = buffImg;
	}
    
    public ImageCode(String code, BufferedImage buffImg, int expireIn) {
    	super(code, expireIn);
		this.buffImg = buffImg;
	}





	public BufferedImage getBuffImg() {
		return buffImg;
	}



	public void setBuffImg(BufferedImage buffImg) {
		this.buffImg = buffImg;
	}



	
}