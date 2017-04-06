package me.someway.example.ssm.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.someway.example.ssm.constant.Constant;
import me.someway.example.ssm.util.ValidCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("prototype")
public class StaticController {
	private static final Logger logger = LoggerFactory.getLogger(StaticController.class);

	/**
	 * 图形验证码
	 */
	@RequestMapping(value = "/validImg", method = RequestMethod.GET)
    public void validImg(HttpServletResponse response, HttpSession session) throws Exception {
		 OutputStream output = null;
		 InputStream in = null;
		 try {
        	ValidCodeGenerator authCode = ValidCodeGenerator.getInstance();
        	session.setAttribute(Constant.VALID_CODE, authCode.getString());
        	//验证码错误次数
        	session.setAttribute(Constant.VALID_ERROR_COUNT, 0);
        	
            //设置验证码输出格式
            response.setContentType("image/jpeg");
            response.setCharacterEncoding("utf-8");
            output = new BufferedOutputStream(response.getOutputStream());
            in = authCode.getImage();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf, 0, 1024)) != -1) {
                output.write(buf, 0, len);
            }
            response.flushBuffer();
            output.flush();
            in.close();
            output.close();
        } catch (IOException e) {
        	logger.error("build validCode error:{}", e);
        } finally {
		 	if (null != in ) {
				in.close();
			}
			if (null != output){
        		output.close();
		 	}
        }
    }
    
}
