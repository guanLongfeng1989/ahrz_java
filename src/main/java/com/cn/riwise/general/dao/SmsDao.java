package com.cn.riwise.general.dao;

import java.util.Date;

import com.cn.riwise.general.pojo.RiwiseMessageObj;
import com.cn.riwise.general.pojo.Sms;

public interface SmsDao {
    /**
     * 获取短信平台模板的SMS_CODE
     * @param valicode
     * @return
     */
	public RiwiseMessageObj getSmsCode(String valicode) ;
    /**
     * 获取10分中内有效验证骂
     * @param mobile
     * @return
     */
	public Sms getSmsValicode(Sms sms);
	/**
	 * 插入验证码
	 * @param stsms
	 */
	public void insertSmsCode(Sms stsms);
}
