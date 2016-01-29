package university.south.center.service;

import java.util.List;

import university.south.center.entity.User;

public interface IUserService {
	 User getUserById(int userId);
	 void deleteUserById(int userId);
	 List<User> getUserList();
	 String checkLoginUser(String name,String password);
}
