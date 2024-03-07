package com.xm.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应格式为图片
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 生成验证码文字
        String text = defaultKaptcha.createText();
        // 将验证码文字保存到session
        request.getSession().setAttribute("captcha", text);
        // 生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(text);
        // 将图片输出到响应中
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}

