package Bean;

public class KnowledgePointResource {
private int resourceId;
private int knowledgePointId;
private String resourceName;
private String resourcePath;
private String resourceRelativePath;
private String resourceExtendName;
private String resourceDesc;
public int getResourceId() {
	return resourceId;
}
public void setResourceId(int resourceId) {
	this.resourceId = resourceId;
}
public int getKnowledgePointId() {
	return knowledgePointId;
}
public void setKnowledgePointId(int knowledgePointId) {
	this.knowledgePointId = knowledgePointId;
}
public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
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
public String getResourceExtendName() {
	return resourceExtendName;
}
public void setResourceExtendName(String resourceExtendName) {
	this.resourceExtendName = resourceExtendName;
}
public String getResourceDesc() {
	return resourceDesc;
}
public void setResourceDesc(String resourceDesc) {
	this.resourceDesc = resourceDesc;
}
public KnowledgePointResource(int knowledgePointId, String resourceName, String resourcePath,
		String resourceRelativePath, String resourceExtendName, String resourceDesc) {
	this.knowledgePointId = knowledgePointId;
	this.resourceName = resourceName;
	this.resourcePath = resourcePath;
	this.resourceRelativePath = resourceRelativePath;
	this.resourceExtendName = resourceExtendName;
	this.resourceDesc = resourceDesc;
}
//�޲ι��캯��������
public KnowledgePointResource() {
	
}
@Override
public String toString() {
	return "KnowledgePointResource [resourceId=" + resourceId + ", knowledgePointId=" + knowledgePointId
			+ ", resourceName=" + resourceName + ", resourcePath=" + resourcePath + ", resourceRelativePath="
			+ resourceRelativePath + ", resourceExtendName=" + resourceExtendName + ", resourceDesc=" + resourceDesc
			+ "]\n";
}

}
