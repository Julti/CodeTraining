package aoc2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
	public void FirstSetSolution() {
		try {
			Scanner s = new Scanner(new File("D:\\input.txt"));
			int pos_blue = 14;
			int pos_red = 12;
			int pos_green = 13;
			long posCount = 0;
			ArrayList<Game> games = new ArrayList<Game>();
			while(s.hasNext()) {
				int blue = 0;
				int red = 0;
				int green = 0;
				String line = s.nextLine();
				String[] parts = line.split(":");
				Game g = new Game();
				g.possible=true;
				g.number = Integer.parseInt(parts[0].replace("Game ","").trim());
				String[] gs = parts[1].split(";");
				for (int i = 0; i < gs.length; i++) {
					String[] colors = gs[i].split(",");
					for (int j = 0; j < colors.length; j++) {
						if(colors[j].contains("blue")) {
							blue=Integer.parseInt(colors[j].trim().replace("blue","").trim());
							if(blue>pos_blue)g.possible=false;
						}else if(colors[j].contains("green")) {
							green=Integer.parseInt(colors[j].trim().replace("green","").trim());
							if(green>pos_green)g.possible=false;
						}if(colors[j].contains("red")) {
							red=Integer.parseInt(colors[j].trim().replace("red","").trim());
							if(red>pos_red)g.possible=false;
						}
					}
				}
				g.blue=blue;
				g.green = green;
				g.red = red;
				//g.possible = blue<=pos_blue&&green<=pos_green&&red<=pos_red;
				if(g.possible)posCount+=g.number;
				games.add(g);
			}
			System.out.println(posCount);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(new File("D:\\input.txt"));
			int pos_blue = Integer.MAX_VALUE;
			int pos_red = Integer.MAX_VALUE;
			int pos_green = Integer.MAX_VALUE;
			long posCount = 0;
			ArrayList<Game> games = new ArrayList<Game>();
			while(s.hasNext()) {
				int min_blue = 0;
				int min_red = 0;
				int min_green = 0;
				String line = s.nextLine();
				String[] parts = line.split(":");
				Game g = new Game();
				g.possible=true;
				g.number = Integer.parseInt(parts[0].replace("Game ","").trim());
				String[] gs = parts[1].split(";");
				for (int i = 0; i < gs.length; i++) {
					String[] colors = gs[i].split(",");
					for (int j = 0; j < colors.length; j++) {
						if(colors[j].contains("blue")) {
							int blue=Integer.parseInt(colors[j].trim().replace("blue","").trim());
							min_blue=Integer.max(min_blue, blue);
						}else if(colors[j].contains("green")) {
							int green=Integer.parseInt(colors[j].trim().replace("green","").trim());
							min_green=Integer.max(min_green, green);
						}if(colors[j].contains("red")) {
							int red=Integer.parseInt(colors[j].trim().replace("red","").trim());
							min_red=Integer.max(min_red, red);
						}
					}
				}
				long max = min_blue*min_red*min_green;
				posCount+=max;
			}
			System.out.println(posCount);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static class Game {
		public int number;
		public int red;
		public int green;
		public int blue;
		public boolean possible;
		public Game() {
			
		}
	}

}
