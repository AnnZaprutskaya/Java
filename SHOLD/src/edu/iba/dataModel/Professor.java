package edu.iba.dataModel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Professor extends Person{
	private Date birth_date;
	public String mainValue(){
		return null;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String string) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		this.birth_date =  (Date) format.parse(string);
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
}
