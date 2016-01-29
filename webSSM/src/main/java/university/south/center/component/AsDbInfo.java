package university.south.center.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AsDbInfo {
	
	@Value("#{asDbSettings['db.name']}")  
	private String name;
	@Value("#{asDbSettings['db.password']}")  
	private String password;
	@Value("#{asDbSettings['db.role']}")  
	private String role;
	@Value("#{asDbSettings['db.permission']}")  
	private String permission;
	
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	public String getPermission() {
		return permission;
	}
	
}
