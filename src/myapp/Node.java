package myapp;

public class Node {
    public int h=0;
    public String value=null;
    public Node left=null;
    public Node right=null;
    //������
    public Node(String value,Node left,Node right) {
    	this.value=value;
    	this.left=left;
    	this.right=right;
    }
    //�ж��Ƿ�Ϊ���Ž��
    public boolean isSymbolNode() {
    	
    	return "[+-����]".equals(this.value);
    	
    }
    //������������
    public void swap() {
    	
    }
    
}
