package com.bestbright.hibernate.onetomany.model;

public class Teacher {
private long teacherId;
private String teacherName;
private String nrc;
public Teacher(){
	
}
public long getTeacherId() {
	return teacherId;
}
public void setTeacherId(long teacherId) {
	this.teacherId = teacherId;
}
public String getTeacherName() {
	return teacherName;
}
public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
}
public String getNrc() {
	return nrc;
}
public void setNrc(String nrc) {
	this.nrc = nrc;
}

}
