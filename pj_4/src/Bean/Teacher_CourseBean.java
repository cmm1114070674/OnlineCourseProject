package Bean;

public class Teacher_CourseBean {
    private int teacherId;
    private int courseId;

    public Teacher_CourseBean(int teacherId, int courseId){
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public Teacher_CourseBean(){

    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
