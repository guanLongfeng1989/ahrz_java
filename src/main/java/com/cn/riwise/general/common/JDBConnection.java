package com.cn.riwise.general.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * JDBC链接测试
 * @author guanlongfeng
 * @date   2017年5月3日 下午3:02:07
 *
 */
public class JDBConnection {
	public Connection connection = null;
	public JDBConnection() {
		ResourceBundle bundle = ResourceBundle.getBundle("xmlconfig/db");
		String driver = bundle.getString("dbDriver");
		String url = bundle.getString("url");
		String user = bundle.getString("userName");
		String password = bundle.getString("password");
		try {
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (SQLException e) {
		}
	}

}