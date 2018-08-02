package Bean;

import java.util.Date;

public class RegisterBean {
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private String username;
private String password;
private String email;
private String phone;
private String activeCode;
private int activeFlag;
private java.sql.Date registerTime; 
public String getActiveCode() {
	return activeCode;
}
public void setActiveCode(String activeCode) {
	this.activeCode = activeCode;
}
public int getActiveFlag() {
	return activeFlag;
}
public void setActiveFlag(int activeFlag) {
	this.activeFlag = activeFlag;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public RegisterBean() {
	
}
public Date getRegisterTime() {
	return registerTime;
}
public void setRegisterTime(java.sql.Date registerTime) {
	this.registerTime = registerTime;
}
public RegisterBean(String username, String password, String email, String phone, String activeCode, int activeFlag,
		java.sql.Date registerTime) {
	super();
	this.username = username;
	this.password = password;
	this.email = email;
	this.phone = phone;
	this.activeCode = activeCode;
	this.activeFlag = activeFlag;
	this.registerTime = registerTime;
}
@Override
public String toString() {
	return "RegisterBean [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
			+ ", phone=" + phone + ", activeCode=" + activeCode + ", activeFlag=" + activeFlag + ", registerTime="
			+ registerTime + "]\n";
}




}
