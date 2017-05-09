package com.cn.riwise.general.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.riwise.general.dao.UserDao;
import com.cn.riwise.general.pojo.User;
import com.cn.riwise.general.service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userdao;
	
	public User selectUserbypwd(String mobile, String passwd) {
		// TODO Auto-generated method stub
		return userdao.selectUserbypwd(mobile,passwd);
	}
}
