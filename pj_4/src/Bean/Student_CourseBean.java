package Bean;

public class Student_CourseBean {
    private int studentId;
    private int courseId;

    public Student_CourseBean(int studentId, int courseId){
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Student_CourseBean(){

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}

