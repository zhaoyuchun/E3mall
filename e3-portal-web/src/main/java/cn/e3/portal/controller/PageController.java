package cn.e3.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 跳转门户系统首页
 * @author Administrator
 *
 */
@Controller
public class PageController {
	@RequestMapping("index")
	public String showIndex(){
		return "index";
	}
}
