package Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Bean.CourseBean;

public interface CourseDao {
public CourseBean getCourse(int courseId, String picturePath);
public void savePicture(CourseBean course, String picturePath) throws FileNotFoundException, IOException ;
}
