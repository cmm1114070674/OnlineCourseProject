package Dao.DaoImpl;

import Bean.HomeworkBean;

import java.sql.*;
import java.util.ArrayList;

import static Service.JdbcUtils.getConnected;

public class HomeworkDaoImpl {
    public static void homework_insert(HomeworkBean homework){
        Connection connection = null;
        PreparedStatement statement = null;
        String name = homework.getHomeworkname();
        String question = homework.getQuestion();
        int courseId = homework.getCourseId();
        try {
            connection = getConnected();
            String sql = "INSERT INTO homework(homeworkname, question, courseId) VALUES(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, question);
            statement.setInt(3, courseId);
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

    public static void homework_update(HomeworkBean homework){
        Connection connection = null;
        PreparedStatement statement = null;
        String name = homework.getHomeworkname();
        String question = homework.getQuestion();
        int homeworkId = homework.getHomeworkId();
        String sql = "update homework set homeworkname = ?, question = ? where homeworkId = ?";
        try{
            connection = getConnected();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, question);
            statement.setInt(3, homeworkId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
