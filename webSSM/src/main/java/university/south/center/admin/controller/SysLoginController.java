package university.south.center.admin.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import university.south.center.component.AsDbInfo;
import university.south.center.util.ValidateUtil;


@Controller
@RequestMapping("/admin/login")
public class SysLoginController {
	@Autowired
	private AsDbInfo asDbInfo;
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request,Model model) {
		return "admin/sysLogin";
	}
	
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model,RedirectAttributes attr) {
		try{
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			if(ValidateUtil.isEmpty(name)&&ValidateUtil.isEmpty(password)){
				return "redirect:/admin/login/toLogin";
			}
			UsernamePasswordToken token = new UsernamePasswordToken(name, password); 
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			//登录成功后并不跳转至shiro配置的successUrl中,而是去下面的main
			return "redirect:/admin/user/toMain";
		}catch (AuthenticationException e){
			attr.addFlashAttribute("message", "用户名或者密码错误, 请重试!");
			return "redirect:/admin/login/toLogin";
		}
		
   }
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,Model model,RedirectAttributes attr) {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
			return "redirect:/admin/login/toLogin";
   }
	
}
