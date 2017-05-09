package com.cn.riwise.general.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.mns.model.TopicMessage;
import com.cn.riwise.general.common.BaseResponseData;
import com.cn.riwise.general.common.RiwiseMessage;
import com.cn.riwise.general.common.SendMessageSystem;
import com.cn.riwise.general.common.WebResponseCode;
import com.cn.riwise.general.pojo.RiwiseMessageObj;
import com.cn.riwise.general.pojo.Sms;
import com.cn.riwise.general.service.SmsService;

/**
 * 短信模板
 * @author guanlongfeng
 * @date   2017年5月4日 下午4:18:17
 *
 */
@Controller
@RequestMapping("/Sms")
public class SmsController {
	@Resource
	private SmsService smsservice;
	/**
	 * 店铺管理的基本设置的订单验证码
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/OrderSms")
	@ResponseBody
	public BaseResponseData sendOrderSms(@RequestParam("mobile") String mobile) {
		BaseResponseData data = new BaseResponseData();
		try{  
			 //获取阿里云对应的code;
			RiwiseMessageObj rmo = smsservice.getSmsCode(RiwiseMessage.ValiCode);
			if(rmo!=null){
				//查询该手机验证吗是否有效  .10分钟之内有效。
				Sms sms=smsservice.getSmsValicode(mobile);
				rmo.setTel(mobile);
				HashMap hmap=new HashMap();
				//随机6位数
				String valicoide="";
				if(sms!=null){
					valicoide=sms.getSMSCode();
				}else{
				    valicoide=(int)((Math.random()*9+1)*100000)+"";
				}
				hmap.put(RiwiseMessage.ValiParame, valicoide);
				rmo.setParamemap(hmap);
				TopicMessage topm=SendMessageSystem.SendMessageOP(rmo);
				if(topm!=null){
					//成功后把验证码插入数据库，用于验证.
					if(sms==null){
						Sms stsms=new Sms();
						stsms.setSMSCode(valicoide+"");
						stsms.setMobile(mobile);
						smsservice.insertSmsCode(stsms);
					}
					data.setCode(WebResponseCode.SUCCESS);
					data.setMessage("短信发送成功");
					HashMap<String, Object> resData = new HashMap<String, Object>();
					resData.put("rmo", rmo);
					data.setResponseData(resData);
					return data;
			     }else{
			    	    data.setCode(WebResponseCode.ERROR);
						data.setMessage("短信发送失败！");
						HashMap<String, Object> resData = new HashMap<String, Object>();
						resData.put("rmo", rmo);
						data.setResponseData(resData);
						return data;
				 }
				
			}else{
				data.setCode(WebResponseCode.ERROR);
				data.setMessage("没有对应CODE");
				HashMap<String, Object> resData = new HashMap<String, Object>();
				resData.put("code", RiwiseMessage.ValiCode);
				data.setResponseData(resData);
				return data;
			}
		}catch(Exception e){
			data.setCode(WebResponseCode.ERROR);
			data.setMessage("数据获取失败！");
			HashMap<String, Object> resData = new HashMap<String, Object>();
			resData.put("errorcode", WebResponseCode.EXECUTIONERROR);
			resData.put("errormessage", WebResponseCode.EXECUTIONERRORMESSAGE);
			data.setResponseData(resData);
			return data;
		}
	}
 
	/**
	 * 短信验证
	 * @param mobile
	 * param code
	 * @return
	 */
	@RequestMapping("/getCheckSmsCode")
	@ResponseBody
	public BaseResponseData getCheckSmsCode(@RequestParam("mobile") String mobile,@RequestParam("code") String code) {
		BaseResponseData data = new BaseResponseData();
		try{  
			 //查询是否存在10分钟之内的短信验证码
			Sms sms= smsservice.getCheckSmsCode(mobile,code);
			if(sms!=null){
					data.setCode(WebResponseCode.SUCCESS);
					data.setMessage("验证码正确！");
					HashMap<String, Object> resData = new HashMap<String, Object>();
					resData.put("sms", sms);
					data.setResponseData(resData);
					return data;
			}else{
				data.setCode(WebResponseCode.ERROR);
				data.setMessage("验证码失败");
				HashMap<String, Object> resData = new HashMap<String, Object>();
				resData.put("sms", sms);
				data.setResponseData(resData);
				return data;
			}
		}catch(Exception e){
			data.setCode(WebResponseCode.ERROR);
			data.setMessage("数据获取失败！");
			HashMap<String, Object> resData = new HashMap<String, Object>();
			resData.put("errorcode", WebResponseCode.EXECUTIONERROR);
			resData.put("errormessage", WebResponseCode.EXECUTIONERRORMESSAGE);
			data.setResponseData(resData);
			return data;
		}
	}
}
