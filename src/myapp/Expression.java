package myapp;

import java.util.Random;
import java.util.regex.Pattern;

public class Expression {
    	Random random=new Random();
    	Random random2=new Random();
    	String[] operator=new String[] {"+","-","×","÷"};
    	int defualtRange=5;
    	int defualtNumOfQuestion=50;//
    	/*
    	 * 生成表达式
    	 * @return 表达式字符串
    	 */
    	public String expressionGenerate() {
    		int numOfFraction=random.nextInt(3)+2;//生成分数个数
        	int numOfOperator=numOfFraction-1;//生成符号个数
        	boolean bracketsGenerate=random2.nextBoolean();//是否随机生成括号
    		StringBuilder sb=new StringBuilder();
    			try {
            		for (int i=1;i<=numOfFraction;i++) {
            			if(i!=numOfFraction) {
            			    sb.append(new Fraction(random.nextInt(defualtRange),random.nextInt(defualtRange)).toString()+" ");
            			    sb.append(operator[random.nextInt(4)]+" ");
            			}else {
            				sb.append(new Fraction(random.nextInt(defualtRange),random.nextInt(defualtRange)).toString()+" ");
            				sb.append("=");
            			}
            		}
            		//分数三个或四个就生成括号
            		if(numOfFraction!=2&&bracketsGenerate) {
            			String[] s = sb.toString().split(" ");
            			if("+".equals(s[s.length-3])||"-".equals(s[s.length-3])) {
            				if(s.length==6) {
            					return s[0]+" "+s[1]+" ( "+s[2]+" "+s[3]+" "+s[4]+" ) "+s[5];
            				}else {
            					return s[0]+" "+s[1]+" "+s[2]+" "+s[3]+" ( "+s[4]+" "+s[5]+" "+s[6]+" ) "+s[7];
            				}
            			}
            		}
            		return sb.toString();
        		}catch(RuntimeException a) {
        			return expressionGenerate();
        		}
    	}
    	
    	
    	/*
    	 * 显示表达式
    	 */
    	public void display() {
    		int i=1;
    		while(i<=defualtNumOfQuestion) {
    		    System.out.println(expressionGenerate());
    		    i++;
    		}
    	}
  
}
