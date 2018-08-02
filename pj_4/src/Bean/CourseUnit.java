package Bean;

public class CourseUnit {
private int courseUnitId;
private int courseId;
private String courseUnitName;
private int courseUnitIndex;
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public String getCourseUnitName() {
	return courseUnitName;
}
public void setCourseUnitName(String courseUnitName) {
	this.courseUnitName = courseUnitName;
}

public int getCourseUnitIndex() {
	return courseUnitIndex;
}
public void setCourseUnitIndex(int courseUnitIndex) {
	this.courseUnitIndex = courseUnitIndex;
}


public CourseUnit() {
	
}
public int getCourseUnitId() {
	return courseUnitId;
}
public void setCourseUnitId(int courseUnitId) {
	this.courseUnitId = courseUnitId;
}
public CourseUnit(int courseId, String courseUnitName, int courseUnitIndex) {
	this.courseId = courseId;
	this.courseUnitName = courseUnitName;
	this.courseUnitIndex = courseUnitIndex;
}
@Override
public String toString() {
	return "CourseUnit [courseUnitId=" + courseUnitId + ", courseId=" + courseId + ", courseUnitName=" + courseUnitName
			+ ", courseUnitIndex=" + courseUnitIndex + "]\n";
}



}
