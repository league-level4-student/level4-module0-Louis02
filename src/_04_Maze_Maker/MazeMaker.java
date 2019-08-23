package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Random ranGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);
		int r = randGen.nextInt(width);
		// 4. select a random cell to start
		Cell ranCell = maze.getCell(r, r);

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(ranCell);
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell cc) {
		// A. mark cell as visited
		cc.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> gun = getUnvisitedNeighbors(cc);
		
		// C. if has unvisited neighbors,
		if (gun.isEmpty() == false) {
			int ran = ranGen.nextInt(gun.size());
			Cell chosen = gun.get(ran);
			uncheckedCells.push(chosen);
			removeWalls(chosen, cc);
			cc = chosen;
			cc.setBeenVisited(true);
			selectNextPath(chosen);
		}
		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		// D. if all neighbors are visited
		if (gun.isEmpty() == true) {
			if (uncheckedCells.isEmpty() == false) {
				Cell popped = uncheckedCells.pop();
				cc = popped;
				selectNextPath(cc);
			}
		}
		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.hasEastWall() == c2.hasWestWall()) {
			c1.setEastWall(false);
		}
		if (c1.hasNorthWall() == c2.hasSouthWall()) {
			c1.setNorthWall(false);
		}
		if (c1.hasWestWall() == c2.hasEastWall()) {
			c1.setWestWall(false);
		}
		if (c1.hasSouthWall() == c2.hasNorthWall()) {
			c1.setSouthWall(false);
		}

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cal = new ArrayList<Cell>();

		if (c.getX() + 1 < width && maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited() == false) {
			cal.add(maze.getCell(c.getX() + 1, c.getY()));
		}
		if (c.getX() - 1 > 0 && maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited() == false) {
			cal.add(maze.getCell(c.getX() - 1, c.getY()));
		}
		if (c.getY() + 1 < height && maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited() == false) {
			cal.add(maze.getCell(c.getX(), c.getY() + 1));
		}
		if (c.getY() - 1 > 0 && maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited() == false) {
			cal.add(maze.getCell(c.getX(), c.getY() - 1));
		}
		return cal;
	}
}
