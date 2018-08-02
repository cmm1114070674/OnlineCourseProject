package Dao.DaoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Bean.FloorBean;
import Dao.DAO;

public class FloorDaoImpl extends DAO<FloorBean> {

	public List<FloorBean> returnAllReply(int postId){
		String sql = "select postId,floorId,replier,replyTo,content,time from "
				+ "floor where postId = ? order by time asc";
		 List<FloorBean> floors = new ArrayList<>();
		 floors = getForList(sql, postId);
		return floors;
	}
	
	public void addReply(int postId,String replier,String replyContent,String replyTo) {
		String sql = "insert into floor(postId,replier,replyTo,content,time) values(?,?,?,?,?)";
		Date time = new Date(new java.util.Date().getTime());
		update(sql, postId,replier,replyTo,replyContent,time);
	}
}
