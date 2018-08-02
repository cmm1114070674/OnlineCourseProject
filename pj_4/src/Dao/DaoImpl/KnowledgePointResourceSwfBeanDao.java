package Dao.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import Dao.DAO;
import Bean.KnowledgePointResource;
import Bean.KnowledgePointResourceSwfBean;

public class KnowledgePointResourceSwfBeanDao extends DAO<KnowledgePointResourceSwfBean> {

	public void saveSwfs(KnowledgePointResourceSwfBean swf){
		String sql = "insert into swfFileResource (resourceId,knowledgePointId,resourcePath,resourceRelativePath,"
				+ "resourceName,resourceDesc) values(?,?,?,?,?,?)";
		update(sql, swf.getResourceId(),swf.getKnowledgePointId(),swf.getResourcePath(),swf.getResourceRelativePath(),
				swf.getResourceName(),swf.getResourceDesc());
		System.out.println(swf.getKnowledgePointId());
	}
	
	public List<KnowledgePointResourceSwfBean> getKnowledgePointPpts(int knowledgePointId) {
		List<KnowledgePointResourceSwfBean> swfs = null;
		
		String sql = "select resourceId,knowledgePointId,resourcePath,"
				+ "resourceRelativePath,resourceName,resourceDesc from swfFileResource "
				+ "where knowledgePointId = ?";
		swfs = getForList(sql, knowledgePointId);
		
		return swfs;
	}
	
	public void deleteSwf(int resourceId) {
		String sql = "delete from swfFileResource where resourceId = ?";
		update(sql, resourceId);
	}
	
}
