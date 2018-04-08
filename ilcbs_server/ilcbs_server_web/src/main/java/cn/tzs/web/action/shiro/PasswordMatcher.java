package cn.tzs.web.action.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        System.out.println("进入了密码比较器");

        // 获取用户登录时的密码
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        String password = new String(uToken.getPassword());

        // 将密码转译为MD5编码
        // 参数：source：要转换的字符  salt：要混淆的字段  hashIterations：打乱的次序
        Md5Hash md5Pwd = new Md5Hash(password, uToken.getUsername(), 2);
        System.out.println(md5Pwd);// 输出查看一下

        // 获取认证时存入的用户数据
        Object uInfo = info.getCredentials();

        return equals(uInfo, md5Pwd.toString());//注意类型格式转换
    }
}
