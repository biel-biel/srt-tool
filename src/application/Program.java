package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting program...");
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("HH:mm:ss");
		
		System.out.println("[" + fmt.format(LocalDateTime.now()) + "] [System/main] Project 'str-tool' by Gabriel Forneck");
		
		System.out.println();
		System.out.println("[" + fmt.format(LocalDateTime.now()) + "] [System/main] Enter the path of the .srt file:");
		String originalSTRPath = sc.nextLine();
		
		System.out.println("[" + fmt.format(LocalDateTime.now()) + "] [System/main] Reading...");
		BufferedReader br = null;
		FileReader fr = null;
		
		sc.close();
		System.out.println("[System/main] Program ended successfully");
	}

}
