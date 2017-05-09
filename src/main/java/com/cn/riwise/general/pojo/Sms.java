package com.cn.riwise.general.pojo;

import java.util.Date;

public class Sms {
  private int id;
  private String Mobile;
  private String SMSCode;
  private Date TimeUpdate;
  private String Starttime;
  private String Endtime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSMSCode() {
	return SMSCode;
}
public void setSMSCode(String sMSCode) {
	SMSCode = sMSCode;
}
public Date getTimeUpdate() {
	return TimeUpdate;
}
public void setTimeUpdate(Date timeUpdate) {
	TimeUpdate = timeUpdate;
}
public String getMobile() {
	return Mobile;
}
public void setMobile(String mobile) {
	Mobile = mobile;
}
public String getEndtime() {
	return Endtime;
}
public void setEndtime(String endtime) {
	Endtime = endtime;
}
public String getStarttime() {
	return Starttime;
}
public void setStarttime(String starttime) {
	Starttime = starttime;
}
}
