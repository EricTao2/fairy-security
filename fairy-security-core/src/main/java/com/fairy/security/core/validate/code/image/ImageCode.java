package com.fairy.security.core.validate.code.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Random;

import javax.imageio.ImageIO;

import com.fairy.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode {

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