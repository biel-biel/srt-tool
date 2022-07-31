package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		DateTimeFormatter fmt1 =  DateTimeFormatter.ofPattern("HH:mm:ss");
		
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Project 'str-tool' by Gabriel Forneck");
		
		System.out.println();
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Enter the path of the .srt file:");
		String originalSRTPath = sc.nextLine();
		
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Reading...");
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader(originalSRTPath);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			DateTimeFormatter fmt2 =  DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
			
			while(line != null) {
				try {
					int subtitleNumber = Integer.parseInt(line);
					LocalDateTime timeBetween;
					System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Reading " + subtitleNumber);
					line = br.readLine();
					try {
						LocalDateTime startTime = LocalDateTime.parse(line.substring(0, 12), fmt2);
						LocalDateTime endTime = LocalDateTime.parse(line.substring(17, 29), fmt2);
						if (subtitleNumber == 1) {
							timeBetween = startTime;
							
						}
					}
					catch (StringIndexOutOfBoundsException e) {
						System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] An error has occurred while reading the file.");
						System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] Getting start and end time error.");
						System.out.println(e.getMessage());
						break;
					}
				}
				catch (NumberFormatException e) {
					System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] An error has occurred while reading the file.");
					System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] Getting subtitle number error.");
					System.out.println(e.getMessage());
					break;
				}
				line = br.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] An error has occurred while reading the file:");
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (br != null)
						br.close();
				if (fr != null)
						fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		sc.close();
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/END] Program ended successfully");
	}

}
