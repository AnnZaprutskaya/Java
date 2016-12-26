package edu.iba.dataModel;

public class Person{
	protected String id;
	protected String first_name;
	protected String second_name;
	protected String father_name;
	public String getFirst_name() {
		return first_name;
	}
	private float avg_mark;
	public Float getAvg_mark(){
		return avg_mark;
	}
	public void setAvg_mark(float newMark){
		avg_mark=newMark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getSecond_name() {
		return second_name;
	}
	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}
}
