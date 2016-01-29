package university.south.center.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import university.south.center.entity.User;
import university.south.center.service.IUserService;
import university.south.center.util.ExcelUtil;

@Controller
@RequestMapping("/excel")
public class ExcelController {
	
		@Resource
		private IUserService userService;
	
	
	   @RequestMapping(value="/export")
	    public String export(HttpServletRequest request,HttpServletResponse response){
	        //填充projects数据
	        List<User> projects=createData();
	        List<Map<String,Object>> list=createExcelRecord(projects);
	        String columnNames[]={"ID","用户名","年龄","密码"};//列名
	        String keys[]   =    {"id","name","age","password"};//map中的key
	        try {
				ExcelUtil.exportExcel(list, keys, columnNames, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return null;
	    }
	    private List<User> createData() {
	        // TODO Auto-generated method stub
	        //自己实现
	        return userService.getUserList();
	    }
	    
	    
	    private List<Map<String, Object>> createExcelRecord(List<User> projects) {
	        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("sheetName", "sheetName");
	        map.put("titleName", "titleName");
	        map.put("fileName", "fileName"+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
	        listmap.add(map);
	        User project=null;
	        for (int j = 0; j < projects.size(); j++) {
	            project=projects.get(j);
	            Map<String, Object> mapValue = new HashMap<String, Object>();
	            mapValue.put("id", project.getId());
	            mapValue.put("name", project.getUserName());
	            mapValue.put("age", project.getAge());
	            mapValue.put("password", project.getPassword());
	            listmap.add(mapValue);
	        }
	        return listmap;
	    }
}
