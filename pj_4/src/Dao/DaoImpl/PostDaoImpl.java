package Dao.DaoImpl;



import java.sql.Date;
import java.util.List;

import Bean.PostBean;
import Dao.DAO;

public class PostDaoImpl extends DAO<PostBean> {

	public int createPost(int courseId,String subject,String content,String username) {
		String sql = "select id from user where name = ?";
		int posterId = getForValue(sql, username);
		System.out.println("posterId:"+posterId);
		
		Date time = new Date(new java.util.Date().getTime());
		
		sql = "insert into post (courseId,posterId,subject,time,content,latestReplyTime,posterName) values"
				+ "(?,?,?,?,?,?,?)";
		int postId = updateWithKeyReturned(sql,courseId,posterId,subject,time,content,time,username);
	
		return postId;
	}
	
	public PostBean getPostBeanByPostId(int postId) {
		String sql = "select courseId,postId,posterId,subject,time,content,latestReplyTime,posterName "
				+ "from post where postId = ?";
		PostBean post = get(sql, postId);
		return post;
	}
	
	public String getPosterNameByPostId(int posterId) {
		String sql = "select name from user where id = ?";
		String posterName = getForValue(sql, posterId);
		return posterName;
	}
	
	public List<PostBean> returnAllPostByTime(int courseId){
		String sql = "select courseId,postId,posterId,subject,time,content,latestReplyTime,posterName "
				+ "from post where courseId = ? order by latestReplyTime desc";
		List<PostBean> posts = getForList(sql, courseId);
		return posts;
	}
	
	public void updateLatestReplyTime(int postId) {
		Date latestReplyTime = new Date(new java.util.Date().getTime());
		String sql = "update post set latestReplyTime = ? where postId = ?";
		update(sql,latestReplyTime,postId);
		
	}
	
}
