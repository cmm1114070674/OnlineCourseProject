package Dao.DaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.DAO;
import Bean.CourseUnit;
import Bean.KnowledgePointBean;

public class KnowledgePointDao extends DAO<KnowledgePointBean> {

	public void saveBasicInfo(List<KnowledgePointBean> points) {
		for(KnowledgePointBean point : points) {
			String sql = "insert into unitKnowledgePoint (unitId,knowledgePoint,knowledgePointIndex) "
					+ "values(?,?,?)";
			update(sql, point.getUnitId(),point.getKnowledgePoint(),point.getKnowledgePointIndex());
		}
	}
	
	public Map<Integer,List<KnowledgePointBean>> getAllKnowledgePoints(List<CourseUnit> units){
		Map<Integer,List<KnowledgePointBean>> allKnowledgePoints = new HashMap<>();
		
		for(CourseUnit unit : units) {
			int courseUnitId = unit.getCourseUnitId();
			String sql = "select knowledgePointId,unitId,knowledgePointIndex,knowledgePoint from "
					+ "UnitKnowledgePoint where unitId = ?";
			List<KnowledgePointBean> unitKnowledgePoints = getForList(sql, courseUnitId);
			allKnowledgePoints.put(courseUnitId, unitKnowledgePoints);
		}
		
		return allKnowledgePoints;
	}
	
	public Map<Integer,Long> getKnowledgePointsCount(List<CourseUnit> units){
		 Map<Integer,Long> knowledgePointsCount = new HashMap<>();
		 
			for(CourseUnit unit : units) {
				int courseUnitIndex = unit.getCourseUnitIndex();
				int unitId = unit.getCourseUnitId();
				String sql = "select count(knowledgePointIndex) from "
						+ "UnitKnowledgePoint where unitId = ?";
				long count = getForValue(sql, unitId);
				knowledgePointsCount.put(courseUnitIndex, count);
			}
		return knowledgePointsCount;
	}
	
	public void alterKnowledgePoint(int knowledgePointId,String alteredPoint) {
		String sql = "update unitknowledgepoint set knowledgePoint = ? where knowledgePointId = ?";
		update(sql, alteredPoint,knowledgePointId);
	}
	
}
