package Bean;

public class HomeworkAnswerBean {
    private int answerId;
    private String answer;
    private boolean isDone = false;
    private int homeworkId;
    private int studentId;

    public HomeworkAnswerBean(int answerId, String answer, boolean isDone, int homeworkId, int studentId){
        this.answerId = answerId;
        this.answer = answer;
        this.isDone = isDone;
        this.homeworkId = homeworkId;
        this.studentId = studentId;
    }

    public HomeworkAnswerBean(){

    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
