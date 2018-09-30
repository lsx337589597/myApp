package myapp;

import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.xml.parsers.FactoryConfigurationError;

public class Expression {
    	Random random=new Random();
    	Random random2=new Random();
    	private StringBuilder s =new StringBuilder();
    	String[] operator=new String[] {"+","-","��","��"};
    	int defualtRange=20;
    	int defualtNumOfQuestion=10;//
    	
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
            			    sb.append(new Fraction(random.nextInt(defualtRange),random.nextInt(defualtRange-1)+1).toString()+" ");
            			    sb.append(operator[random.nextInt(4)]+" ");
            			}else {
            				sb.append(new Fraction(random.nextInt(defualtRange),random.nextInt(defualtRange-1)+1).toString());
            			}
            		}
            		//�����������ĸ��������������
            		if(numOfFraction!=2&&bracketsGenerate) {
            			String[] s = sb.toString().split(" ");
            			if("+".equals(s[s.length-2])||"-".equals(s[s.length-2])) {
            				if(s.length==5) {
            					return s[0]+" "+s[1]+" ( "+s[2]+" "+s[3]+" "+s[4]+" ) ";
            				}else {
            					return s[0]+" "+s[1]+" "+s[2]+" "+s[3]+" ( "+s[4]+" "+s[5]+" "+s[6]+" ) ";
            				}
            			}
            		}
  
            		return sb.toString();
        		}catch(RuntimeException a) {
        			return expressionGenerate();
        		}
    	}
    	/*
    	 * ��ȡ��ȷ���ʽ����
    	 */
    	public Node getCorrectTree() {
    		Node node=null;//�����ɵı��ʽ����
    		while(true) {
    			String s=expressionGenerate();
    			node = buildTree(s);
				ridMeaningless(node.clone(), node);
				if(isTrue) {
					break;
				}else {
					isTrue = true;
				}
			}
    		ridNegative(node.clone(),node);//ȥ������
    		return node;
    	}
    	public Node getCorrectTree(String s) {
    		Node node=null;//�����ɵı��ʽ����
    		while(true) {
    			
    			node = buildTree(s);
				ridMeaningless(node.clone(), node);
				if(isTrue) {
					break;
				}else {
					isTrue = true;
				}
			}
    		ridNegative(node.clone(),node);//ȥ������
    		return node;
    	}
    	/*
    	 * �������Ľ�㣬������ȷ�ı��ʽ
    	 * @param Node
    	 * @return String���ʽ
    	 */
    	public void nodeTraToStr(Node node,StringBuilder sb) {
    		if(node!=null) {
    			nodeTraToStr(node.left, sb);
    			if(Node.isSymbolNode(node)&&Node.isSymbolNode(node.right)&&(!Node.isSymbolNode(node.right.right))&&(Node.isSymbolNode(node.left))
    					&&Expression.comparePriority(node.value, node.right.value)>=0) {
    				
    				sb.append(node.value+" ( ");
    			}else{
    				sb.append(node.value+" ");
    			}
    			
    			nodeTraToStr(node.right, sb);
    		}
    	}
    	/*
    	 * �Ա��ʽ����
    	 * @param ���ʽ����
    	 * @return ���ĸ��ڵ�
    	 */
    	public Node buildTree(String expression) {
    		String ex[]=expression.trim().split(" ");
            if(ex.length==3) {//��λ�����������(������)
                Node node0=new Node(ex[0],null,null);
                Node node2=new Node(ex[2],null,null);
                Node node1=new Node(ex[1],node0,node2);
                return node1;
            }else if(ex.length==5) {//��λ������������������ţ�
            	//��ǰ�����ַ�������������Ϊ1����Ϊ��������Ϊ0��2�ķֱ�����������
            	Node node0=new Node(ex[0],null,null);
            	Node node2=new Node(ex[2],null,null);
            	Node node1=new Node(ex[1],node0,node2);
            	if("+".equals(ex[1])||"-".equals(ex[1])) {//��һ������Ϊ�ӻ��
            		if("+".equals(ex[3])||"-".equals(ex[3])) {//�ڶ�������Ϊ�ӻ��
            			//�ѵڶ����ķ�����Ϊ�¸����Ӿɸ������һ�����
            			Node node4=new Node(ex[4],null,null);
            			Node node3=new Node(ex[3],node1,node4);
            			return node3;
            		}else {//�ڶ�������Ϊ�˻��
            			//�ڶ���������Ϊ���ڵ��������ڵ�����������Ϊ���ڵ��������
            			Node node4=new Node(ex[4],null,null);
            			Node node3=new Node(ex[3],node2,node4);
            			node1.right=node3;
            			return node1;
            		}
            	}else{//��һ������Ϊ�˻��
            			//�ѵڶ����ķ�����Ϊ�¸����Ӿɸ������һ�����
            			Node node4=new Node(ex[4],null,null);
            			Node node3=new Node(ex[3],node1,node4);
            			return node3; 		
            	}
            }else if(ex.length==7) {//��λ�����������ź���λ�����������
            	if("(".equals(ex[2])) {//��λ������������
            		//�ض����������
            		Node node0=new Node(ex[0],null,null);
            		Node node2=new Node(ex[3],null,null);
            		Node node4=new Node(ex[5],null,null);
            		Node node3=new Node(ex[4],node2,node4);
            		Node node1=new Node(ex[1],node0,node3);
            		return node1;
            	}else {//��λ������
            		Node node0=new Node(ex[0],null,null);
            		Node node1=new Node(ex[1],node0,null);
            		if("+".equals(ex[1])||"-".equals(ex[1])) {//��һ������Ϊ�ӻ��
            			if("+".equals(ex[3])||"-".equals(ex[3])) {//�ڶ�������Ϊ�ӻ��
            				//��ǰ�����ַ�������������Ϊ1����Ϊ��������Ϊ0��2�ķֱ�����������
            				Node node2=new Node(ex[2],null,null);
            				node1.right=node2;
            				if("+".equals(ex[5])||"-".equals(ex[5])) {//����������Ϊ�ӻ��
            					Node node4=new Node(ex[4],null,null);
            					Node node3=new Node(ex[3],node1,node4);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node3,node6);
            					return node5;
            				}else {//����������Ϊ�˻��
            					Node node4=new Node(ex[4],null,null);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node4,node6);
            					Node node3=new Node(ex[3],node1,node5);
            					return node3;
            				}
            			}else {//�ڶ�������Ϊ�˻��
            				//�ض��еڶ��͵�������������˻����
            				Node node2=new Node(ex[2],null,null);
            				Node node4=new Node(ex[4],null,null);
            				Node node3=new Node(ex[3],node2,node4);
            				if("+".equals(ex[5])||"-".equals(ex[5])) {//����������Ϊ�ӻ��
            					node1.right=node3;
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node1,node6);
            					return node5;
            				}else {//����������Ϊ�˻��
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node3,node6);
            					node1.right=node5;
            					return node1;
            				}
            			}
            			
            		}else {//��һ������Ϊ�˻��
            			//�ض���ǰ���������������㣬��ǰ�����ַ�������������Ϊ1����Ϊ��������Ϊ0��2�ķֱ�����������
        				Node node2=new Node(ex[2],null,null);
        				node1.right=node2;
            			if("+".equals(ex[3])||"-".equals(ex[3])) {//�ڶ�������Ϊ�ӻ��
            				if("+".equals(ex[5])||"-".equals(ex[5])) {//����������Ϊ�ӻ��
            					Node node4=new Node(ex[4],null,null);
            					Node node3=new Node(ex[3],node1,node4);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node3,node6);
            					return node5;
            				}else {//����������Ϊ�˻��
            					Node node4=new Node(ex[4],null,null);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node4,node6);
            					Node node3=new Node(ex[3],node1,node5);
            					return node3;
            				}
            			}else{//�ڶ�������Ϊ�˻��
            				//��һ�ڶ������Ŷ��ǳ˳��Ļ��ض���˳������
            				Node node4=new Node(ex[4],null,null);
        					Node node3=new Node(ex[3],node1,node4);
        					Node node6=new Node(ex[6],null,null);
        					Node node5=new Node(ex[5],node3,node6);
        					return node5;
            			}
            		}
            	}
            	
            }else {//�ĸ�������������
            	//�ض����������������Ӽ�������һ��node6�����Һ���Ϊ���������
            	Node node5=new Node(ex[5],null,null);
            	Node node7=new Node(ex[7],null,null);
            	Node node6=new Node(ex[6],node5,node7);
            	if(("+".equals(ex[1])||"-".equals(ex[1]))&&("��".equals(ex[3])||"��".equals(ex[3]))) {
            		Node node2=new Node(ex[2],null,null);
            		Node node3=new Node(ex[3],node2,node6);
            		Node node0=new Node(ex[0],null,null);
            		Node node1=new Node(ex[1],node0,node3);
            		return node1;
            	}else {
            		Node node0=new Node(ex[0],null,null);
            		Node node2=new Node(ex[2],null,null);
            		Node node1=new Node(ex[1],node0,node2);
            		Node node3=new Node(ex[3],node1,node6);
            		return node3;
            	}
            }
		}
    	/*
    	 * ����ĳ��������Ҷ�ӽ��
    	 * @Param Node
    	 * @return 
    	 */
    	public Fraction calculate(Node node) {
    		if("+".equals(node.value)) {
    			return new Fraction(node.left.value).add(new Fraction(node.right.value));
    		}else if("-".equals(node.value)) {
    			return new Fraction(node.left.value).subtract(new Fraction(node.right.value));
    		}else if("��".equals(node.value)) {
    			return new Fraction(node.left.value).multiply(new Fraction(node.right.value));
    		}else
    			return new Fraction(node.left.value).devide(new Fraction(node.right.value));
    	}
    	/**
    	 * @param root
    	 * @return Fraction
    	 */
//    	public String getAnswers() {
//    		return getAnswers(root).toString();
//    	}
//    	public Fraction getAnswers(Node node) {
//    		if(Node.isSymbolNode(node)) {
//    			getAnswers(node.left);
//    			getAnswers(node.right);
//    			return calculate(node);
//    			
//    		}
//			return new Fraction(node.value);
//    	}
//    	
    	public  void sum(Node node2) {
    		if(Node.isSymbolNode(node2)) {
			sum(node2.left);
			sum(node2.right);
			Fraction f =calculate(node2);
			node2.value=f.toString();
			
		    }
    		
    	}
    	
    	/*
    	 * ȥ������
    	 * @param Node
    	 */
    	public void ridNegative(Node node1,Node node2) {
    		if(Node.isSymbolNode(node1)) {
    			ridNegative(node1.left,node2.left);
    			ridNegative(node1.right,node2.right);
    			Fraction f=calculate(node1);
    			if(f.isNegative) {
    				Node.swap(node1);
    				Node.swap(node2);
    			}
    			node1.value=f.toString();
    		}
    	}
    	/*
    	 * ȥ������Ϊ0
    	 * @param Node
    	 */
    	boolean isTrue=true;
    	public void ridMeaningless(Node node1,Node node2) {
    		if(Node.isSymbolNode(node1)) {
    			ridMeaningless(node1.left,node2.left);
    			ridMeaningless(node1.right,node2.right);
    			if("��".equals(node1.value)&&"0".equals(node1.right.value)) {//
    				Node.swap(node1);
    				Node.swap(node2);
    				if("0".equals(node1.right.value)) {//�������֮����0,������˼������ֻ�ܰ��㶪����
//    					node1.right.value="1";
//    					node2.right.value="1";
    					node1.value = "1";
    					isTrue = false;
    					return;
    				}
    			}
    			Fraction f=calculate(node1);
    			node1.value=f.toString();	
    		}

    	}
    	
    	/*
    	 * �������������ȽϷ���
    	 */
    	public static int getPriority(String s) {
    		switch (s) {
			case "+":
			case "-":
				return -1;
			case "��":
			case "��":
				return 1;
			default:
				return 0;
			}
    	}
    	public static int comparePriority(String s1,String s2) {
    		if(Expression.getPriority(s1)>Expression.getPriority(s2)) {
    			return 1;
    		}else if(Expression.getPriority(s1)<Expression.getPriority(s2)) {
    			return -1;
    		}else {
    			return 0;
    		}
    	}
  
}
