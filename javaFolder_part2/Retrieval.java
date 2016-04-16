package hw2_part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeSet;

public class Retrieval {
	public static void main(String[] args) throws Exception {
		
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("input your query?");
	     String input = scanner.nextLine();
		
	     System.out.println(input);
	     Function f =  new Function();
	     f.rankOR(input);
		 System.out.println("done!");
		
	
				
		
	}

}
