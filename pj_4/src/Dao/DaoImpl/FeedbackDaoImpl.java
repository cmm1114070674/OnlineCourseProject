package Dao.DaoImpl;

import Bean.FeedbackBean;
import Bean.HomeworkAnswerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Service.JdbcUtils.getConnected;

public class FeedbackDaoImpl {
    public static void feedback_insert(FeedbackBean feedbackBean){
        Connection connection = null;
        PreparedStatement statement = null;
        String feedback = feedbackBean.getFeedback();
        int score = feedbackBean.getScore();
        boolean isCorrected = feedbackBean.isCorrected();
        int corrected = -1;
        if(isCorrected)
            corrected = 1;
        else
            corrected = 0;
        int answerId = feedbackBean.getAnswerId();
        int homeworkId = feedbackBean.getHomeworkId();
        int studentId = feedbackBean.getStudentId();
        try {
            connection = getConnected();
            String sql = "INSERT INTO feedback(feedback, score, isCorrected, answerId, homeworkId, studentId) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, feedback);
            statement.setInt(2, score);
            statement.setInt(3, corrected);
            statement.setInt(4, answerId);
            statement.setInt(5, homeworkId);
            statement.setInt(6, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
    }

    public static void feedback_update(FeedbackBean feedbackBean, String feedback, int score){
        Connection connection = null;
        PreparedStatement statement = null;
        int feedbackId = feedbackBean.getFeedbackId();
        String sql = "update feedback set feedback = ?, score = ? where feedbackId = ?";
        try{
            connection = getConnected();
            statement = connection.prepareStatement(sql);
            statement.setString(1, feedback);
            statement.setInt(2, score);
            statement.setInt(3, feedbackId);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
