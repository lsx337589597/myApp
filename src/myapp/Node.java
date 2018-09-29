package myapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Node implements Serializable{
    public String value=null;
    public Node left=null;	
    public Node right=null;
    /*
     * ��¡
     * @see java.lang.Object#clone()
     */
    public Node clone() {   
        ByteArrayOutputStream byteOut = null;   
        ObjectOutputStream objOut = null;   
        ByteArrayInputStream byteIn = null;   
        ObjectInputStream objIn = null;   
        try {   
            byteOut = new ByteArrayOutputStream();    
            objOut = new ObjectOutputStream(byteOut);    
            objOut.writeObject(this);   
            byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
            objIn = new ObjectInputStream(byteIn);   
            return (Node) objIn.readObject();   
        } catch (IOException e) {   
            throw new RuntimeException("Clone Object failed in IO.",e);      
        } catch (ClassNotFoundException e) {   
            throw new RuntimeException("Class not found.",e);      
        } finally{   
            try{   
                byteIn = null;   
                byteOut = null;   
                if(objOut != null) objOut.close();      
                if(objIn != null) objIn.close();      
            }catch(IOException e){      
            }      
        }   
    }  
    //������
    public Node(String value,Node left,Node right) {
    	this.value=value;
    	this.left=left;
    	this.right=right;
    }
    //�ж��Ƿ�Ϊ���Ž��
    public static boolean isSymbolNode(Node n) {
    	
    	return "+".equals(n.value)||"-".equals(n.value)||"��".equals(n.value)||"��".equals(n.value);
    }
    /*
     * ������������
     */
    public static void swap(Node n) {
    	Node temp;
    	temp=n.left;
    	n.left=n.right;
    	n.right=temp;
    }
    /*
     * ������
     * @param ���ĸ��ڵ�
     * @return ����
     */
    public static int high(Node n) {
    	if(n==null)
    		return 0;
    	return Math.max(high(n.left), high(n.right))+1;
    }
    /*
     * �������
     */
    public static void postOrder(Node n) {
    	if (n!= null)
    	{
    		postOrder(n.left);
    		postOrder(n.right);
    		System.out.println(n.value);
    	}

    }
    	 
}
    

