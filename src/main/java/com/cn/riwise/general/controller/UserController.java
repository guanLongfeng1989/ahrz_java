package com.cn.riwise.general.controller;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.riwise.general.common.BaseResponseData;
import com.cn.riwise.general.common.WebResponseCode;
import com.cn.riwise.general.pojo.User;
import com.cn.riwise.general.service.UserService;
@Controller
@RequestMapping("/User")
public class UserController {
	@Resource
	private  UserService userService;
	/**
	 * 用戶登陸
	 * @param mobile
	 * @param passwd
	 * @param c
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public BaseResponseData login(@RequestParam("mobile") String mobile , @RequestParam("passwd") String passwd,HttpServletRequest c) {
		BaseResponseData data = new BaseResponseData();
		try{
			User user = userService.selectUserbypwd(mobile,passwd);
			if(user!=null){
				data.setCode(WebResponseCode.SUCCESS);
				data.setMessage("获取成功！");
				HashMap<String, Object> resData = new HashMap<String, Object>();
				resData.put("user", user);
				data.setResponseData(resData);
				return data;
			}else{
				data.setCode(WebResponseCode.ERROR);
				data.setMessage("用户名密码错误！");
				HashMap<String, Object> resData = new HashMap<String, Object>();
				resData.put("user", user);
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
