package Bean;

import java.sql.Blob;

public class HomepageCourseBean {
	private String name;
	private String introduction;
//	private Blob picture;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
//	public Blob getPicture() {
//		return picture;
//	}
//	public void setPicture(Blob picture) {
//		this.picture = picture;
//	}
	public HomepageCourseBean(String name, String introduction) {
	
		this.name = name;
		this.introduction = introduction;
//		this.picture = picture;
	}
	@Override
	public String toString() {
		return "HomepageCourseBean [name=" + name + ", introduction=" + introduction + ", picture="  + "]";
	}
	
	
}
