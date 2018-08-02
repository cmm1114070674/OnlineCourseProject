package Bean;

import java.util.Date;

public class PostBean {
private int courseId;
private int postId;
private int posterId;
private String subject;
private String content;
private Date time;
private Date latestReplyTime;
private String posterName;

public String getPosterName() {
	return posterName;
}
public void setPosterName(String posterName) {
	this.posterName = posterName;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public int getPostId() {
	return postId;
}
public void setPostId(int postId) {
	this.postId = postId;
}
public int getPosterId() {
	return posterId;
}
public void setPosterId(int posterId) {
	this.posterId = posterId;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}


public PostBean(int courseId, int posterId, String subject, String content, Date time, Date latestReplyTime,
		String posterName) {
	super();
	this.courseId = courseId;
	this.posterId = posterId;
	this.subject = subject;
	this.content = content;
	this.time = time;
	this.latestReplyTime = latestReplyTime;
	this.posterName = posterName;
}
public PostBean(){
	
}

public Date getLatestReplyTime() {
	return latestReplyTime;
}
public void setLatestReplyTime(Date latestReplyTime) {
	this.latestReplyTime = latestReplyTime;
}
@Override
public String toString() {
	return "PostBean [courseId=" + courseId + ", postId=" + postId + ", posterId=" + posterId + ", subject=" + subject
			+ ", content=" + content + ", time=" + time + ", latestReplyTime=" + latestReplyTime + ", posterName="
			+ posterName + "]\n";
}



}
