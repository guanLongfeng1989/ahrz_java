package com.cn.riwise.general.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.riwise.general.common.BaseResponseData;
import com.cn.riwise.general.common.WebResponseCode;
import com.cn.riwise.general.pojo.LocationObj;
import com.cn.riwise.general.service.LocationService;
/**
 * 地址模板
 * @author guanlongfeng
 * @date   2017年5月5日 下午5:43:43
 *
 */
@Controller
@RequestMapping("/Location")
public class LocationController {
	@Resource
	private LocationService Localtionservice;
	@RequestMapping("/getLocation")
	@ResponseBody
	public BaseResponseData getLocationObj(@RequestParam("locationname") String locationname,@RequestParam("pid") int pid) {
		BaseResponseData data = new BaseResponseData();
		try{  
			LocationObj location=new LocationObj();
			location.setName(locationname);
			location.setPid(pid);
			List<LocationObj> locationlist=Localtionservice.getLocationObj(location);
			data.setCode(WebResponseCode.SUCCESS);
			data.setMessage("获取成功！");
			HashMap<String, Object> resData = new HashMap<String, Object>();
			resData.put("locationlist", locationlist);
			data.setResponseData(resData);
			return data;
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
