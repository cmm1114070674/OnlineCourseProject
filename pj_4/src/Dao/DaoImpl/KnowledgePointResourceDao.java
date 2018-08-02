package Dao.DaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.DAO;
import Bean.KnowledgePointResource;

public class KnowledgePointResourceDao extends DAO<KnowledgePointResource> {

	public void save(List<KnowledgePointResource> beans) {
		
		for(KnowledgePointResource bean : beans) {
			String sql = "insert into knowledgePointResource(knowledgePointId,resourceName,resourcePath,"
					+ "resourceRelativePath,resourceExtendName,resourceDesc) values(?,?,?,?,?,?)";
			update(sql, bean.getKnowledgePointId(),bean.getResourceName(),bean.getResourcePath(),
					bean.getResourceRelativePath(),bean.getResourceExtendName(),bean.getResourceDesc());
		}
	}
	
	public List<Integer> saveWithKeysReturned(List<KnowledgePointResource> beans) {
		List<Integer> keys = new ArrayList<>();
		for(KnowledgePointResource bean : beans) {
			String sql = "insert into knowledgePointResource(knowledgePointId,resourceName,resourcePath,"
					+ "resourceRelativePath,resourceExtendName,resourceDesc) values(?,?,?,?,?,?)";
		int key = updateWithKeyReturned(sql, bean.getKnowledgePointId(),bean.getResourceName(),bean.getResourcePath(),
					bean.getResourceRelativePath(),bean.getResourceExtendName(),bean.getResourceDesc());
		keys.add(key);
		}
		return keys;
	}
	
	
	
	public List<KnowledgePointResource> getKnowledgePointVideos(int knowledgePointId) {
		List<KnowledgePointResource> videos = new ArrayList<>();
		
		String sql = "select resourceId,knowledgePointId,resourceName,resourcePath,"
				+ "resourceRelativePath,resourceExtendName,resourceDesc from knowledgePointResource "
				+ "where knowledgePointId = ?";
		List<KnowledgePointResource> allResources = getForList(sql, knowledgePointId);
		
		for(KnowledgePointResource resource : allResources) {
			if(resource.getResourceExtendName().equals("mp4")) {
				videos.add(resource);
			}
		}
		return videos;
	}
	
	public void deleteResource(int resourceId) {
		String sql = "delete from knowledgePointResource where resourceId = ?";
		update(sql,resourceId);
	}
	
	public List<KnowledgePointResource> getBeanListByKeys(List<Integer> keys){
		List<KnowledgePointResource> beansFromDatabase = new ArrayList<>();
		String sql = "select resourceId,knowledgePointId,resourceName,resourcePath,"
				+ "resourceRelativePath,resourceExtendName,resourceDesc from knowledgePointResource "
				+ "where resourceId = ?";
		
		for(Integer key : keys) {
			KnowledgePointResource resource = get(sql, key);
			beansFromDatabase.add(resource);
		}
		return beansFromDatabase;
	}
	
	
}
