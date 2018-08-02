package Service;

import Bean.HomeworkAnswerBean;
import Bean.HomeworkBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Service.JdbcUtils.getConnected;

public class HomeworkAnswerGet {
    public static ArrayList<HomeworkAnswerBean> getHomeworkAnswerListAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<HomeworkAnswerBean> homeworkAnswerBeans = new ArrayList<>();
        try{
            connection = getConnected();
            String sql = "SELECT answerId, answer, isDone, homeworkId, studentId FROM homeworkanswer";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int answerId = rs.getInt("answerId");
                String answer = rs.getString("answer");
                boolean isDone;
                if(rs.getInt("isDone") == 1)
                    isDone = true;
                else
                    isDone = false;
                int homeworkId = rs.getInt("homeworkId");
                int studentId = rs.getInt("studentId");
                homeworkAnswerBeans.add(new HomeworkAnswerBean(answerId, answer, isDone, homeworkId, studentId));
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
        return homeworkAnswerBeans;
    }

    public static HomeworkAnswerBean getHomeworkAnswer(int homeworkId, int studentId){
        ArrayList<HomeworkAnswerBean> homeworkAnswerBeansAll = getHomeworkAnswerListAll();
        HomeworkAnswerBean homeworkAnswerBean = new HomeworkAnswerBean();
        for(HomeworkAnswerBean i:homeworkAnswerBeansAll){
            if(i.getHomeworkId() == homeworkId && i.getStudentId() == studentId)
                homeworkAnswerBean = new HomeworkAnswerBean(i.getAnswerId(), i.getAnswer(), i.isDone(), i.getHomeworkId(), i.getStudentId());
        }
        return homeworkAnswerBean;
    }

    public static ArrayList<HomeworkAnswerBean> getHomeworkAnswerListByStudentId(int studentId){
        ArrayList<HomeworkAnswerBean> homeworkAnswerBeansAll = getHomeworkAnswerListAll();
        HomeworkAnswerBean homeworkAnswerBean = new HomeworkAnswerBean();
        ArrayList<HomeworkAnswerBean> homeworkAnswerBeans = new ArrayList<>();
        for(HomeworkAnswerBean i:homeworkAnswerBeansAll){
            if(i.getStudentId() == studentId) {
                homeworkAnswerBean = new HomeworkAnswerBean(i.getAnswerId(), i.getAnswer(), i.isDone(), i.getHomeworkId(), i.getStudentId());
                homeworkAnswerBeans.add(homeworkAnswerBean);
            }
        }
        return homeworkAnswerBeans;
    }

    public static ArrayList<HomeworkAnswerBean> getHomeworkAnswerListByHomeworkId(int homeworkId){
        ArrayList<HomeworkAnswerBean> homeworkAnswerBeansAll = getHomeworkAnswerListAll();
        HomeworkAnswerBean homeworkAnswerBean = new HomeworkAnswerBean();
        ArrayList<HomeworkAnswerBean> homeworkAnswerBeans = new ArrayList<>();
        for(HomeworkAnswerBean i:homeworkAnswerBeansAll){
            if(i.getHomeworkId() == homeworkId) {
                homeworkAnswerBean = new HomeworkAnswerBean(i.getAnswerId(), i.getAnswer(), i.isDone(), i.getHomeworkId(), i.getStudentId());
                homeworkAnswerBeans.add(homeworkAnswerBean);
            }
        }
        return homeworkAnswerBeans;
    }


}
