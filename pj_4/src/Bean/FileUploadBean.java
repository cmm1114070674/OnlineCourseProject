package Bean;

public class FileUploadBean {

	private int resourceId;
	private int courseId;
	private String resourcePath;
	private String resourceName;
	private String resourceDesc;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public FileUploadBean() {
		
	}
	public String getResourceDesc() {
		return resourceDesc;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public FileUploadBean(int courseId, String resourcePath, String resourceName, String resourceDesc) {
		super();
		this.courseId = courseId;
		this.resourcePath = resourcePath;
		this.resourceName = resourceName;
		this.resourceDesc = resourceDesc;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	@Override
	public String toString() {
		return "FileUploadBean [resourceId=" + resourceId + ", courseId=" + courseId + ", resourcePath=" + resourcePath
				+ ", resourceName=" + resourceName + ", resourceDesc=" + resourceDesc + "]\n";
	}


	

	
}
