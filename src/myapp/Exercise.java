package myapp;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ShoreLee
 * C:\Users\Administrator\Desktop\Exercises.txt
 *
 */
public class Exercise {
	public void Write(String str) throws IOException {
		BufferedWriter fw=new BufferedWriter(new FileWriter("C:/Users/Administrator/Desktop/Exercises.txt"));
	    fw.write(str);
	    
	    fw.newLine();
	    
	    fw.close();
	}
	
    
}
