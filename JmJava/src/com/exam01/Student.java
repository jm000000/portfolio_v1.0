package com.exam01;

import java.io.Serializable;

public class Student implements Serializable{
	
	private String name; //�̸�
	private String major; //�а�
	private String studentNum; //�й�
	private double gradeAvg; //���� ���
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
