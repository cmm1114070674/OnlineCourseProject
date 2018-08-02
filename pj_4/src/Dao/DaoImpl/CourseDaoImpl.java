package Dao.DaoImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Bean.CourseBean;
import Dao.CourseDao;
import Dao.DAO;
import Service.CourseGet;

import static Service.JdbcUtils.getConnected;

public class CourseDaoImpl extends DAO<CourseBean> implements CourseDao {

	public CourseBean getCourse(int courseId,String picturePath) {
		
		String sql = "select id,name,introduction,picture,picturePath,pictureRelativePath,date,studentNumber from course "
				+ "where id = ?";
		CourseBean course = get(sql,courseId);
		
		try {
			savePicture(course,picturePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}
	
	public void savePicture(CourseBean course,String picturePath) throws FileNotFoundException, IOException {
		byte[] picture = course.getPicture();
		if(picture.length != 0) {
			OutputStream out = new FileOutputStream(picturePath);
			out.write(picture);
			out.close();
		}
	}
	
	public void alterCourseName(int courseId,String courseName) {
		String sql = "update course set name = ? where id = ?";
		update(sql, courseName,courseId);
	}
	
	public void alterCourseIntro(int courseId,String courseIntro) {
		String sql = "update course set introduction = ? where id = ?";
		update(sql, courseIntro,courseId);
	}
	
	public void alterPicturePath(int courseId,String picturePath) {
		String sql = "update course set picturePath = ? where id = ?";
		update(sql, picturePath,courseId);
	}

	public void alterPictureRelativePath(int courseId,String extName) {
		String pictureRelativePath = "CoursePictureFromDatabase\\" + courseId + extName;
		String sql = "update course set pictureRelativePath = ? where id = ?";
		update(sql,pictureRelativePath,courseId);
	}
	
	public void alterPictureBlob(int courseId , String filePath) {
		try {
			InputStream inputStream = new FileInputStream(filePath);
			String sql = "update course set picture = ? where id =  ?";
			update(sql,inputStream,courseId);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getPermissoin(int courseId,String username) {
		int permission ;
	
		String sql = "select id from user where name = ?";
		int userId = getForValue(sql, username);
		System.out.println("userId:"+userId);
	
		sql = "select count(studentId) from student_course where studentId = ? and courseId = ?";
		long stuCount = getForValue(sql, userId,courseId);
		if(stuCount != 0) {
			permission = 2;
		}
		else {
			sql = "select count(teacherId) from teacher_course where teacherId = ? and courseId = ?";
			long teaCount = getForValue(sql, userId,courseId);
			if(teaCount != 0) {
				permission = 3;
			}
			else {
				permission = 1;
			}
		}
		return permission;
	}

	public static void course_insert(CourseBean courseBean){
		Connection connection = null;
		PreparedStatement statement = null;
		String name = courseBean.getName();
		String introduction = courseBean.getIntroduction();
		byte[] picture = courseBean.getPicture();
		Date date = courseBean.getDate();
		int studentNumber = courseBean.getStudentNumber();
		String picturePath = courseBean.getPicturePath();
		String pictureRelativePath = courseBean.getPictureRelativePath();
		try {
			connection = getConnected();
            String sql = "INSERT INTO course(name, introduction, picture, date, studentNumber, picturePath, pictureRelativePath) VALUES(?, ?, ?, ?, ?, ?, ?)";
  
  
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, introduction);
			statement.setBytes(3, picture);
            java.sql.Date sdate = new java.sql.Date(date.getTime());//ÀàÐÍ×ª»»
			statement.setDate(4, sdate);
			statement.setInt(5, studentNumber);
			statement.setString(6, picturePath);
			statement.setString(7, pictureRelativePath);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public static void course_update(CourseBean course){
		Connection connection = null;
		PreparedStatement statement = null;
		int id = course.getId();
		String name = course.getName();
		String introduction = course.getIntroduction();
		byte[] picture = course.getPicture();
		String picturePath = course.getPicturePath();
		String pictureRelativePath = course.getPictureRelativePath();
		String sql = "update course set name = ?, introduction = ?, picture = ?, picturePath = ?, pictureRelativePath = ? where id = ?";
		try{
			connection = getConnected();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, introduction);
			statement.setBytes(3, picture);
			statement.setString(4, picturePath);
			statement.setString(5, pictureRelativePath);
			statement.setInt(6, id);
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

	public static void teacher_course_insert(int teacherId, int courseId){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnected();
			String sql = "INSERT INTO teacher_course(teacherId, courseId) VALUES(?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, teacherId);
			statement.setInt(2, courseId);
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

	public static void student_course_insert(int studentId, int courseId){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnected();
			String sql = "INSERT INTO student_course(studentId, courseId) VALUES(?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			statement.setInt(2, courseId);
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

	public static void student_course_delete(int studentId, int courseId){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnected();
			String sql = "DELETE FROM student_course WHERE studentId = ? AND courseId = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			statement.setInt(2, courseId);
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


}
