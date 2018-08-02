package Bean;

public class KnowledgePointBean {
private int knowledgePointId ;
private int unitId;
private int knowledgePointIndex;
private String knowledgePoint;
public int getKnowledgePointId() {
	return knowledgePointId;
}
public void setKnowledgePointId(int knowledgePointId) {
	this.knowledgePointId = knowledgePointId;
}
public int getUnitId() {
	return unitId;
}
public void setUnitId(int unitId) {
	this.unitId = unitId;
}
public int getKnowledgePointIndex() {
	return knowledgePointIndex;
}
public void setKnowledgePointIndex(int knowledgePointIndex) {
	this.knowledgePointIndex = knowledgePointIndex;
}
public String getKnowledgePoint() {
	return knowledgePoint;
}
public void setKnowledgePoint(String knowledgePoint) {
	this.knowledgePoint = knowledgePoint;
}
public KnowledgePointBean(int unitId, int knowledgePointIndex, String knowledgePoint) {

	this.unitId = unitId;
	this.knowledgePointIndex = knowledgePointIndex;
	this.knowledgePoint = knowledgePoint;
}
public KnowledgePointBean() {
	
}
@Override
public String toString() {
	return "KnowledgePointBean [knowledgePointId=" + knowledgePointId + ", unitId=" + unitId + ", knowledgePointIndex="
			+ knowledgePointIndex + ", knowledgePoint=" + knowledgePoint + "]\n";
}

}
