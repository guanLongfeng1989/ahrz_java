package com.cn.riwise.general.service;

import com.cn.riwise.general.pojo.RiwiseMessageObj;
import com.cn.riwise.general.pojo.Sms;

public interface SmsService {

	public RiwiseMessageObj getSmsCode(String valicode);

	public Sms getSmsValicode(String mobile);

	public void insertSmsCode(Sms stsms);

	public Sms getCheckSmsCode(String mobile, String code);

}
