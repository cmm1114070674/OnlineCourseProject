package Bean;

public class HomeworkBean {
    private int homeworkId;
    private String homeworkname;
    private String question;
    private int courseId;

    public HomeworkBean(int homeworkId, String homeworkname, String question, int courseId){
        this.homeworkId = homeworkId;
        this.homeworkname = homeworkname;
        this.question = question;
        this.courseId = courseId;
    }

    public HomeworkBean(){

    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkname(){
        return homeworkname;
    }

    public void setHomeworkname(String homeworkname){
        this.homeworkname = homeworkname;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId;
    }

}
