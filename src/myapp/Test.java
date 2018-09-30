package myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		BufferedWriter fw;
		BufferedWriter fw2;
		BufferedWriter fw3;
		//-e C:/Users/Administrator/Desktop/Exercises.txt -a C:/Users/Administrator/Desktop/Answers.txt
		Scanner scanner = new Scanner(System.in);
		System.out.println("MyApp.exe ");
		System.out.println("请输入命令生成题目数目'-n number'");
		System.out.println("请输入命令数值范围数目'-r number'");
		System.out.println("查询请输入'-e <exercisefile>.txt -a <answerfile>.txt'\n");
		Expression e=new Expression();
		//Exercise exercise=new Exercise();
		boolean b=true;
		while (b) {
			try {
				System.out.println("请输入命令：");
				String str=scanner.nextLine();
			    String[] input=str.trim().split(" ");
			    if(input.length==2) {
			    	if(Integer.valueOf(input[1])>0) {
			    		if(input[0].equals("-n")) {
			    			e.defualtNumOfQuestion=Integer.valueOf(input[1]);
			    		}else if(input[0].equals("-r")){
			    			e.defualtRange=Integer.valueOf(input[1]);
			    		}else {
			    			throw new Exception();
			    		}
			    		fw=new BufferedWriter(new FileWriter(new File("C:/Users/Administrator/Desktop/Exercises.txt")));
			    		fw2=new BufferedWriter(new FileWriter("C:/Users/Administrator/Desktop/Answers.txt"));
			    		  for(int i=1;i<=e.defualtNumOfQuestion;i++) {
		    					Node n = e.getCorrectTree();
		    					StringBuilder sb = new StringBuilder();	
		    					e.nodeTraToStr(n, sb);
		    					e.sum(n);
		    					fw2.write(i+"、"+n.value);
		    					fw2.newLine();
		    					fw2.flush();
		    					if(sb.toString().contains("(")) {
		    						sb.append(")");
		    					}
		    					fw.write(i+"、"+sb.toString()+"=");
		    					fw.newLine();
		    					fw.flush();
		    					}
			    		     fw.close();
			    			fw2.close();
			    			
			    	}else {
			    		throw new Exception();
			    	}
			    }else if(input.length==4&&input[0].equals("-e")&&input[2].equals("-a")){//检验答案
			    	File fileE=new File(input[1]);
			    	File fileA=new File(input[3]);
			    	if(fileE.exists()&&fileA.exists()) {
			    		BufferedReader bra=new BufferedReader(new FileReader(fileA));
			    		BufferedReader bre=new BufferedReader(new FileReader(fileE));
			    		fw3=new BufferedWriter(new FileWriter("C:/Users/Administrator/Desktop/Grade.txt"));
			    		//s为读入的题目
			    		String s;
			    		while((s=bre.readLine())!=null) {
			    			String ss[]=s.split("、");
			    			s=ss[1];
			    			//对每条题目进行计算
			    			Node tn=e.getCorrectTree(s);
			    			e.sum(tn);
			    			//cAnswer为正确答案
			    			String cAnswer=tn.value;
			    			//tAnswer为输入的答案
			    			String[] sss=bre.readLine().split("[、=]");
			    			String tAnswer=sss[2];
			    			
			    			if(cAnswer.equals(tAnswer)) {
			    				
			    				fw3.write(sss[0]);
				    			
			    			}
			    			
			    		}
			    		fw3.flush();
			    		fw3.close();
			    		bra.close();
			    		bre.close();
			    	}else
			    		throw new FileNotFoundException("文件不存在");
			    }else if("-q".equals(str)){
			    	System.out.println("已退出！");
			    	b=false;
			    }else {
			    	throw new Exception();
			    }
			}catch(Exception ex){
				System.out.println("命令错误，请重新输入命令\n");
			}
		}
			

	}
	
}
