package com.fairy.security.core.validate.code.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.ValidateCodeGenerator;

/**
 * 图片验证码生成器
 * @author Administrator
 *
 */
//该类在ValidateCodeBeanConfig里条件化配置，不需要@Component注解
public class ImageCodeGenerator implements ValidateCodeGenerator {

	
	private SecurityProperties securityProperties;
	
	private Random random = new Random();
	@Override
	public ImageCode generate(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
		int codeCount = ServletRequestUtils.getIntParameter(request.getRequest(), "length", securityProperties.getCode().getImage().getLength());
		int lineCount = ServletRequestUtils.getIntParameter(request.getRequest(), "line", securityProperties.getCode().getImage().getLine());
		int expireIn = ServletRequestUtils.getIntParameter(request.getRequest(), "expireIn", securityProperties.getCode().getImage().getExpireIn());
		
		BufferedImage buffImg = null;
		String code = null;
		
		int fontWidth = width / codeCount;// 字体的宽度
        int fontHeight = height - 5;// 字体的高度
        int codeY = height - 8;
        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        //Graphics2D g = buffImg.createGraphics();
        // 设置背景色
        Color color = getRandBackgroundColor();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        
        
        
        // 设置字体
        //Font font1 = getFont(fontHeight);
        Font font = getFont(fontHeight);
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点
        float yawpRate = 0.01f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            buffImg.setRGB(x, y, random.nextInt(255));
        }

        //扭曲图片
        shear(g, width, height, color);
        
        String str1 = randomStr(codeCount);// 得到随机字符
        code = str1;
        for (int i = 0; i < codeCount; i++) {
            String strRand = str1.substring(i, i + 1);
            g.setColor(getRandColor(0, 100, -122, 122, -157, 157));
            // g.drawString(a,x,y);
            // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
            
            g.drawString(strRand, i*fontWidth+3, codeY);
            
        }

		
		ImageCode imageCode = new ImageCode(code, buffImg, expireIn);
		return imageCode;
	}
	
	/**   
	 * @Title: getRandBackgroundColor   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: Color      
	 * @throws   
	 */
	private Color getRandBackgroundColor() {
		int one = random.nextInt(11);
		switch (one) {
		case 1 : return new Color(25, 202, 173);
		case 2 : return new Color(140, 199, 181);
		case 3 : return new Color(160 , 238 , 225);
		case 4 : return new Color(190 , 231 , 233);
		case 5 : return new Color(190, 237 , 199);
		case 6 : return new Color(214, 213 , 183);
		case 7 : return new Color(209 , 186 , 116);
		case 8 : return new Color(230, 206 , 172);
		case 9 : return new Color(236, 173 , 158);
		case 10 : return new Color(244 , 96 , 108);
		}
		return null;
	}

	/**
	 * 
	 * @Title: getRandColor   
	 * @Description: 使用rgb模式获取颜色  
	 * @param: @param fc
	 * @param: @param bc
	 * @param: @return      
	 * @return: Color      
	 * @throws
	 */
    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    /**
	 * 
	 * @Title: getRandColor   
	 * @Description: 使用yuv模式生成随机颜色   
	 * @param: @param ymin
	 * @param: @param ymax
	 * @param: @param umin
	 * @param: @param umax
	 * @param: @param vmin
	 * @param: @param vmax
	 * @param: @return      
	 * @return: Color      
	 * @throws
	 */
    private Color getRandColor(int ymin, int ymax, int umin, int umax, int vmin, int vmax) {// 给定范围获得随机颜色
        if (ymin > 255) ymin = 255;
        if (ymax > 255) ymax = 255;
        if (ymin < 0) ymin = 0;
        if (ymax < 0) ymax = 0;
        if (umin > 122) umin = 122;
        if (umax > 122) umax = 122;
        if (umin < -122) umin = -122;
        if (umax < -122) umax = -122;
        if (vmin > 157) vmin = 157;
        if (vmax > 157) vmax = 157;
        if (vmin < -157) vmin = -157;
        if (vmax < -157) vmax = -157;
        int y = ymin + random.nextInt(ymax - ymin + 1);
        int u = umin + random.nextInt(umax - umin + 1);
        int v = vmin + random.nextInt(vmax - vmin + 1);
        int R = (int)(y+1.14*v);
        int G = (int)(y-0.394*u-0.581*v);
        int B = (int)(y+2.028*u);
        if (R<0) R=0;
        if (G<0) G=0;
        if (B<0) B=0;
        if (R>255) R=255;
        if (G>255) G=255;
        if (B>255) B=255;
        return new Color(R, G, B);
    }

 // 得到随机字符
    private String randomStr(int n) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }
    
    /**
     * 产生随机字体
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }
    
    // 扭曲方法
        private void shear(Graphics g, int w1, int h1, Color color) {
            shearX(g, w1, h1, color);
            shearY(g, w1, h1, color);
        }

        private void shearX(Graphics g, int w1, int h1, Color color) {

            int period = random.nextInt(2);

            boolean borderGap = true;
            int frames = 1;
            int phase = random.nextInt(2);

            for (int i = 0; i < h1; i++) {
                double d = (double) (period >> 1)
                        * Math.sin((double) i / (double) period
                                + (6.2831853071795862D * (double) phase)
                                / (double) frames);
                g.copyArea(0, i, w1, 1, (int) d, 0);
                if (borderGap) {
                    g.setColor(color);
                    g.drawLine((int) d, i, 0, i);
                    g.drawLine((int) d + w1, i, w1, i);
                }
            }

        }

        private void shearY(Graphics g, int w1, int h1, Color color) {

            int period = random.nextInt(40) + 10; // 50;

            boolean borderGap = true;
            int frames = 20;
            int phase = 7;
            for (int i = 0; i < w1; i++) {
                double d = (double) (period >> 1)
                        * Math.sin((double) i / (double) period
                                + (6.2831853071795862D * (double) phase)
                                / (double) frames);
                g.copyArea(i, 0, 1, h1, 0, (int) d);
                if (borderGap) {
                    g.setColor(color);
                    g.drawLine(i, (int) d, i, 0);
                    g.drawLine(i, (int) d + h1, i, h1);
                }

            }

        }

		public SecurityProperties getSecurityProperties() {
			return securityProperties;
		}

		public void setSecurityProperties(SecurityProperties securityProperties) {
			this.securityProperties = securityProperties;
		}
        
}
