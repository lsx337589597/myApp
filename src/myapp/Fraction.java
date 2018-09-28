package myapp;

/**
 * @author ShoreLee
 * ������
 */
public class Fraction {
    int numerator;//����
    int denominator;//��ĸ
    /*
     * �������
     */
    public void createFraction(int numerator,int denominator) {
   	 if(denominator==0) {
   		 throw new RuntimeException("��ĸ����Ϊ0��");
   	 }
   	 int mcf=gcd(numerator,denominator);
   	 this.numerator=numerator/mcf;
   	 this.denominator=denominator/mcf;
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
