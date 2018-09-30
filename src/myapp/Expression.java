package myapp;

import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.xml.parsers.FactoryConfigurationError;

public class Expression {
    	Random random=new Random();
    	Random random2=new Random();
    	private StringBuilder s =new StringBuilder();
    	String[] operator=new String[] {"+","-","×","÷"};
    	int defualtRange=20;
    	int defualtNumOfQuestion=10;//
    	
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
            			    sb.append(new Fraction(random.nextInt(defualtRange),random.nextInt(defualtRange-1)+1).toString()+" ");
            			    sb.append(operator[random.nextInt(4)]+" ");
            			}else {
            				sb.append(new Fraction(random.nextInt(defualtRange),random.nextInt(defualtRange-1)+1).toString());
            			}
            		}
            		//分数三个或四个就随机生成括号
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
    	 * 获取正确表达式的树
    	 */
    	public Node getCorrectTree() {
    		Node node=null;//对生成的表达式建树
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
    		ridNegative(node.clone(),node);//去掉负数
    		return node;
    	}
    	public Node getCorrectTree(String s) {
    		Node node=null;//对生成的表达式建树
    		while(true) {
    			
    			node = buildTree(s);
				ridMeaningless(node.clone(), node);
				if(isTrue) {
					break;
				}else {
					isTrue = true;
				}
			}
    		ridNegative(node.clone(),node);//去掉负数
    		return node;
    	}
    	/*
    	 * 输入树的结点，生成正确的表达式
    	 * @param Node
    	 * @return String表达式
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
    	 * 对表达式建树
    	 * @param 表达式雏形
    	 * @return 树的根节点
    	 */
    	public Node buildTree(String expression) {
    		String ex[]=expression.trim().split(" ");
            if(ex.length==3) {//两位操作数的情况(无括号)
                Node node0=new Node(ex[0],null,null);
                Node node2=new Node(ex[2],null,null);
                Node node1=new Node(ex[1],node0,node2);
                return node1;
            }else if(ex.length==5) {//三位操作数的情况（无括号）
            	//把前三个字符构成树，索引为1的作为根，索引为0或2的分别作左右子树
            	Node node0=new Node(ex[0],null,null);
            	Node node2=new Node(ex[2],null,null);
            	Node node1=new Node(ex[1],node0,node2);
            	if("+".equals(ex[1])||"-".equals(ex[1])) {//第一个符号为加或减
            		if("+".equals(ex[3])||"-".equals(ex[3])) {//第二个符号为加或减
            			//把第二个的符号作为新根连接旧根和最后一个结点
            			Node node4=new Node(ex[4],null,null);
            			Node node3=new Node(ex[3],node1,node4);
            			return node3;
            		}else {//第二个符号为乘或除
            			//第二个符号作为父节点连接相邻的两数，再作为根节点的右子树
            			Node node4=new Node(ex[4],null,null);
            			Node node3=new Node(ex[3],node2,node4);
            			node1.right=node3;
            			return node1;
            		}
            	}else{//第一个符号为乘或除
            			//把第二个的符号作为新根连接旧根和最后一个结点
            			Node node4=new Node(ex[4],null,null);
            			Node node3=new Node(ex[3],node1,node4);
            			return node3; 		
            	}
            }else if(ex.length==7) {//三位操作数有括号和四位操作数的情况
            	if("(".equals(ex[2])) {//三位操作数有括号
            		//必定是先算符号
            		Node node0=new Node(ex[0],null,null);
            		Node node2=new Node(ex[3],null,null);
            		Node node4=new Node(ex[5],null,null);
            		Node node3=new Node(ex[4],node2,node4);
            		Node node1=new Node(ex[1],node0,node3);
            		return node1;
            	}else {//四位操作数
            		Node node0=new Node(ex[0],null,null);
            		Node node1=new Node(ex[1],node0,null);
            		if("+".equals(ex[1])||"-".equals(ex[1])) {//第一个符号为加或减
            			if("+".equals(ex[3])||"-".equals(ex[3])) {//第二个符号为加或减
            				//把前三个字符构成树，索引为1的作为根，索引为0或2的分别作左右子树
            				Node node2=new Node(ex[2],null,null);
            				node1.right=node2;
            				if("+".equals(ex[5])||"-".equals(ex[5])) {//第三个符号为加或减
            					Node node4=new Node(ex[4],null,null);
            					Node node3=new Node(ex[3],node1,node4);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node3,node6);
            					return node5;
            				}else {//第三个符号为乘或除
            					Node node4=new Node(ex[4],null,null);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node4,node6);
            					Node node3=new Node(ex[3],node1,node5);
            					return node3;
            				}
            			}else {//第二个符号为乘或除
            				//必定有第二和第三个操作数相乘或相除
            				Node node2=new Node(ex[2],null,null);
            				Node node4=new Node(ex[4],null,null);
            				Node node3=new Node(ex[3],node2,node4);
            				if("+".equals(ex[5])||"-".equals(ex[5])) {//第三个符号为加或减
            					node1.right=node3;
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node1,node6);
            					return node5;
            				}else {//第三个符号为乘或除
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node3,node6);
            					node1.right=node5;
            					return node1;
            				}
            			}
            			
            		}else {//第一个符号为乘或除
            			//必定有前两个操作数向运算，把前三个字符构成树，索引为1的作为根，索引为0或2的分别作左右子树
        				Node node2=new Node(ex[2],null,null);
        				node1.right=node2;
            			if("+".equals(ex[3])||"-".equals(ex[3])) {//第二个符号为加或减
            				if("+".equals(ex[5])||"-".equals(ex[5])) {//第三个符号为加或减
            					Node node4=new Node(ex[4],null,null);
            					Node node3=new Node(ex[3],node1,node4);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node3,node6);
            					return node5;
            				}else {//第三个符号为乘或除
            					Node node4=new Node(ex[4],null,null);
            					Node node6=new Node(ex[6],null,null);
            					Node node5=new Node(ex[5],node4,node6);
            					Node node3=new Node(ex[3],node1,node5);
            					return node3;
            				}
            			}else{//第二个符号为乘或除
            				//第一第二个符号都是乘除的话必定按顺序运算
            				Node node4=new Node(ex[4],null,null);
        					Node node3=new Node(ex[3],node1,node4);
        					Node node6=new Node(ex[6],null,null);
        					Node node5=new Node(ex[5],node3,node6);
        					return node5;
            			}
            		}
            	}
            	
            }else {//四个操作数加括号
            	//必定有括号里面的数相加减，定义一个node6，左右孩子为最后两个数
            	Node node5=new Node(ex[5],null,null);
            	Node node7=new Node(ex[7],null,null);
            	Node node6=new Node(ex[6],node5,node7);
            	if(("+".equals(ex[1])||"-".equals(ex[1]))&&("×".equals(ex[3])||"÷".equals(ex[3]))) {
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
    	 * 计算某结点的两个叶子结点
    	 * @Param Node
    	 * @return 
    	 */
    	public Fraction calculate(Node node) {
    		if("+".equals(node.value)) {
    			return new Fraction(node.left.value).add(new Fraction(node.right.value));
    		}else if("-".equals(node.value)) {
    			return new Fraction(node.left.value).subtract(new Fraction(node.right.value));
    		}else if("×".equals(node.value)) {
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
    	 * 去掉负数
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
    	 * 去掉除数为0
    	 * @param Node
    	 */
    	boolean isTrue=true;
    	public void ridMeaningless(Node node1,Node node2) {
    		if(Node.isSymbolNode(node1)) {
    			ridMeaningless(node1.left,node2.left);
    			ridMeaningless(node1.right,node2.right);
    			if("÷".equals(node1.value)&&"0".equals(node1.right.value)) {//
    				Node.swap(node1);
    				Node.swap(node2);
    				if("0".equals(node1.right.value)) {//如果交换之后还是0,不好意思，老子只能把你丢弃了
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
    	 * 以下两个方法比较符号
    	 */
    	public static int getPriority(String s) {
    		switch (s) {
			case "+":
			case "-":
				return -1;
			case "×":
			case "÷":
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
