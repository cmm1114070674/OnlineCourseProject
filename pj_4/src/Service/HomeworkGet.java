package Service;

import Bean.HomeworkBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Service.JdbcUtils.getConnected;

public class HomeworkGet {
    public static ArrayList<HomeworkBean> getHomeworkListAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<HomeworkBean> homeworkBeans = new ArrayList<>();
        try{
            connection = getConnected();
            String sql = "SELECT homeworkId, homeworkname, question, courseId FROM homework";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int homeworkId = rs.getInt("homeworkId");
                String homeworkname = rs.getString("homeworkname");
                String question = rs.getString("question");
                int courseId = rs.getInt("courseId");
                homeworkBeans.add(new HomeworkBean(homeworkId, homeworkname, question, courseId));
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
        return homeworkBeans;
    }

    public static ArrayList<HomeworkBean> getHomeworkList(int courseId){
        ArrayList<HomeworkBean> homeworkBeans = new ArrayList<>();
        ArrayList<HomeworkBean> homeworkBeansAll = getHomeworkListAll();
        for(HomeworkBean i:homeworkBeansAll) {
            if (i.getCourseId() == courseId) {
                homeworkBeans.add(new HomeworkBean(i.getHomeworkId(), i.getHomeworkname(), i.getQuestion(), i.getCourseId()));
            }
        }
        return homeworkBeans;
    }

    public static HomeworkBean getHomeworkById(int homeworkId){
        ArrayList<HomeworkBean> homeworkBeans = getHomeworkListAll();
        HomeworkBean homeworkBean = new HomeworkBean();
        for(HomeworkBean i:homeworkBeans){
            if(i.getHomeworkId() == homeworkId)
                homeworkBean = new HomeworkBean(i.getHomeworkId(), i.getHomeworkname(), i.getQuestion(), i.getCourseId());
        }
        return homeworkBean;
    }


}
