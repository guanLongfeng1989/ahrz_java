package com.cn.riwise.general.pojo;

import java.util.HashMap;

/**
 * 短信实例
 * 
 * @author guanlongfeng
 * @date 2017年5月5日 上午10:14:07
 * 
 */
public class RiwiseMessageObj {
	private int id;
	private String tel;
	private String mycode;
	private String alicode;
	@SuppressWarnings("rawtypes")
	private HashMap paramemap;
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public HashMap getParamemap() {
		return paramemap;
	}

	public void setParamemap(HashMap paramemap) {
		this.paramemap = paramemap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMycode() {
		return mycode;
	}

	public void setMycode(String mycode) {
		this.mycode = mycode;
	}

	public String getAlicode() {
		return alicode;
	}

	public void setAlicode(String alicode) {
		this.alicode = alicode;
	}
}
