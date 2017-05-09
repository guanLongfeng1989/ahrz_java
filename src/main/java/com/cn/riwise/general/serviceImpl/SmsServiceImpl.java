package com.cn.riwise.general.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.riwise.general.dao.SmsDao;
import com.cn.riwise.general.pojo.RiwiseMessageObj;
import com.cn.riwise.general.pojo.Sms;
import com.cn.riwise.general.service.SmsService;
@Service("SmsServiceImpl")
public class SmsServiceImpl implements SmsService{
    
	@Resource
	private SmsDao smsdao;
	
	public RiwiseMessageObj getSmsCode(String valicode) {
		// TODO Auto-generated method stub
		return smsdao.getSmsCode(valicode);
	}

	public Sms getSmsValicode(String mobile) {
		// TODO Auto-generated method stub
		Date now = new Date();
		Date now_10 = new Date(now.getTime() - 600000); //10分钟前的时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String end_time = dateFormat.format(now);
		String start_time = dateFormat.format(now_10);
		Sms sms=new Sms();
		sms.setMobile(mobile);
		sms.setStarttime(start_time);
		sms.setEndtime(end_time);
		return smsdao.getSmsValicode(sms);
	}

	public void insertSmsCode(Sms stsms) {
		// TODO Auto-generated method stub
		smsdao.insertSmsCode(stsms);
		
	}

	public Sms getCheckSmsCode(String mobile, String code) {
		Date now = new Date();
		Date now_10 = new Date(now.getTime() - 600000); //10分钟前的时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String end_time = dateFormat.format(now);
		String start_time = dateFormat.format(now_10);
		Sms sms=new Sms();
		sms.setMobile(mobile);
		sms.setStarttime(start_time);
		sms.setEndtime(end_time);
		sms.setSMSCode(code);
	    sms= smsdao.getSmsValicode(sms);
	    return sms;
	}


}
