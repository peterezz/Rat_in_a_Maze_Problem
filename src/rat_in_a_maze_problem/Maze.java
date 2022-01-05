/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rat_in_a_maze_problem;

import java.util.Random;

/**
 *
 * @author Peter
 */
public class Maze {

    private Random rand = new Random();
    private int upperbound = 2;
    private int int_random;
    public int mazeSize;
    private int[][] The_Maze;

    public Maze(int maze[][], int mazeSize) {
        this.The_Maze = maze;
        this.mazeSize = mazeSize;
        printMaze();
    }

    public int[][] generateMaze() {

        for (int i = 0; i < mazeSize; i++) {
            for (int y = 0; y < mazeSize; y++) {
                this.int_random = rand.nextInt(this.upperbound);
                this.The_Maze[i][y] = int_random;

            }
        }
        The_Maze[0][0] = 1;
        The_Maze[mazeSize - 1][mazeSize - 1] = 1;
        printMaze();
        return this.The_Maze;

    }

    private void printMaze() {
        System.out.println("The Maze ----------------------->");
        for (int i = 0; i < this.mazeSize; i++) {
            System.out.print("{");
            for (int y = 0; y < this.mazeSize; y++) {
                System.out.print(" " + this.The_Maze[i][y] + " ");
            }
            System.out.println("}");
        }
    }

    boolean isSafe(
            int x, int y) {
        // if (x, y outside maze) return false
        return (x >= 0 && x < mazeSize && y >= 0
                && y < mazeSize && The_Maze[x][y] == 1);
    }

    boolean isGoal(int x, int y) {
        if (x == this.mazeSize - 1 && y == this.mazeSize - 1) {
            return true;
        } else {
            return false;
        }
    }

}
