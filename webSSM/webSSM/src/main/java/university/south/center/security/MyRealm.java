package university.south.center.security;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import university.south.center.admin.entity.SysUser;
import university.south.center.component.AsDbInfo;

public class MyRealm extends AuthorizingRealm {  
	
	
	@Autowired
	private AsDbInfo asDbInfo;
    /** 
     * 为当前登录的Subject授予角色和权限 
     * @see  经测试:本例中该方法的调用时机为需授权资源被访问时 
     * @see  经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache 
     * @see  个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache 
     * @see  比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){  
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()  
        String currentUsername = (String)super.getAvailablePrincipal(principals);  
      /*List<String> roleList = new ArrayList<String>();  
      List<String> permissionList = new ArrayList<String>();  
      //从数据库中获取当前登录用户的详细信息  
      User user = userService.getByUsername(currentUsername);  
      if(null != user){  
          //实体类User中包含有用户角色的实体类信息  
          if(null!=user.getRoles() && user.getRoles().size()>0){  
              //获取当前登录用户的角色  
              for(Role role : user.getRoles()){  
                  roleList.add(role.getName());  
                  //实体类Role中包含有角色权限的实体类信息  
                  if(null!=role.getPermissions() && role.getPermissions().size()>0){  
                      //获取权限  
                      for(Permission pmss : role.getPermissions()){  
                          if(!StringUtils.isEmpty(pmss.getPermission())){  
                              permissionList.add(pmss.getPermission());  
                          }  
                      }  
                  }  
              }  
          }  
          //为当前用户设置角色和权限  
	      SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
	      simpleAuthorInfo.addRoles(roleList);  
	      simpleAuthorInfo.addStringPermissions(permissionList); 
	      return simpleAuthorInfo;
      }else{  
          return null;
      }  
     */ 
      //实际中可能会像上面注释的那样从数据库取得 ,为了演示直接从asdb.properties里取
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        List<String> roleList =  new ArrayList<String>();  
        List<String> permissionList = new ArrayList<String>();  
        if(null!=currentUsername && "dwj".equals(currentUsername)){  
        	  roleList.add(asDbInfo.getRole());
              permissionList.add(asDbInfo.getPermission());
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色    
        	 simpleAuthorInfo.addRoles(roleList);  
            //添加权限  
        	 simpleAuthorInfo.addStringPermissions(permissionList); 
            System.out.println("已为用户[dwj]赋予了[admin]角色和[admin:XXX]权限");  
            return simpleAuthorInfo;  
        }else{
        	//若该方法什么都不做直接返回null的话,就会导致任何用户访问无授权页面时都会自动跳转到unauthorizedUrl指定的地址  
            //详见spring-shiro.xml中的<bean id="shiroFilter">的配置  
            return null;  
        }
        
    }  
   
       
    /** 
     * 验证当前登录的Subject 
     * @see  经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
        //获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;  
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
	     /* User user = userService.getByUsername(token.getUsername());  
	      if(null != user){  
	          AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());  
	          this.setSession("currentUser", user);  
	          return authcInfo;  
	      }else{  
	          return null;  
	      }  */
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
        //将数据库中查出的数据放在AuthenticationInfo里返回。本例中为了演示就从asdb.properties中取)  
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证  
        String userName = asDbInfo.getName();
        String password = asDbInfo.getPassword();
        //User user = new User();
        SysUser user = new SysUser();
        user.setUserName(userName);
        user.setPassword(password);//封装一个user,本来应该从数据库中查出
        if(user!=null){  
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), this.getName());  
            this.setSession("currentUser", user);  
            return authcInfo;  
        }
        //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常  
        return null;  
    }  
       
       
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }  
}