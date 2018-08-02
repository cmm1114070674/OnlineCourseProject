package Bean;

import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;

public class CourseBean {
private int id;
private String name;
private String introduction;
private byte[] picture;
private String picturePath;
private String pictureRelativePath;
private Date date;
private int studentNumber;

public String getPictureRelativePath() {
	return pictureRelativePath;
}
public void setPictureRelativePath(String pictureRelativePath) {
	this.pictureRelativePath = pictureRelativePath;
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
public String getIntroduction() {
	return introduction;
}
public void setIntroduction(String introduction) {
	this.introduction = introduction;
}
public byte[] getPicture() {
	return picture;
}
public void setPicture(byte[] picture) {
	this.picture = picture;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public int getStudentNumber() {
	return studentNumber;
}
public void setStudentNumber(int studentNumber) {
	this.studentNumber = studentNumber;
}

public String getPicturePath() {
	return picturePath;
}
public void setPicturePath(String picturePath) {
	this.picturePath = picturePath;
}
//�չ��캯�������У������޷�ʹ��DBUtils��new BeanHandler
public CourseBean() {
	
}
public CourseBean(String name, String introduction, byte[] picture, String picturePath, String pictureRelativePath,
		Date date, int studentNumber) {
	super();
	this.name = name;
	this.introduction = introduction;
	this.picture = picture;
	this.picturePath = picturePath;
	this.pictureRelativePath = pictureRelativePath;
	this.date = date;
	this.studentNumber = studentNumber;
}
@Override
public String toString() {
	return "CourseBean [id=" + id + ", name=" + name + ", introduction=" + introduction + ", picture="
			+ Arrays.toString(picture) + ", picturePath=" + picturePath + ", pictureRelativePath=" + pictureRelativePath
			+ ", date=" + date + ", studentNumber=" + studentNumber + "]\n";
}




}
