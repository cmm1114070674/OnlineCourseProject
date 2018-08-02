package Service;

import Bean.CourseBean;
import Bean.UserBean;

import java.util.*;

public class SearchUtil {
    private static ArrayList<CourseBean> coursesAll = CourseGet.getCourseListAll();
    private static ArrayList<UserBean> usersAll = UserGet.getUserListAll();

    public static ArrayList<CourseBean> getCoursesAllByDate(){
        ArrayList<CourseBean> courses = coursesAll;
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getDate().before(o2.getDate()))
                    return 1;
                if(o1.getDate().equals(o2.getDate()))
                    return 0;
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> getCoursesAllByStudentNumber(){
        ArrayList<CourseBean> courses = coursesAll;
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getStudentNumber() > o2.getStudentNumber())
                    return 1;
                if(o1.getStudentNumber() == o2.getStudentNumber())
                    return 0;
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> searchCourseNameByDate(String word){
        ArrayList<CourseBean> courses = new ArrayList<>();
        for(CourseBean i:coursesAll){
            if(i.getName().contains(word)) {
                int id = i.getId();
                String name = i.getName();
                String introduction = i.getIntroduction();
                byte[] picture = i.getPicture();
                String picturePath = i.getPicturePath();
                String pictureRelativePath = i.getPictureRelativePath();
                Date date = i.getDate();
                int studentNumber = i.getStudentNumber();
                CourseBean courseBean = new CourseBean(name, introduction, picture, picturePath, pictureRelativePath, date, studentNumber);
                courseBean.setId(id);
                courses.add(courseBean);
            }
        }
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getDate().before(o2.getDate()))
                    return 1;
                if(o1.getDate().equals(o2.getDate()))
                    return 0;
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> searchCourseNameByStudentNumber(String word){
        ArrayList<CourseBean> courses = new ArrayList<>();
        for(CourseBean i:coursesAll){
            if(i.getName().contains(word)){
                int id = i.getId();
                String name = i.getName();
                String introduction = i.getIntroduction();
                byte[] picture = i.getPicture();
                String picturePath = i.getPicturePath();
                String pictureRelativePath = i.getPictureRelativePath();
                Date date = i.getDate();
                int studentNumber = i.getStudentNumber();
                CourseBean courseBean = new CourseBean(name, introduction, picture, picturePath, pictureRelativePath, date, studentNumber);
                courseBean.setId(id);
                courses.add(courseBean);
            }
        }
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getStudentNumber() > o2.getStudentNumber())
                    return 1;
                if(o1.getStudentNumber() == o2.getStudentNumber())
                    return 0;
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> searchCourseTeacherByDate(String word){
        ArrayList<CourseBean> courses = new ArrayList<>();
        ArrayList<UserBean> users = new ArrayList<>();
        for(UserBean i:usersAll) {
        	if (i.getName().contains(word))
        		users.add(new UserBean(i.getId(), i.getName(), i.getPassword(),
        				i.getEmail(), i.getPhone(), i.getActiveCode(), i.getActiveFlag(), i.getRegisterTime()));
        }
        System.out.println("u"+users);
        for (UserBean j : users) {
            courses.addAll(CourseGet.getCourseByTeacher(j));
            System.out.println("c"+CourseGet.getCourseByTeacher(j));
        }
        for(CourseBean k:courses) {
        	System.out.println("cd"+k);
        }
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getDate().before(o2.getDate())) {
                	System.out.println("searchUtil:"+o1.getDate());
                	System.out.println("searchUtil:"+o2.getDate());
                	return 1;
                }
                if(o1.getDate().equals(o2.getDate())) {
                	System.out.println("searchUtil:"+o1.getDate());
                	System.out.println("searchUtil:"+o2.getDate());
                	return 0;
                }
                   System.out.println("-1");
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> searchCourseTeacherByStudentNumber(String word){
        ArrayList<CourseBean> courses = new ArrayList<>();
        ArrayList<UserBean> users = new ArrayList<>();
        for(UserBean i:usersAll)
            if (i.getName().contains(word))
                users.add(new UserBean(i.getId(), i.getName(), i.getPassword(),
                        i.getEmail(), i.getPhone(), i.getActiveCode(), i.getActiveFlag(), i.getRegisterTime()));
        for (UserBean j : users)
            courses.addAll(CourseGet.getCourseByTeacher(j));
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getStudentNumber() > o2.getStudentNumber())
                    return 1;
                if(o1.getStudentNumber() == o2.getStudentNumber())
                    return 0;
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> searchCourseContentByDate(String word){
        ArrayList<CourseBean> courses = new ArrayList<>();
        for(CourseBean i:coursesAll){
            if(i.getIntroduction().contains(word)){
                int id = i.getId();
                String name = i.getName();
                String introduction = i.getIntroduction();
                byte[] picture = i.getPicture();
                String picturePath = i.getPicturePath();
                String pictureRelativePath = i.getPictureRelativePath();
                Date date = i.getDate();
                int studentNumber = i.getStudentNumber();
                CourseBean courseBean = new CourseBean(name, introduction, picture, picturePath, pictureRelativePath, date, studentNumber);
                courseBean.setId(id);
                courses.add(courseBean);
            }
        }
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getDate().before(o2.getDate()))
                    return 1;
                if(o1.getDate().equals(o2.getDate()))
                    return 0;
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> searchCourseContentByStudentNumber(String word){
        ArrayList<CourseBean> courses = new ArrayList<>();
        for(CourseBean i:coursesAll){
            if(i.getIntroduction().contains(word)){
                int id = i.getId();
                String name = i.getName();
                String introduction = i.getIntroduction();
                byte[] picture = i.getPicture();
                String picturePath = i.getPicturePath();
                String pictureRelativePath = i.getPictureRelativePath();
                Date date = i.getDate();
                int studentNumber = i.getStudentNumber();
                CourseBean courseBean = new CourseBean(name, introduction, picture, picturePath, pictureRelativePath, date, studentNumber);
                courseBean.setId(id);
                courses.add(courseBean);
            }
        }
        Collections.sort(courses, new Comparator<CourseBean>() {
            @Override
            public int compare(CourseBean o1, CourseBean o2) {
                if(o1.getStudentNumber() > o2.getStudentNumber())
                    return 1;
                if(o1.getStudentNumber() == o2.getStudentNumber())
                    return 0;
                return -1;
            }
        });
        return courses;
    }

    public static ArrayList<CourseBean> getReverseList(ArrayList<CourseBean> courseBeans){
        int number = courseBeans.size();
        CourseBean temp = new CourseBean();
        CourseBean result_course = new CourseBean();
        ArrayList<CourseBean> result = new ArrayList<>();
        for(int i = 0; i < number; i++){
            temp = courseBeans.get(number - i -1);
            result_course = new CourseBean(temp.getName(), temp.getIntroduction(), temp.getPicture(),
                    temp.getPicturePath(), temp.getPictureRelativePath(), temp.getDate(), temp.getStudentNumber());
            result_course.setId(temp.getId());
            result.add(result_course);
        }
        return result;
    }

}
