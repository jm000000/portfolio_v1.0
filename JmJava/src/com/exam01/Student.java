package com.exam01;

import java.io.Serializable;

public class Student implements Serializable{
	
	private String name; //이름
	private String major; //학과
	private String studentNum; //학번
	private double gradeAvg; //학점 평균
	private String searchName;	
	
	public Student(String name, String major, String studentNum, double gradeAvg) 
	{
		this.name=name;
		this.major=major;
		this.studentNum=studentNum;
		this.gradeAvg=gradeAvg;
	}
	
	public Student search(String searchName) 
	{		
		return null;
	}	
	public String getName() 
	{
		return name;
	}	
	//------------------------------------
	public String getMajor() 
	{
		return major;
	}	
	//------------------------------------
	public String getStudentNum() 
	{
		return studentNum;
	}	
	//------------------------------------
	public double getGradeAvg() 
	{
		return gradeAvg;
	}	
}
