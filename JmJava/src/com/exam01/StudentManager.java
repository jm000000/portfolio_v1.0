package com.exam01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class StudentManager {
   static Scanner sc = new Scanner(System.in);
   ArrayList<Student> array = new ArrayList<Student>(); //전역으로 만들어줘야 한다.
   
   File dir, file;
	public StudentManager() throws IOException, ClassNotFoundException //클레스가 없을수도 있다
	{
		dir = new File("src\\com\\exam01");
		file = new File(dir, "student.txt"); //myFriend.txt이파일로 읽어라
		if(file.exists()) //파일 있으면
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			array = (ArrayList<Student>) ois.readObject();
		}
		else
		{
			file.createNewFile(); //파일 생성
		}
	}
   
   public static void showMenu() //처음 보여지는 메뉴
   {
		System.out.println("선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 전체보기");
		System.out.println("3. 학생찾기");
		System.out.println("4. 저장/종료");
		System.out.print("선택  >>");
   }
   public void inputData() //입력한 데이터 보여주기
   {
	   while(true) 
	   {
		   System.out.println("학생 이름,학과,학번,학점평균 입력하세요.(입력은 , 로 구분하고 종료는 x)");
		   System.out.print(">> ");
		   try {
			   String text = StudentManager.sc.nextLine();
			   if(text.toLowerCase().equals("x")) 
			   {
				   System.out.println("입력종료");
				   break;
			   }
			   StringTokenizer st = new StringTokenizer(text, ",");
			   String name = st.nextToken();//이름
			   String dept = st.nextToken(); //학과
			   String id = st.nextToken();//학번
			   double grade =Double.parseDouble(st.nextToken()); //학점
			   array.add(new Student(name, dept, id, grade));
				   
		   }catch (NoSuchElementException e){
			   System.out.println("정확하게 입력해주세요.");		   
		   }catch (NumberFormatException n) {
			   System.out.println("정확하게 입력해주세요.");
		   }
	   }
   }
	 public void viewData() 
	 {
		 System.out.println("학생전체리스트.....");
		 for(Student student : array) {
				System.out.println("이름:" + student.getName());
				System.out.println("학과:" + student.getMajor());
				System.out.println("학번:" + student.getStudentNum());
				System.out.println("학점평균:" + student.getGradeAvg());
				System.out.println();
		 }		   
	   }
	 public void searchData() 
	 {
		 System.out.println("학생 찾기.....");
		 System.out.print("찾을 학생 이름 >>");
		 String searhName = StudentManager.sc.next();
		 Student s = search(searhName);
		 if(s==null) 
		 {
			 System.out.println("찾는 학생 없음");
			 return;
		 }
		 System.out.println("이름:" + s.getName());
		System.out.println("학과:" + s.getMajor());
		System.out.println("학번:" + s.getStudentNum());
		System.out.println("학점평균:" + s.getGradeAvg());
	 }
	 private Student search(String searhName) 
	 {
		 for(int i=0;i<array.size();i++) 
		 {
			 if(searhName.equals(array.get(i).getName())) 
			 {
				 return array.get(i);
			 }
		 }
		 return null;
	 }
	 public void saveData() throws FileNotFoundException, IOException 
	 {
		 ObjectOutputStream oos = 
					new ObjectOutputStream(new FileOutputStream(file));//file로 내보낸다.
																
		oos.writeObject(array); 
		System.out.println("종료/저장");
		System.exit(0);
	}	

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		StudentManager sm = new StudentManager();
		while(true)
		{
			StudentManager.showMenu();
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) 
			{
			case 1: sm.inputData(); break;
			case 2: sm.viewData(); break;
			case 3: sm.searchData(); break;
			case 4: sm.saveData();
			   System.out.println("프로그램 종료");
			   System.exit(0);
			default : System.out.println("입력오류");
			}			
		}
	}
}