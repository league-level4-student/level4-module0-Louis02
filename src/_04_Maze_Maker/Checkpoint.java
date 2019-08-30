package _04_Maze_Maker;

import java.util.Random;

public class Checkpoint {
	public static void main(String[] args) {
		int[][] check = new int[5][5];
		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check[i].length; j++) {
				Random ran = new Random();
				int r = ran.nextInt();
				check[i][j] = r;
			}
		}
		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check[i].length; j++) {
				System.out.print(check[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
