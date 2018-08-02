package Dao.DaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.DAO;
import Bean.CourseUnit;

public class CourseUnitDao extends DAO<CourseUnit> {

	public void save(List<CourseUnit> units) {
		for(CourseUnit unit : units) {
			String sql = "insert into courseUnit (courseUnitName,courseUnitIndex,courseId) "
					+ "values(?,?,?)";
			update(sql, unit.getCourseUnitName(),unit.getCourseUnitIndex(),unit.getCourseId());
		}
	}
	
	public List<CourseUnit> getAllUnits(int courseId){
		String sql = "select courseUnitId,courseUnitName,courseUnitIndex,courseId from "
				+ "courseunit where courseId = ?";
		List<CourseUnit> units = getForList(sql, courseId);
		return units;
	}
	
	public Map<Integer,CourseUnit> getAllUnitsByOrder(List<CourseUnit> units){
		Map<Integer,CourseUnit> allUnits  = new HashMap<>();
		for(CourseUnit unit : units) {
			int unitIndex = unit.getCourseUnitIndex();
			allUnits.put(unitIndex, unit);
		}
		return allUnits;
	}
	
	public void alterUnit(int courseId,int courseUnitIndex,String alteredUnitName) {
		String sql  = "update courseunit set courseUnitName = ? where courseId = ? "
				+ "and courseUnitIndex = ?";
		update(sql, alteredUnitName,courseId,courseUnitIndex);
	}
	
	
	
}
