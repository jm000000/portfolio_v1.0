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
   ArrayList<Student> array = new ArrayList<Student>(); //�������� �������� �Ѵ�.
   
   File dir, file;
	public StudentManager() throws IOException, ClassNotFoundException //Ŭ������ �������� �ִ�
	{
		dir = new File("src\\com\\exam01");
		file = new File(dir, "student.txt"); //myFriend.txt�����Ϸ� �о��
		if(file.exists()) //���� ������
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			array = (ArrayList<Student>) ois.readObject();
		}
		else
		{
			file.createNewFile(); //���� ����
		}
	}
   
   public static void showMenu() //ó�� �������� �޴�
   {
		System.out.println("�����ϼ���...");
		System.out.println("1. ������ �Է�");
		System.out.println("2. ��ü����");
		System.out.println("3. �л�ã��");
		System.out.println("4. ����/����");
		System.out.print("����  >>");
   }
   public void inputData() //�Է��� ������ �����ֱ�
   {
	   while(true) 
	   {
		   System.out.println("�л� �̸�,�а�,�й�,������� �Է��ϼ���.(�Է��� , �� �����ϰ� ����� x)");
		   System.out.print(">> ");
		   try {
			   String text = StudentManager.sc.nextLine();
			   if(text.toLowerCase().equals("x")) 
			   {
				   System.out.println("�Է�����");
				   break;
			   }
			   StringTokenizer st = new StringTokenizer(text, ",");
			   String name = st.nextToken();//�̸�
			   String dept = st.nextToken(); //�а�
			   String id = st.nextToken();//�й�
			   double grade =Double.parseDouble(st.nextToken()); //����
			   array.add(new Student(name, dept, id, grade));
				   
		   }catch (NoSuchElementException e){
			   System.out.println("��Ȯ�ϰ� �Է����ּ���.");		   
		   }catch (NumberFormatException n) {
			   System.out.println("��Ȯ�ϰ� �Է����ּ���.");
		   }
	   }
   }
	 public void viewData() 
	 {
		 System.out.println("�л���ü����Ʈ.....");
		 for(Student student : array) {
				System.out.println("�̸�:" + student.getName());
				System.out.println("�а�:" + student.getMajor());
				System.out.println("�й�:" + student.getStudentNum());
				System.out.println("�������:" + student.getGradeAvg());
				System.out.println();
		 }		   
	   }
	 public void searchData() 
	 {
		 System.out.println("�л� ã��.....");
		 System.out.print("ã�� �л� �̸� >>");
		 String searhName = StudentManager.sc.next();
		 Student s = search(searhName);
		 if(s==null) 
		 {
			 System.out.println("ã�� �л� ����");
			 return;
		 }
		 System.out.println("�̸�:" + s.getName());
		System.out.println("�а�:" + s.getMajor());
		System.out.println("�й�:" + s.getStudentNum());
		System.out.println("�������:" + s.getGradeAvg());
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
					new ObjectOutputStream(new FileOutputStream(file));//file�� ��������.
																
		oos.writeObject(array); 
		System.out.println("����/����");
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
			   System.out.println("���α׷� ����");
			   System.exit(0);
			default : System.out.println("�Է¿���");
			}			
		}
	}
}