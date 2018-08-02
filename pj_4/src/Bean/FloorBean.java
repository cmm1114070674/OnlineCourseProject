package Bean;

import java.util.Date;

public class FloorBean {
private int postId;
private int floorId;
private String replier;
private String replyTo;
private String content;
private Date time;
public int getPostId() {
	return postId;
}
public void setPostId(int postId) {
	this.postId = postId;
}
public int getFloorId() {
	return floorId;
}
public void setFloorId(int floorId) {
	this.floorId = floorId;
}

public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}

public FloorBean() {
	
}
public String getReplier() {
	return replier;
}
public void setReplier(String replier) {
	this.replier = replier;
}
public String getReplyTo() {
	return replyTo;
}
public void setReplyTo(String replyTo) {
	this.replyTo = replyTo;
}
public FloorBean(int postId, String replier, String replyTo, String content, Date time) {
	super();
	this.postId = postId;
	this.replier = replier;
	this.replyTo = replyTo;
	this.content = content;
	this.time = time;
}
@Override
public String toString() {
	return "FloorBean [postId=" + postId + ", floorId=" + floorId + ", replier=" + replier + ", replyTo=" + replyTo
			+ ", content=" + content + ", time=" + time + "]\n";
}



}
