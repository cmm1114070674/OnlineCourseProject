package Service;

import Bean.FeedbackBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Service.JdbcUtils.getConnected;

public class FeedbackGet {
    public static ArrayList<FeedbackBean> getFeedbackListAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<FeedbackBean> feedbackBeans = new ArrayList<>();
        try{
            connection = getConnected();
            String sql = "SELECT feedbackId, feedback, score, isCorrected, answerId, homeworkId, studentId FROM feedback";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int feedbackId = rs.getInt("feedbackId");
                String feedback = rs.getString("feedback");
                int score = rs.getInt("score");
                boolean isCorrected;
                if(rs.getInt("isCorrected") == 1)
                    isCorrected = true;
                else
                    isCorrected = false;
                int answerId = rs.getInt("answerId");
                int homeworkId = rs.getInt("homeworkId");
                int studentId = rs.getInt("studentId");
                feedbackBeans.add(new FeedbackBean(feedbackId, feedback, score, isCorrected, answerId, homeworkId, studentId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return feedbackBeans;
    }

    public static ArrayList<FeedbackBean> getFeedbackListByHomeworkId(int homeworkId){
        ArrayList<FeedbackBean> feedbackBeansAll = getFeedbackListAll();
        ArrayList<FeedbackBean> feedbackBeans = new ArrayList<>();
        FeedbackBean feedbackBean = new FeedbackBean();
        for(FeedbackBean i:feedbackBeansAll){
            if(i.getHomeworkId() == homeworkId){
                feedbackBean = new FeedbackBean(i.getFeedbackId(), i.getFeedback(), i.getScore(), i.isCorrected(), i.getAnswerId(), i.getHomeworkId(),i.getStudentId());
                feedbackBeans.add(feedbackBean);
            }
        }
        return feedbackBeans;
    }

    public static ArrayList<FeedbackBean> getFeedbackListByStudentId(int studentId){
        ArrayList<FeedbackBean> feedbackBeansAll = getFeedbackListAll();
        ArrayList<FeedbackBean> feedbackBeans = new ArrayList<>();
        FeedbackBean feedbackBean = new FeedbackBean();
        for(FeedbackBean i:feedbackBeansAll){
            if(i.getStudentId() == studentId){
                feedbackBean = new FeedbackBean(i.getFeedbackId(), i.getFeedback(), i.getScore(), i.isCorrected(),
                        i.getAnswerId(), i.getHomeworkId(),i.getStudentId());
                feedbackBeans.add(feedbackBean);
            }
        }
        return feedbackBeans;
    }

    public static FeedbackBean getFeedbackByAnswerId(int answerId){
        ArrayList<FeedbackBean> feedbackBeansAll = getFeedbackListAll();
        FeedbackBean feedbackBean = new FeedbackBean();
        for(FeedbackBean i:feedbackBeansAll){
            if(i.getAnswerId() == answerId){
                feedbackBean = new FeedbackBean(i.getFeedbackId(), i.getFeedback(), i.getScore(), i.isCorrected(),
                        i.getAnswerId(), i.getHomeworkId(),i.getStudentId());
            }
        }
        return feedbackBean;
    }

    public static FeedbackBean getFeedbackByHomeworkIdAndStudentId(int homeworkId, int studentId){
        ArrayList<FeedbackBean> feedbackBeansAll = getFeedbackListAll();
        FeedbackBean feedbackBean = new FeedbackBean();
        for(FeedbackBean i:feedbackBeansAll){
            if(i.getHomeworkId() == homeworkId && i.getStudentId() == studentId){
                feedbackBean = new FeedbackBean(i.getFeedbackId(), i.getFeedback(), i.getScore(), i.isCorrected(),
                        i.getAnswerId(), i.getHomeworkId(),i.getStudentId());
            }
        }
        return feedbackBean;
    }

}
