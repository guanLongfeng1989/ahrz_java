package com.cn.riwise.general.jutest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cn.riwise.general.dao.LocationDao;
import com.cn.riwise.general.dao.SmsDao;
import com.cn.riwise.general.dao.UserDao;
import com.cn.riwise.general.pojo.LocationObj;
import com.cn.riwise.general.pojo.Sms;
import com.cn.riwise.general.pojo.User;
/**
 * 
 * @author guanlongfeng
 * @date   2017年4月28日 下午3:58:56
 *
 */
public class JuTest {
	UserDao userdao;
	SmsDao smsdao;
	LocationDao locationdao;
	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring_mybatis.xml" });

		userdao = context.getBean(UserDao.class);
		smsdao=context.getBean(SmsDao.class);
		locationdao=context.getBean(LocationDao.class);
	}
	//@Test
	public void test1() {
		User user = userdao.selectUserbypwd("13739288457", "123");
		System.out.println(user.getMobile());
	}
	//@Test
	public void test2() {
		//RiwiseMessageObj rmo = smsdao.getSmsCode("rz10001");
		Date now = new Date();
		Date now_10 = new Date(now.getTime() - 600000); //10分钟前的时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String end_time = dateFormat.format(now);
		String start_time = dateFormat.format(now_10);
		Sms sms=new Sms();
		sms.setMobile("13739288457");
		sms.setStarttime("2017-05-05 15:53:32");
		sms.setEndtime("2017-05-05 16:03:32");
		sms.setSMSCode("848607");
		sms=smsdao.getSmsValicode(sms);
		System.out.println(sms);
	/*	Sms stsms=new Sms();
		stsms.setSMSCode("123");
		stsms.setMobile("13739288457");
		smsdao.insertSmsCode(stsms);*/
		
	}
	@Test
	public void test3() {
		LocationObj location=new LocationObj();
		location.setPid(0);
		location.setName("bj");
		List<LocationObj> ll = locationdao.getLocationObj(location);
		System.out.println(ll.size());
	}
}
