package university.south.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import university.south.center.dao.UserDao;
import university.south.center.entity.User;
import university.south.center.service.IUserService;
@Service("userService") 
public class UserServiceImpl implements IUserService {

	@Resource
	private UserDao userDao;
	@Override
	public User getUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}
	
	@Transactional
	@Override
	public void deleteUserById(int userId) {
		 userDao.deleteByPrimaryKey(userId);
		 String a = null;
		 if(a.equals("")){
			 //人为抛出异常，测试事务
		 }
			 
	}
	
	public List<User> getUserList() {
		return userDao.selectList();
	}

	@Override
	public String checkLoginUser(String name,String password) {
		
		 User user= userDao.selectByUserName(name);
		 if(user==null||"".equals(user)){
			 return "用户名不存在!";
		 }else{
			 if(password!=null&&password.equals(user.getPassword())){
				 return "登录成功!";
			 }else{
				 return "密码错误!";
			 }
		 }
	}

}
