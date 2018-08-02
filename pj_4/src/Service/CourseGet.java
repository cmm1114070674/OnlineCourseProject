package Service;

import Bean.CourseBean;
import Bean.Student_CourseBean;
import Bean.Teacher_CourseBean;
import Bean.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static Service.JdbcUtils.getConnected;
import static Service.UserGet.getUserByUsername;


public class CourseGet {
    public static ArrayList<CourseBean> getCourseListAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<CourseBean> courseBeans = new ArrayList<>();
        try{
            connection = getConnected();
            String sql = "SELECT id, name, introduction, picture, date, studentNumber, picturePath, pictureRelativePath FROM course";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String introduction = rs.getString("introduction");
                byte[] picture = rs.getBytes("picture");
                Date date = rs.getDate("date");
                int studentNumber = rs.getInt("studentNumber");
                String picturePath = rs.getString("picturePath");
                String pictureRelativePath = rs.getString("pictureRelativePath");
                CourseBean courseBean = new CourseBean(name, introduction, picture, picturePath, pictureRelativePath, date, studentNumber);
                courseBean.setId(id);
                courseBeans.add(courseBean);
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
        return courseBeans;
    }

    public static ArrayList<Student_CourseBean> getStudentCourseListAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Student_CourseBean> courseBeans = new ArrayList<>();
        try{
            connection = getConnected();
            String teacherSQL = "SELECT studentId, courseId FROM student_course";
            statement = connection.prepareStatement(teacherSQL);
            ResultSet rs = statement.executeQuery(teacherSQL);
            while(rs.next()) {
                int studentId = rs.getInt("studentId");
                int courseId = rs.getInt("courseId");
                Student_CourseBean courseBean = new Student_CourseBean(studentId, courseId);
                courseBeans.add(courseBean);
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
        return courseBeans;
    }

    public static ArrayList<Teacher_CourseBean> getTeacherCourseListAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Teacher_CourseBean> courseBeans = new ArrayList<>();
        try{
            connection = getConnected();
            String teacherSQL = "SELECT teacherId, courseId FROM teacher_course";
            statement = connection.prepareStatement(teacherSQL);
            ResultSet rs = statement.executeQuery(teacherSQL);
            while(rs.next()) {
                int teacherId = rs.getInt("teacherId");
                int courseId = rs.getInt("courseId");
                Teacher_CourseBean courseBean = new Teacher_CourseBean(teacherId, courseId);
                courseBeans.add(courseBean);
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
        return courseBeans;
    }

    public static CourseBean getCourse(int courseId){
        ArrayList<CourseBean> courseBeans = getCourseListAll();
        CourseBean courseBean = new CourseBean();
        for(CourseBean i:courseBeans)
            if(i.getId() == courseId)
                courseBean = i;
        return courseBean;
    }

    public static ArrayList<CourseBean> getChooseCourseList(int userId){
        ArrayList<Student_CourseBean> student_courseBeans = getStudentCourseListAll();
        ArrayList<CourseBean> courseBeans = new ArrayList<>();
        CourseBean courseBean = new CourseBean();
        CourseBean courseBean1 = new CourseBean();
        for(Student_CourseBean i:student_courseBeans) {
            if (i.getStudentId() == userId) {
                courseBean1 = getCourse(i.getCourseId());
                courseBean = new CourseBean(courseBean1.getName(), courseBean1.getIntroduction(), courseBean1.getPicture(), courseBean1.getPicturePath(),
                        courseBean1.getPictureRelativePath(), courseBean1.getDate(), courseBean1.getStudentNumber());
                courseBean.setId(i.getCourseId());
                courseBeans.add(courseBean);
            }
        }
        return courseBeans;
    }

    public static ArrayList<CourseBean> getOpenCourseList(int userId){
        ArrayList<Teacher_CourseBean> student_courseBeans = getTeacherCourseListAll();
        ArrayList<CourseBean> courseBeans = new ArrayList<>();
        CourseBean courseBean = new CourseBean();
        CourseBean courseBean1 = new CourseBean();
        for(Teacher_CourseBean i:student_courseBeans) {
            if (i.getTeacherId() == userId) {
                courseBean1 = getCourse(i.getCourseId());
                courseBean = new CourseBean(courseBean1.getName(), courseBean1.getIntroduction(), courseBean1.getPicture(), courseBean1.getPicturePath(),
                        courseBean1.getPictureRelativePath(), courseBean1.getDate(), courseBean1.getStudentNumber());
                courseBean.setId(i.getCourseId());
                courseBeans.add(courseBean);
            }
        }
        return courseBeans;
    }

    public static UserBean getTeacherByCourse(CourseBean course){
        ArrayList<Teacher_CourseBean> teacher_courseBeans = getTeacherCourseListAll();
        int teacherId = 0;
        for(Teacher_CourseBean i:teacher_courseBeans)
            if(i.getCourseId() == course.getId())
                teacherId = i.getTeacherId();
        ArrayList<UserBean> userBeans = UserGet.getUserListAll();
        UserBean userBean = new UserBean();
        for(UserBean i:userBeans)
            if(i.getId() == teacherId)
                userBean = i;
        return userBean;
    }

    public static ArrayList<CourseBean> getCourseByTeacher(UserBean userBean){
        ArrayList<CourseBean> courseBeans = new ArrayList<>();
        ArrayList<Teacher_CourseBean> teacher_courseBeans = getTeacherCourseListAll();
        System.out.println("courseget:teacher_courseBeans "+teacher_courseBeans);
        int courseId = 0;
        CourseBean course = new CourseBean();
        for(Teacher_CourseBean i:teacher_courseBeans) {
        	
            if (i.getTeacherId() == userBean.getId()) {
            	courseId = i.getCourseId();
            	
            	course = getCourse(courseId);
            	courseBeans.add(course);
            	
            }
        }
        return courseBeans;
    }


}
