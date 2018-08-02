package Bean;

import java.util.Date;

public class UserBean {
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String activeCode;
    private int activeFlag;
    private Date registerTime;

    public UserBean(){

    }

    public UserBean(int id, String name, String password, String email, String phone, String activeCode, int activeFlag, Date registerTime){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.activeCode = activeCode;
        this.activeFlag = activeFlag;
        this.registerTime = registerTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", activeCode=" + activeCode + ", activeFlag=" + activeFlag + ", registerTime=" + registerTime
				+ "]\n";
	}
    
    
}
