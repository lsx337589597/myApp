package myapp;

import java.util.Random;
import java.util.regex.Pattern;

public class Expression {
    	Random random=new Random();
    	Random random2=new Random();
    	String[] operator=new String[] {"+","-","��","��"};
    	int defualtRange=5;
    	int defualtNumOfQuestion=50;//
    	/*
    	 * ���ɱ��ʽ
    	 * @return ���ʽ�ַ���
    	 */
    	public String expressionGenerate() {
    		int numOfFraction=random.nextInt(3)+2;//���ɷ�������
        	int numOfOperator=numOfFraction-1;//���ɷ��Ÿ���
        	boolean bracketsGenerate=random2.nextBoolean();//�Ƿ������������
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
            		//�����������ĸ�����������
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
    	 * ��ʾ���ʽ
    	 */
    	public void display() {
    		int i=1;
    		while(i<=defualtNumOfQuestion) {
    		    System.out.println(expressionGenerate());
    		    i++;
    		}
    	}
  
}
