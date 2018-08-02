package Bean;

public class FeedbackBean {
    private int feedbackId;
    private String feedback;
    private int score = -1;
    private boolean isCorrected = false;
    private int answerId;
    private int homeworkId;
    private int studentId;

    public FeedbackBean(int feedbackId, String feedback, int score, boolean isCorrected, int answerId, int homeworkId, int studentId){
        this.feedbackId = feedbackId;
        this.feedback = feedback;
        this.score = score;
        this.isCorrected = isCorrected;
        this.answerId = answerId;
        this.homeworkId = homeworkId;
        this.studentId = studentId;
    }

    public FeedbackBean(){

    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCorrected() {
        return isCorrected;
    }

    public void setCorrected(boolean corrected) {
        isCorrected = corrected;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
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
