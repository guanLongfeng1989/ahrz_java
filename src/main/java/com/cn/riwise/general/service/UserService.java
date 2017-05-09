package com.cn.riwise.general.service;
import com.cn.riwise.general.pojo.User;

public interface UserService {
	public User selectUserbypwd(String mobile, String passwd);
}
