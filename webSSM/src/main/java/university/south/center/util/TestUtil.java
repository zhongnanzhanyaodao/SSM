package university.south.center.util;


public class TestUtil {
	public static void main(String[] args) {
		String userName = PropertiesUtil.getProValueByKey("asdb.properties", "db.name");
        String password = PropertiesUtil.getProValueByKey("asdb.properties", "db.password");
		System.out.println(PropertiesUtil.getProValueByKey("asdb.properties", "db.role"));
		System.out.println(userName);
		System.out.println(password);
	}
}
