package Dao.DaoImpl;

import java.util.List;


import Dao.DAO;

import Dao.HomepageDao;
import Bean.CourseBean;
import Bean.HomepageCourseBean;

public class HomepageDaoImpl extends DAO<CourseBean> implements HomepageDao {


	@Override
	public List<CourseBean> getLatestCourses() {
		
		List<CourseBean> courses = null;
		
		String sql = "select id,name,introduction,date,studentNumber,picturePath,pictureRelativePath from course order by date desc";
		
		courses = getForList(sql);
		
		return courses;
	}

	@Override
	public List<CourseBean> getHottestCourses() {
		
		List<CourseBean> courses = null;
		
		String sql = "select id,name,introduction,date,studentNumber,picturePath,pictureRelativePath from course order by studentNumber desc";
		
		courses = getForList(sql);
		
		return courses;
	}

}
