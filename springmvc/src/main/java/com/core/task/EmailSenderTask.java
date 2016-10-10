package com.core.task;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送的定时任务
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年10月12日 下午4:45:24
 * @version 1.0
 */
public class EmailSenderTask {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// [秒] [分] [小时] [日] [月] [周] [年]
	@Scheduled(cron="0 0 12 * * ?")
	//@Scheduled(cron="0/10 * 11 * * ?")
	public void sendEmail() throws Exception {
		
		System.out.println("========163邮箱=========" + System.currentTimeMillis()+ "," +sdf.format(new Date()));
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		// 设置邮件服务器
		sender.setHost("smtp.163.com");
		// 用户名
		sender.setUsername("13632370739@163.com");
		// 密码
		sender.setPassword("qwer1000");
		// 邮件内容的编码
		sender.setDefaultEncoding("utf-8");
		
		/** 发送邮件需要鉴权 */
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", true);
		sender.setJavaMailProperties(javaMailProperties);
		
		/** 创建邮件消息体 */
		MimeMessage message = sender.createMimeMessage();
		/** 创建邮件消息体的帮助类 */
		MimeMessageHelper helper = new MimeMessageHelper(message);
		/** 设置主题 */
		helper.setSubject("我的邮件");
		/** 发件人 */
		helper.setFrom("13632370739@163.com");
		/** 收件人 */
		helper.setTo("919711403@qq.com");
		/** 邮件内容  true : html格式的邮件  false: 文本格式的 */
		helper.setText("<h1>163邮箱发送,日期"+ sdf.format(new Date()) +"</h1>", true);
		
		sender.send(message);
		
	}
}
