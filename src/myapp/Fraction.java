package myapp;

/**
 * @author ShoreLee
 * ������
 */
public class Fraction {
    int numerator;//����
    int denominator;//��ĸ
    boolean isNegative=false;//�Ƿ�Ϊ��
    /*
     * �������
     */
    public void createFraction(int numerator,int denominator) {
   	 if(denominator==0) {
   		 throw new RuntimeException("��ĸ����Ϊ0��");
   	 }
   //��¼�����ı�־
     int isNegative = (numerator*denominator<0)? -1 : 1;
     if(isNegative==-1) {
    	 this.isNegative=true;
     }
     numerator = Math.abs(numerator);
     denominator = Math.abs(denominator);
     int c = gcd(numerator, denominator);
     //��ֻ֤��a�Ż�С��0
     this.numerator = numerator / c ;
     this.denominator = denominator / c;
    }
    
    public Fraction(int numerator,int denominator) {
    	createFraction(numerator, denominator);
    }
    
    public Fraction(String farction) {
    	int numerator;
    	int denominator;
    	farction.trim();
    	int indexOfPoite=farction.indexOf("'");
    	int indexOfLine=farction.indexOf("/");
    	if(indexOfPoite!=-1) {
    		int integer=Integer.valueOf(farction.substring(0, indexOfPoite));
    		denominator=Integer.valueOf(farction.substring(indexOfLine+1));
    		numerator=integer*denominator+Integer.valueOf(farction.substring(indexOfPoite+1, indexOfLine));
    	}else if(indexOfLine!=-1) {
    		denominator=Integer.valueOf(farction.substring(indexOfLine+1));
    		numerator=Integer.valueOf(farction.substring(0,indexOfLine));
    	}else {
    		numerator=Integer.valueOf(farction);
    	    denominator=1;
    	}
    	createFraction(numerator, denominator);
    }
     /*
      * ���������������
      */
     public int gcd(int a,int b) {
    	 if(a%b==0) {
    		 return b;
    	 }else {
    		 return gcd(b,a%b);
    	 }
     }
     /*
      * �ӷ�
      */
     public Fraction add(Fraction f) {
    	 return new Fraction(this.numerator*f.denominator+this.denominator*f.numerator,
    			 this.denominator*f.denominator);
     }
     /*
      * ����
      */
     public Fraction subtract(Fraction f) {
    	 return new Fraction(this.numerator*f.denominator-this.denominator*f.numerator,
    			 this.denominator*f.denominator);
     }
     /*
      * �˷�
      */
     public Fraction multiply(Fraction f) {
    	 return new Fraction(this.numerator*f.numerator,this.denominator*f.denominator);
     }
     /*
      * ����
      */
     public Fraction devide(Fraction f) {
    	 return new Fraction(this.numerator*f.denominator,this.denominator*f.numerator);
     }
//     /**
//      * ����ֵ
//      */
//     public static Fraction abs(Fraction f) {
//         int numerator = Math.abs(f.numerator);
//         
//         
//     }
     @Override
     public String toString() {
    	 if(denominator==1) {
    		 return String.valueOf(numerator);
    	 }else
    		 if(numerator>denominator) {
    			 return String.format("%d'%d/%d", numerator/denominator,numerator%denominator,denominator);
    		 }else {
    			 return String.format("%d/%d", numerator,denominator);
    		 }
     }
}
