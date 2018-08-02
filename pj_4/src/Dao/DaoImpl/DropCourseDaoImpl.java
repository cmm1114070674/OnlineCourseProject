package Dao.DaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import Dao.ChooseCourseDao;
import Dao.DAO;
import Dao.TransactionDao2;
import Bean.CourseBean;
import Service.JdbcUtils;

public class DropCourseDaoImpl  extends TransactionDao2<CourseBean>{
	public void dropCourse(String username, int courseId) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			
			connection.setAutoCommit(false);
			
			String sql = "select id from user where name = ?";
			
			int studentId = getForValue(connection, sql, username);
			
		    sql = "delete from student_course where studentId = ? and courseId = ?";
		    
		    update(connection,sql,studentId,courseId);
		    
		    
		    sql = "select studentNumber from course where id = ?";
		    int studentNumber = getForValue(connection, sql, courseId);
		    
		    sql = "update course set studentNumber = ? where id = ?";
		    update(connection, sql, studentNumber-1,courseId);
		    
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
