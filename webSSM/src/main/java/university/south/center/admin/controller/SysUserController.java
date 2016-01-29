package university.south.center.admin.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class SysUserController {
	
	//@RequiresPermissions("admin:delete")
	@RequestMapping("/toMain")
	public String toMain(HttpServletRequest request,Model model) {
		 return "admin/sysMain";
	}
	
	//@RequiresRoles("admin")
	@RequestMapping("/testBootStrap")
	public String testBootStrap(HttpServletRequest request,Model model){
		return "firstBS";
	}
	
}
