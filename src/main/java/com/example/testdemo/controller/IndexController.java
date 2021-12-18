package com.example.testdemo.controller;


import com.example.testdemo.common.contants.PlatFormConstants;
import com.example.testdemo.common.vo.UserVo;
import com.example.testdemo.mybatis.entity.Menu;
import com.example.testdemo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @author yanglei
 * 2017年6月14日 下午4:19:56
 */
@Controller
public class IndexController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request){
		return "login";
	}
	
	@RequestMapping("/loginSuccess.do")
	public String loginSuccess(Model model, HttpServletRequest request){
		UserVo userVo = (UserVo)request.getSession().getAttribute(PlatFormConstants.USER_INFO);
		List<Menu> leftMenuList = menuService.selectLeftMenuList(userVo.getUserId());
		model.addAttribute("leftMenuList", leftMenuList);
		return "layout/index";
	}
	
	/**
	 * 跳转到欢迎页面
	 * @author yanglei
	 * 2017年6月14日 下午4:28:48
	 */
	@RequestMapping("/welcome.do")
	public String welcome(Model model, HttpServletRequest request){
		return "layout/welcome";
	}
}
