package university.south.center.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesUtil {
	public static void main(String[] args) { 
        Properties prop = new Properties();     
        try{
            //读取属性文件a.properties
            InputStream in = ClassLoader.getSystemResourceAsStream("asdb.properties"); 
            //加载属性列表
            prop.load(in);     
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+prop.getProperty(key));
            }
            in.close();
            
            ///保存属性到test.properties文件
            FileOutputStream oFile = new FileOutputStream("src/main/resources/test.properties", true);//true表示追加打开
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    } 
	/**
	 * 
	 * @param fileName src/main/resource下的文件
	 * @param key
	 * @return
	 */
	public static String getProValueByKey(String fileName,String key){
		String value = null;
		try {
			//这种Properties prop = new Properties()的方式只能在main方法里测试
			//若是用spring容器启动得用就像AsDbInfo的方式
			Properties prop = new Properties();    
			InputStream in = ClassLoader.getSystemResourceAsStream(fileName); 
			prop.load(in);
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}     
		return value;
	}
}
