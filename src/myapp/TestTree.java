package myapp;

import java.util.Scanner;

public class TestTree {

	public static void main(String[] args) {
		System.out.println("请输入命令生成题目数目'-n number'");
		System.out.println("MyApp.exe ");
		System.out.println("请输入命令数值范围数目'-r number'");
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
			System.out.println(i+"、"+sb.toString());
			
		}
		
	}

}
