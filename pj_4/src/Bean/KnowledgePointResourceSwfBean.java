package Bean;

public class KnowledgePointResourceSwfBean {
private int resourceId;
private int knowledgePointId;
private String resourcePath;
private String resourceRelativePath;
private String resourceName;
private String resourceDesc;

public int getKnowledgePointId() {
	return knowledgePointId;
}
public void setKnowledgePointId(int knowledgePointId) {
	this.knowledgePointId = knowledgePointId;
}
public String getResourcePath() {
	return resourcePath;
}
public void setResourcePath(String resourcePath) {
	this.resourcePath = resourcePath;
}
public String getResourceRelativePath() {
	return resourceRelativePath;
}
public void setResourceRelativePath(String resourceRelativePath) {
	this.resourceRelativePath = resourceRelativePath;
}
public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}
public String getResourceDesc() {
	return resourceDesc;
}
public void setResourceDesc(String resourceDesc) {
	this.resourceDesc = resourceDesc;
}

public int getResourceId() {
	return resourceId;
}
public void setResourceId(int resourceId) {
	this.resourceId = resourceId;
}
public KnowledgePointResourceSwfBean() {
	
}
public KnowledgePointResourceSwfBean(int resourceId, int knowledgePointId, String resourcePath,
		String resourceRelativePath, String resourceName, String resourceDesc) {
	super();
	this.resourceId = resourceId;
	this.knowledgePointId = knowledgePointId;
	this.resourcePath = resourcePath;
	this.resourceRelativePath = resourceRelativePath;
	this.resourceName = resourceName;
	this.resourceDesc = resourceDesc;
}
@Override
public String toString() {
	return "KnowledgePointResourceSwfBean [resourceId=" + resourceId + ", knowledgePointId=" + knowledgePointId
			+ ", resourcePath=" + resourcePath + ", resourceRelativePath=" + resourceRelativePath + ", resourceName="
			+ resourceName + ", resourceDesc=" + resourceDesc + "]\n";
}




}
