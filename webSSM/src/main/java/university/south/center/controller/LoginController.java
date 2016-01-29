package university.south.center.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import university.south.center.dao.redis.RUserDao;
import university.south.center.entity.User;
import university.south.center.service.IUserService;
import university.south.center.util.ValidateUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource
	private IUserService userService;
	@Autowired
	private AsDbInfo asDbInfo;
	
	@Autowired
	private RUserDao rUserDao;
	
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request,Model model) {
		return "login";
	}
	
	
	/**
	 * 简单登录
	 * @param request
	 * @param model
	 * @param attr
	 * @return
	 */
	/*@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model,RedirectAttributes attr) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = userService.checkLoginUser(name, password);
		if("登录成功!".equals(message)){
			return "main";  
		}else{
			attr.addFlashAttribute("message", message);
			return "redirect:/login/toLogin";
		}
   }*/
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model,RedirectAttributes attr) {
		try{
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			if(ValidateUtil.isEmpty(name)&&ValidateUtil.isEmpty(password)){
				return "redirect:/login/toLogin";
			}
			UsernamePasswordToken token = new UsernamePasswordToken(name, password); 
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			//测试存redis
			User user = new User();
			user.setId(1);
			user.setUserName(name);
			user.setPassword(password);
			rUserDao.save(user);
			//登录成功后并不跳转至shiro配置的successUrl中,而是去下面的main
			return "redirect:/user/toMain";
		}catch (AuthenticationException e){
			attr.addFlashAttribute("message", "用户名或者密码错误, 请重试!");
			return "redirect:/login/toLogin";
		}
		
   }
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,Model model,RedirectAttributes attr) {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
			//测试从redis读取和删除redis里的数据
			User user = rUserDao.read("1");
			logger.info(user.getId());
			logger.info(user.getUserName());
			logger.info(user.getPassword());
			rUserDao.delete("1");
			user = rUserDao.read("1");
			logger.info(user);
			return "redirect:/login/toLogin";
   }
	
	@RequestMapping("/noPermission")
	public String noPermission(HttpServletRequest request,Model model,RedirectAttributes attr) {
		return "noPermission";
		
   }
}
