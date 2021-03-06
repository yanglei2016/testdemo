package com.example.testdemo.common.filter;

import com.example.testdemo.common.contants.PlatFormConstants;
import com.example.testdemo.common.vo.UserVo;
import com.example.testdemo.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 过滤器
 * @author yanglei
 * 2017年6月16日 上午10:31:21
 */
@WebFilter(filterName="loginFilter", urlPatterns={"*.do"})
public class LoginFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String requestUri = request.getRequestURI();
		// 去掉工程名
		requestUri = requestUri.replace(request.getContextPath()+"/", "");
		
		// 是否跳过检查
		String ignoreUrl = PropertiesUtils.getProp("login.ignore.passUrl");
		if(Pattern.matches(ignoreUrl, requestUri)){
			filterChain.doFilter(req, res);
			return ;
		}else{
			// 获取登录用户信息
			UserVo userVo = (UserVo)request.getSession().getAttribute(PlatFormConstants.USER_INFO);
			if (userVo == null){
				// 用户未登录
				logger.error("登录过滤器用户未登录");
				response.sendRedirect(request.getContextPath() + "/index.do");
				return ;
			}
		}
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
