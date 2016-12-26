package edu.iba.dataModel;

public class Group{
	private String group_number;
	private float avg_mark;
	public Float getAvg_mark(){
		return avg_mark;
	}
	public void setAvg_mark(float newMark){
		avg_mark=newMark;
	}
	public String getGroup_number() {
		return group_number;
	}
	public void setGroup_number(String group_number) {
		this.group_number = group_number;
	}
}
