package edu.iba.dataModel;

public class Study{
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;
	private String hours;
	public String getHours() {
		return hours;
	}
	public void setHours(String string) {
		this.hours = string;
	}
	private String name;
	private String professor_id;
	private float avg_mark;
	public Float getAvg_mark(){
		return avg_mark;
	}
	public void setAvg_mark(float newMark){
		avg_mark=newMark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
	}
}
