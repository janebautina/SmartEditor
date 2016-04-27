package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FilesReading {
	static String str;
	static Scanner sc;
    public static void main(String [] args) {
    	/*sc = new Scanner(System.in);
    	try(FileWriter wt = new FileWriter("mytext.txt")) {
    		do{
    			System.out.println("Writing...");
    			str = sc.nextLine();
    			if(str.compareTo("stop")== 0) break;
    			str = str + "\r\n";
    			wt.write(str);
    		} 
    		while(str.compareTo("stop") != 0);
    	}
    	catch(IOException ex){
    		System.out.println(ex);
    	}
    	*/
    	try(Scanner scan = new Scanner(new FileReader("mytext.txt"))){
    		while (scan.hasNext()){
    			System.out.println(scan.nextLine());
    		}	
    	} catch(IOException exp){
    		System.out.println(exp);
    	}
    }
}
