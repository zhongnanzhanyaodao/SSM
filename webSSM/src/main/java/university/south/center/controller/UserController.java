package university.south.center.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mydubbo.api.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import university.south.center.entity.User;
import university.south.center.service.IUserService;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@Resource  
	private DemoService demoService; 
	
	@RequestMapping("/toMain")
	public String toMain(HttpServletRequest request,Model model) {
		 return "main";
	}
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	@RequestMapping("/testBootStrap")
	public String testBootStrap(HttpServletRequest request,Model model){
		return "firstBS";
	}
	
	@RequestMapping("/testUserTrans")
	public String testUserTrans(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		userService.deleteUserById(userId);
		return "showUser";
	}
	@ResponseBody
	@RequestMapping(value="/testJson",produces="text/html;charset=UTF-8")//produces="text/html;charset=UTF-8"的作用是避免中文乱码
	public String testJson(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUserById(userId);
		String str = JSON.toJSONString(user);
		System.out.println(str);
		return str;
	}
	
	@RequestMapping("/toFileUpload")
	public String toFileUpload(HttpServletRequest request,Model model) {
		 return "fileUpload";
		
	}
	
	
	@RequestMapping("/fileUpload")
	public String fileUpload(HttpServletRequest request,Model model) throws IllegalStateException, IOException{
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "NewUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        String path = "E:/" + fileName;  
                        File localFile = new File(path); 
                        //上传文件到本地
                        file.transferTo(localFile);  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
        return "success";  
		
	}
	
	@ResponseBody
	@RequestMapping("/testDubbo")
	public String testDubbo(HttpServletRequest request,Model model) {
		System.out.println("Test dubbo begin .............");
		if (demoService!=null) {
			return demoService.sayHello("dwj");
		}
		return "no people";
		
	}
	
}
