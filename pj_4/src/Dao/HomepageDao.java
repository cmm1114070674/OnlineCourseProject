package Dao;

import java.util.List;

import Bean.CourseBean;
import Bean.HomepageCourseBean;

public interface HomepageDao {
public List<CourseBean> getLatestCourses();
public List<CourseBean> getHottestCourses();
}
