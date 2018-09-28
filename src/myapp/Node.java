package myapp;

public class Node {
    public int h=0;
    public String value=null;
    public Node left=null;
    public Node right=null;
    //构造结点
    public Node(String value,Node left,Node right) {
    	this.value=value;
    	this.left=left;
    	this.right=right;
    }
    //判断是否为符号结点
    public boolean isSymbolNode() {
    	
    	return "[+-×÷]".equals(this.value);
    	
    }
    //交换左右子树
    public void swap() {
    	
    }
    
}
