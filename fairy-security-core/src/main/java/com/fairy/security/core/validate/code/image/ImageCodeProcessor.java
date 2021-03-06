/**
 * 
 */
package com.fairy.security.core.validate.code.image;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.validate.code.ValidateCodeProcessor;
import com.fairy.security.core.validate.impl.AbstractValidateCodeProcessor;

/**
 * 图片验证码处理器
 * @author Administrator
 *
 */
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> implements ValidateCodeProcessor {


	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
		ImageIO.write(imageCode.getBuffImg(), "JPEG", request.getResponse().getOutputStream());
	}

}
