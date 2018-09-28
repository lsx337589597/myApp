package myapp;

/**
 * @author ShoreLee
 * 分数类
 */
public class Fraction {
    int numerator;//分子
    int denominator;//分母
    /*
     * 构造分数
     */
    public void createFraction(int numerator,int denominator) {
   	 if(denominator==0) {
   		 throw new RuntimeException("分母不能为0！");
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
      * 求两数的最大公因数
      */
     public int gcd(int a,int b) {
    	 if(a%b==0) {
    		 return b;
    	 }else {
    		 return gcd(b,a%b);
    	 }
     }
     /*
      * 加法
      */
     public Fraction add(Fraction f) {
    	 return new Fraction(this.numerator*f.denominator+this.denominator*f.numerator,
    			 this.denominator*f.denominator);
     }
     /*
      * 减法
      */
     public Fraction subtract(Fraction f) {
    	 return new Fraction(this.numerator*f.denominator-this.denominator*f.numerator,
    			 this.denominator*f.denominator);
     }
     /*
      * 乘法
      */
     public Fraction multiply(Fraction f) {
    	 return new Fraction(this.numerator*f.numerator,this.denominator*f.denominator);
     }
     /*
      * 除法
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
