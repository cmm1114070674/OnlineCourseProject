package Dao.DaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import Bean.CourseBean;
import Dao.ChooseCourseDao;
import Dao.TransactionDao2;
import Service.JdbcUtils;


public class ChooseCourseDaoImpl  extends TransactionDao2<CourseBean> implements ChooseCourseDao {

	@Override
	public void chooseCourse(String username, int courseId) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			
			connection.setAutoCommit(false);
			
			String sql = "select id from user where name = ?";
			
			int studentId = getForValue(connection, sql, username);
			
		    sql = "insert into student_course (studentId,courseId) values (?,?)";
		    
		    update(connection,sql,studentId,courseId);
		    
		    
		    sql = "select studentNumber from course where id = ?";
		    int studentNumber = getForValue(connection, sql, courseId);
		    
		    sql = "update course set studentNumber = ? where id = ?";
		    update(connection, sql, studentNumber+1,courseId);
		    
		    connection.commit();
					
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			JdbcUtils.releaseConnection(connection);
		}

	}

}
