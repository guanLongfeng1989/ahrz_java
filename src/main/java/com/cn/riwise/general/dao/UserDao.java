package com.cn.riwise.general.dao;

import org.apache.ibatis.annotations.Param;

import com.cn.riwise.general.pojo.User;

public interface UserDao {
	
	public User selectUserbypwd(@Param(value="mobile")String mobile,@Param(value="passwd") String passwd);
}
