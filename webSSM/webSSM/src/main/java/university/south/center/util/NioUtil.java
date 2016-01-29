package university.south.center.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.log4j.Logger;

public class NioUtil {
	
	private static Logger logger = Logger.getLogger(NioUtil.class);
	
	public static void copyFile(String origin,String target){
		    long beginTime = System.currentTimeMillis();
		    FileInputStream inf = null;
		    FileOutputStream outf = null;
		    FileChannel inFc = null;
		    FileChannel outFc = null;
		    try {
				 inf = new FileInputStream(new File(origin));
				 outf = new FileOutputStream(new File(target));
				 ByteBuffer buffer = ByteBuffer.allocate(1024); 
				 inFc = inf.getChannel();  
				 outFc = outf.getChannel();  
				 Charset charSet = Charset.forName("utf-8");  
				 CharsetDecoder decoder = charSet.newDecoder();  
				 CharsetEncoder encoder = charSet.newEncoder(); 
			     while(true) { 
			    	//读取源文件内容存到buff中
	                buffer.clear();                          
	                CharBuffer cb = decoder.decode(buffer);  
	                ByteBuffer bb = encoder.encode(cb);  
                    int t = inFc.read(bb);  
                    if(t == -1) {  
                        break;  
                    }  
                    //读取buff中的内容存到目标文件中
                    bb.flip();  
                    outFc.write(bb); 
                } 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (CharacterCodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(inFc!=null){
						inFc.close();
					}
					if(outFc!=null){
						outFc.close();
					}
					if(inf!=null){
						inf.close();
					}
					if(outf!=null){
						outf.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				long endTime = System.currentTimeMillis();
			    logger.info("采用NIO复制文件耗时：" + (endTime - beginTime)+"ms");
			} 
		   
	}
	
		 
	public static void main(String[] args) {
		NioUtil.copyFile("E:/hello.txt", "E:/helloCopyed.txt");
	}


}