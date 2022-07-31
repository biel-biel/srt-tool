package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss,SSS");
		
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Project 'str-tool' by Gabriel Forneck");
		
		System.out.println();
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Enter the path of the .srt file:");
		System.out.print("> ");
		String originalSRTPath = sc.nextLine();
		
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Reading...");
		BufferedReader br = null;
		FileReader fr = null;
		
		List<Subtitle> subtitleList = new ArrayList<Subtitle>();
		boolean error = false;
		
		try {
			fr = new FileReader(originalSRTPath);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
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
						error = true;
						break;
					}
					catch (StringIndexOutOfBoundsException e) {
						System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] An error has occurred while reading the file.");
						System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] Getting start and end time error.");
						System.out.println(e.getMessage());
						error = true;
						break;
					}
				}
				catch (NumberFormatException e) {
					System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] An error has occurred while reading the file.");
					System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] Getting subtitle number error.");
					System.out.println(e.getMessage());
					error = true;
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
		
		if (error == false) {
			System.out.println("");
			System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Success! :)");
			System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Enter the time change percentage:");
			System.out.print("> ");
			Double timeChangePercentage = sc.nextDouble();
			timeChangePercentage = timeChangePercentage/100;
			sc.nextLine();
			
			System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Working...");
			List<Subtitle> modifiedSubtitleList = new ArrayList<Subtitle>();
			
			for (Subtitle s : subtitleList) {
				System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Working " + s.getSubtitleNumber());
				
				if (s.getSubtitleNumber() == 1) {
					Date timeBetween = new Date(Math.round(s.getTimeBetween().getTime() * timeChangePercentage));
					Date startTime = timeBetween;
					Date endTime = new Date(startTime.getTime() + Math.round(s.getDuration().getTime() * timeChangePercentage));
					modifiedSubtitleList.add(new Subtitle(startTime, endTime, timeBetween, s.getSubtitleNumber(), s.getSubtitleStringList()));
				}
				else {
					Date timeBetween = new Date(Math.round(s.getTimeBetween().getTime() * timeChangePercentage));
					Date startTime = new Date(subtitleList.get(s.getSubtitleNumber()-2).getEndTime().getTime() + s.getTimeBetween().getTime());
					Date endTime = new Date(startTime.getTime() + Math.round(s.getDuration().getTime() * timeChangePercentage));
					modifiedSubtitleList.add(new Subtitle(startTime, endTime, timeBetween, s.getSubtitleNumber(), s.getSubtitleStringList()));
				}
			}
			
			System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Writing...");
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\temp\\out.srt"))) {
				for (Subtitle s : modifiedSubtitleList) {
					System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] Writing " + s.getSubtitleNumber());
					bw.write(String.valueOf(s.getSubtitleNumber()));
					bw.newLine();
					bw.write(sdf1.format(s.getStartTime()) + " --> " + sdf1.format(s.getEndTime()));
					bw.newLine();
					for (String line : s.getSubtitleStringList()) {
						bw.write(line);
						bw.newLine();
					}
					bw.newLine();
					}
				}
				catch (IOException e) {
				e.printStackTrace();
				error = true;
			}
		}
		else {
			System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] Oops! Something bad happened. Cleaning up...");
			try {
				if (br != null)
						br.close();
				if (fr != null)
						fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		if (error == true) {
			System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/ERROR] Oops! Something bad happened. Cleaning up...");
			try {
				if (br != null)
						br.close();
				if (fr != null)
						fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] SUCCESS!");
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/INFO] New file path: 'C:\\temp\\out.srt'!");
		
		sc.close();
		System.out.println("[" + fmt1.format(LocalDateTime.now()) + "] [System/main/END] Program ended successfully");
	}

}
