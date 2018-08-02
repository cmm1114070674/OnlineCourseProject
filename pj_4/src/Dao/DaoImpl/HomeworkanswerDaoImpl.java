package Dao.DaoImpl;

import Bean.HomeworkAnswerBean;
import Bean.HomeworkBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Service.JdbcUtils.getConnected;

public class HomeworkanswerDaoImpl {
    public static void homeworkanswer_insert(HomeworkAnswerBean answerBean){
        Connection connection = null;
        PreparedStatement statement = null;
        String answer = answerBean.getAnswer();
        boolean isDone = answerBean.isDone();
        int done = -1;
        if(isDone)
            done = 1;
        else
            done = 0;
        int homeworkId = answerBean.getHomeworkId();
        int studentId = answerBean.getStudentId();
        try {
            connection = getConnected();
            String sql = "INSERT INTO homeworkanswer(answer, isDone, homeworkId, studentId) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, answer);
            statement.setInt(2, done);
            statement.setInt(3, homeworkId);
            statement.setInt(4, studentId);
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

    public static void homeworkanswer_update(HomeworkAnswerBean homeworkanswer, String answerString){
        Connection connection = null;
        PreparedStatement statement = null;
        int homeworkId = homeworkanswer.getHomeworkId();
        int studentId = homeworkanswer.getStudentId();
        String sql = "update homeworkanswer set answer = ? where homeworkId = ? and studentId = ?";
        try{
            connection = getConnected();
            statement = connection.prepareStatement(sql);
            statement.setString(1, answerString);
            statement.setInt(2, homeworkId);
            statement.setInt(3, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
