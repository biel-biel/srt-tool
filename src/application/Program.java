package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Subtitle;

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
		
		List<Subtitle> subtitleList = new ArrayList<Subtitle>();
		
		try {
			fr = new FileReader(originalSRTPath);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss,SSS");
			
			while(line != null) {
				try {
					int subtitleNumber = Integer.parseInt(line);
					Date timeBetween;
					System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Reading " + subtitleNumber);
					line = br.readLine();
					
					try {
						Date startTime = sdf1.parse(line.substring(0, 12));
						Date endTime = sdf1.parse(line.substring(17, 29));
						if (subtitleNumber == 1) {
							timeBetween = startTime;
							
							List<String> subtitleStringList = new ArrayList<String>();
							line = br.readLine();
							while(!(line.isBlank())) {
								subtitleStringList.add(line);
								line = br.readLine();
							}
							
							subtitleList.add(new Subtitle(startTime, endTime, timeBetween, subtitleNumber, subtitleStringList));
						}
						else {
							startTime = sdf1.parse(line.substring(0, 12));
							endTime = sdf1.parse(line.substring(17, 29));
							timeBetween = new Date(startTime.getTime() - subtitleList.get(subtitleNumber-2).getEndTime().getTime());
							
							List<String> subtitleStringList = new ArrayList<String>();
							line = br.readLine();
							while(!(line.isBlank())) {
								subtitleStringList.add(line);
								line = br.readLine();
							}
							
							subtitleList.add(new Subtitle(startTime, endTime, timeBetween, subtitleNumber, subtitleStringList));
						}
					}
					catch (ParseException e) {
						System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] An error has occurred while reading the file.");
						System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] Getting start and end time error.");
						System.out.println(e.getMessage());
						break;
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
