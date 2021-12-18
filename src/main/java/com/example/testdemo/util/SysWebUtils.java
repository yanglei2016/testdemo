package com.example.testdemo.util;

import com.example.testdemo.common.contants.PlatFormConstants;
import com.example.testdemo.common.vo.UserVo;
import com.example.testdemo.mybatis.entity.Menu;
import com.example.testdemo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统web工具类
 * @author yanglei
 * 2017年7月14日 下午4:35:57
 */
@Lazy
@Component
public class SysWebUtils {
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 刷新权限信息
	 * 
	 * @author yanglei
	 * 2017年7月14日 下午4:44:33
	 */
	public void refreshAuth(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserVo userVo = (UserVo)WebUtils.getSessionAttribute(request, PlatFormConstants.USER_INFO);
		this.getMenuIdMap(userVo);
	}
	
	private void getMenuIdMap(UserVo userVo){
		Map<String, String> menuIdMap = null;
		List<Menu> menuList = menuService.selectMenuByUserId(userVo.getUserId());
		if(menuList != null && menuList.size() > 0){
			menuIdMap = new HashMap<String, String>();
			for(Menu menu : menuList){
				menuIdMap.put(menu.getMenuId(), menu.getMenuId());
			}
			userVo.setMenuIdMap(menuIdMap);
		}
	}
}
