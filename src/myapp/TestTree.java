package myapp;

import java.util.Scanner;

public class TestTree {

	public static void main(String[] args) {
		System.out.println("����������������Ŀ��Ŀ'-n number'");
		System.out.println("MyApp.exe ");
		System.out.println("������������ֵ��Χ��Ŀ'-r number'");
		System.out.println("MyApp.exe ");
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNext()) {
			
		int i=scanner.nextInt();
		}
		
		
		
		Expression e=new Expression();
	
		    for(int i=1;i<=e.defualtNumOfQuestion;i++) {
		    
			Node n = e.getCorrectTree();
			StringBuilder sb = new StringBuilder();	
			e.nodeTraToStr(n, sb);
			if(sb.toString().contains("(")) {
				sb.append(")");
			}
			System.out.println(i+"��"+sb.toString());
			
		}
		
	}

}
