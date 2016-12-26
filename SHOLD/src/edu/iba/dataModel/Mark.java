package edu.iba.dataModel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Mark{
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;
	private String study_id;
	private String student_id;
	private String professor_id;
	private Date date;
	private Integer mark;
	private String comments;
	public String getStudy_id() {
		return study_id;
	}
	public void setStudy_id(String study_id) {
		this.study_id = study_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String string) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		this.date = (Date) format.parse(string);
	}
	public String getMark() {
		return mark.toString();
	}
	public void setMark(String string) {
		this.mark = Integer.parseInt(string);
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
