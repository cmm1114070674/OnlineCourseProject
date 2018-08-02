package Dao;

import java.sql.Connection;
import java.util.List;

import Bean.FileUploadBean;


public class UploadFileDao extends DAO<FileUploadBean>{
	
	public List<FileUploadBean> getFiles(int courseId){
			String sql = "select courseId,resourcePath,resourceName,resourceDesc,resourceId from "
					+ "courseResource where courseId = ?";
			return getForList(sql,courseId);
	}
	
	public void save(List<FileUploadBean> uploadFiles) {
		String sql = "insert into courseResource(courseId,resourcePath,resourceName,resourceDesc)  "
				+ "values (?,?,?,?)";
		for(FileUploadBean file : uploadFiles) {
			update(sql,file.getCourseId(),file.getResourcePath(),file.getResourceName(),
					file.getResourceDesc());
		}
		
	}
	
	public String getResourcePath(int resourceId) {
		String sql = "select resourcePath from courseresource where resourceId = ?";
		String resourcePath = getForValue(sql, resourceId);
		return resourcePath;
	}
	
	public String getResourceName(int resourceId) {
		String sql = "select resourceName from courseresource where resourceId = ?";
		String resourceName= getForValue(sql, resourceId);
		return resourceName;
	}

	public void deleteFile(int resourceId) {
		String sql = "delete from courseResource where resourceId = ?";
		update(sql, resourceId);
	}

}
