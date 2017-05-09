package com.cn.riwise.general.pojo;

public class User {
private int id;
private String Mobile;
private String Name;
private  String Password;
private  int AccountLevel;
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getPassword() {
	return Password;
}

public void setPassword(String password) {
	Password = password;
}

public String getMobile() {
	return Mobile;
}

public void setMobile(String mobile) {
	Mobile = mobile;
}

public String getName() {
	return Name;
}

public void setName(String name) {
	Name = name;
}

public int getAccountLevel() {
	return AccountLevel;
}

public void setAccountLevel(int accountLevel) {
	AccountLevel = accountLevel;
}

}
