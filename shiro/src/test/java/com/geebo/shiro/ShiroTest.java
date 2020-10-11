package com.geebo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 5:49 2020/10/11
 */
public class ShiroTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        // 认证
        simpleAccountRealm.addAccount("aaa", "123");
        // 授权
        simpleAccountRealm.addAccount("bbb", "123", "admin", "user");
    }

    /**
     * 创建securitymanager环境
     * 主体提交认证
     * securitymanager认证
     * authenticator认证
     * realm验证
     */
    @Test
    public void testAuth() {
        //1.构建securitymanager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2.主体提交认证主题
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("aaa", "123", "1");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("aaa", "123");
        subject.login(usernamePasswordToken);

        System.out.println("auth:" + subject.isAuthenticated());

        subject.logout();
    }

    /**
     * 创建securitymanager环境
     * 主体提交授权
     * securitymanager授权
     * authorizer授权
     * realm获取角色权限数据
     */
    @Test
    public void testAuthorization() {
        //1.构建securitymanager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2.主体提交认证主题
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("bbb", "123");
        subject.login(usernamePasswordToken);

        System.out.println("auth:" + subject.isAuthenticated());
        subject.checkRoles("user", "admin");
    }

}
