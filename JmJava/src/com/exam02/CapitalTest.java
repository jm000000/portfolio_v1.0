package com.exam02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CapitalTest {
	public static Scanner sc = new Scanner(System.in);
	private HashMap<String,String> map = new HashMap<String, String>();
	File dir, file;
	public CapitalTest()
	{		
		dir = new File("src\\com\\exam02");
		file = new File(dir, "capitalTest.txt");
		map.clear(); //map 깨끗하게 지워주기
		try {
			if(!file.exists()) //파일 없음
			{
				file.createNewFile(); //파일생성
				return;
			}
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext())
			{
				String country = scanner.next(); //나라
				String capital = scanner.next(); //수도
				map.put(country, capital);
			}
			scanner.close();
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public  static void showMenu() 
	{
		System.out.println("**** 수도 맞추기 게임을 시작합니다. ****");
		System.out.print("1. 입력, 2.퀴즈, 3. 파일저장 및 종료");
		System.out.print("선택 >> ");
   }
	
	private void input() //나라와 수도 알려준다.
	{
		while(true) {
			System.out.println("나라와 수도 입력(종료는  x)>>");
			String cont = CapitalTest.sc.next(); //나라
			if(cont.toUpperCase().equals("X")) break;
				//맴에 입력한 나라가 있는지 확인
				if(map.containsKey(cont)) { //map 에 입력한 나라 있음
				System.out.println("이미 입력한 나라입니다.");
				continue; //반복문 다시 실행 해라
			}
			String sudo = CapitalTest.sc.next(); //수도
			 map.put(cont, sudo);
		}
	}
	
	//컴퓨터가 랜덤하게 도시를 알려주면 그에 대한
    // 수도를 입력하면 정답, 오답 판단해줌
	private void quiz() 
	{
		Set<String>  set = map.keySet();
		//배열로 변환
		Object[] arr = set.toArray(); // set을 배열 형태로 변환(순서를 알기위해)
		while(true) {
			int n = (int)(Math.random()*map.size());
			String city =(String) arr[n];//나라 이름
		    String dosi = map.get(city);//수도이름(답)
		    
		    //문제출제
		    System.out.println(city +" 의 수도는 ? 종료는 x >>");
		    String answer = sc.next();
		    if(answer.toLowerCase().equals("x")) break;
		    if(answer.equals(dosi)) {
		    	System.out.println("정답");
		    }else {
		    	System.out.println("틀렸습니다.");
		    }		    
  		}		
	}
	
	//해쉬맴을 파일에 저장하고 종료하는 메소드
	  private void save() 
	  {
		  FileWriter fw=null;
		  //System.out.println(map);
		  try {
			 fw = new FileWriter(file);
			 Set<String> set =map.keySet();  // 나라만 가져옴
			 Iterator<String> it = set.iterator();//나라를 방문하기 위해
			   while(it.hasNext()) { // it.hasNext() 나라가 있는동안(?)
				   String key = it.next(); //나라가져옴
				   String value = map.get(key) ;// 수도 가져옴
				   fw.write(key+"  "); //나라 출력
				   fw.write(value+"\n"); //수도출력
			   }
			   fw.close();
			   System.out.println("종료");
			   System.exit(0);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일저장 오류");
		}
	}	  
	  
	public static void main(String[] args) {
		CapitalTest ct = new CapitalTest();
		while(true)
		{
			CapitalTest.showMenu();
			int choice = CapitalTest.sc.nextInt();
			switch (choice)
			{
			case 1: ct.input(); break;
			case 2: ct.quiz(); break;
			case 3: ct.save(); break;
			default: System.out.println("입력오류");
			}
		}
	}
}
