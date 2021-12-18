package com.example.testdemo.controller;

import com.example.testdemo.common.contants.PlatFormConstants;
import com.example.testdemo.common.vo.UserVo;
import com.example.testdemo.mybatis.entity.User;
import com.example.testdemo.service.UserService;
import com.example.testdemo.util.MD5Util;
import com.example.testdemo.util.SysWebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录
 * @author yanglei
 * 2017年6月16日 上午9:13:58
 */
@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String LOGIN_PAGE = "login";
	
	@Autowired
	private UserService userService;
	@Autowired
	private SysWebUtils sysWebUtils;
	
	/**
	 * 登录
	 * @author yanglei
	 * 2017年6月16日 上午9:17:41
	 */
	@RequestMapping("login.do")
	public String login(Model model, HttpServletRequest request, String userName, String password){
		logger.info("进入登录页");
		
		try{
			User user = userService.checkUser(userName, MD5Util.md5(password));
			if(user != null){
				UserVo userVo = new UserVo();
				userVo.setUserId(user.getUserId());
				userVo.setUserName(user.getUserName());
				HttpSession session = request.getSession();
				session.setAttribute(PlatFormConstants.USER_INFO, userVo);
				
				sysWebUtils.refreshAuth();
			}else{
				throw new RuntimeException("用户名或密码错误");
			}
			return "redirect:loginSuccess.do";
		}catch(Exception e){
			logger.error("登录失败", e);
			model.addAttribute(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_NOPASS);
			model.addAttribute(PlatFormConstants.RESPONSE_MESSAGE, "登录失败");
			model.addAttribute(PlatFormConstants.MESSAGE_EXCEPTION_DETAIL, e.getMessage());
			return LOGIN_PAGE;
		}
	}
	
	/**
	 * 登出
	 * @author yanglei
	 * 2017年6月16日 上午9:17:45
	 */
	@RequestMapping("logout.do")
	public String logout(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(PlatFormConstants.USER_INFO);
		return LOGIN_PAGE;
	}
}
