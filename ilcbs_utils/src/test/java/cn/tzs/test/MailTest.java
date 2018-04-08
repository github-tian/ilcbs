package cn.tzs.test;

import cn.tzs.utils.MailUtil;

public class MailTest {

    public static void main(String[] args) throws Exception {
        MailUtil.sendMsg("15538871366@163.com","我自己给的邮件","这是普通发送的邮件的邮件内容，请注意查收");
    }
}
