package aoc2023;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day4 {

	public static void main(String[] args) {
		//Can be done using a difference array?
		Scanner s;
		try {
			s = new Scanner(new File("D:\\input.txt"));
			long count=0;
			ArrayList<String> lines = new ArrayList<String>();
			while(s.hasNext()) {
				String line = s.nextLine();
				lines.add(line);
			}
			s.close();
			int counts[] = new int[lines.size()];
			for (int i = 0; i < counts.length; i++) {
				counts[i]=1;
			}
			for (int i = 0; i <lines.size(); i++) {
				String line = lines.get(i);
				String interesting = line.split(":")[1];
				List<String> winning = Arrays.asList(interesting.split("\\|")[0].trim().split(" "));
				List<String> guesses = Arrays.asList(interesting.split("\\|")[1].trim().split(" "));

				for (int k = 0; k < counts[i]; k++) {
					int score=0;
					for (int j = 0; j < guesses.size(); j++) {
						if(winning.contains(guesses.get(j))&&guesses.get(j).equals("")==false) {
							score++;
						}
					}
					for (int j = i+1; j <Integer.min(counts.length,i+1+score) ; j++) {
						counts[j]=counts[j]+1;
					}
				}
			}
			for (int i = 0; i < counts.length; i++) {
				count+=counts[i];
			}
			System.out.println(count);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
