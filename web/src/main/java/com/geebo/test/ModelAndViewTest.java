package com.geebo.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 0:39 2020/3/15
 */
@RestController
public class ModelAndViewTest {
    @GetMapping("/test")
    public ModelAndView test(Model model) {
        HashMap map = new HashMap();
        map.put("msg", "SpringBoot With Freemark hello world!");
        return new ModelAndView("test/test", map);
    }
}

/*@Controller
public class Test {
    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg", "SpringBoot With Freemark hello world!");
        return "test/test";
    }
}*/

