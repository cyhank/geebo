package com.geebo.test;

import com.geebo.verified.VerifyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 21:26 2020/3/24
 */
@RestController
public class KaptchaController {

    @GetMapping("/img/index")
    public ModelAndView getImgIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap map = new HashMap();
        map.put("msg", "点击跳转到");

        return new ModelAndView("img/index", map);
    }

    @GetMapping("/img/img")
    public ModelAndView getImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap map = new HashMap();
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片
            request.getSession().setAttribute("vrifyCode", (String) request.getSession().getAttribute("RANDOMREDISKEY"));
            //将生成的随机验证码存放到redis中
//            redisService.setForValue("RANDOMVALIDATECODEKEY", (String) request.getSession().getAttribute("RANDOMREDISKEY"), Long.parseLong("60000"));
            map.put("msg", randomValidateCode);
            // TODO getOutputStream() has already been called for this response

        } catch (Exception e) {
//            logger.error("获取验证码异常：", e);
        }
        return new ModelAndView("img/img", map);
    }

    @PostMapping("/img/verify")
    public ModelAndView verify(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        String parameter = request.getParameter("vrifyCode");
        System.out.println("Session  vrifyCode " + captchaId + " form vrifyCode " + parameter);

        if (!captchaId.equals(parameter)) {
            mav.addObject("msg", "错误的验证码").setViewName("index");
        } else {
            mav.addObject("msg", "登录成功").setViewName("index");
        }
        return mav;
    }
}
