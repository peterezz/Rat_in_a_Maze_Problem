/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rat_in_a_maze_problem;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Peter
 */
public class Rat {

    private static ReentrantLock LOCK;
    private int countDOWN, countFORWARD;
    private volatile ArrayList<grid> movements;
    private Maze maze;
    private volatile boolean diedEnd;

    public Rat(Maze maze) {
        LOCK = new ReentrantLock();
        this.maze = maze;
        movements = new ArrayList<grid>();
        movements.add(new grid(0, 0));

    }

    public ArrayList<grid> getMovements() {
        return movements;
    }

    private void printSolution() {
        System.out.println("solution:-");
        for (grid x : movements) {
            System.out.print("X: " + x.posX + "| Y: " + x.posY + "\n "
                    + " next blocked forward pos: " + x.ForwardPosBlocked + "| next blocked forward X: " + x.nextBlockedFXpos + "| next blocked forward Y: " + x.nextBlockedFYpos + "\n"
                    + " next blocked down pos: " + x.DownPosBlocked + "| next blocked down x: " + x.nextBlockedDXpos + "| next blocked down Y: " + x.nextBlockedDYpos
                    + "\n ________________________________________________________________________________________________________________________________________________________" + "\n");
        }
    }

    private void runForward() {
        LOCK.lock();
        try {
//                while (!diedEnd) {
            grid oldVal = movements.get(movements.size() - 1);
            int XPos = oldVal.posX;
            int nextYPos = oldVal.posY + 1;
            
            
            // checking the goal
            // if goal -> deadEnd=true - thread.sleep
            if (maze.isGoal(XPos, oldVal.posY)) {
                diedEnd = true;
                
                //if safe
            } else if (maze.isSafe(XPos, nextYPos)) {
                if (XPos != oldVal.nextBlockedFXpos && nextYPos != oldVal.nextBlockedFYpos) {
                    oldVal.forwardThread = true;
                    movements.add(movements.size(), new grid(XPos, nextYPos));
                }
            } else {
                //if not safe
                movements.set(movements.size() - 1, new grid(oldVal.posX, oldVal.posY, true, XPos, nextYPos, oldVal.DownPosBlocked, oldVal.nextBlockedDXpos, oldVal.nextBlockedDYpos));
                movements.get(movements.size() - 1).forwardThread = true;
                                movements.get(movements.size() - 1).downThread = oldVal.downThread;
                // if forward and down are blocked
                if (oldVal.DownPosBlocked) {
                    for (int i = movements.size(); i > 0; i--) {
                        if (movements.get(i - 1).DownPosBlocked == false && movements.get(i - 1).ForwardPosBlocked == false) {
                            grid currentVal = movements.get(i - 1);
                            grid nextVal = movements.get(i);
                            // forward path is blcked
                            if (nextVal.posY > currentVal.posY) {
//                                    temp.ForwardPosBlocked = true;
//                                    temp.nextBlockedFXpos = temp1.posX;
//                                    temp.nextBlockedFYpos = temp1.posY;
                                movements.add(movements.size(), new grid(currentVal.posX, currentVal.posY, true, nextVal.posX, nextVal.posY, currentVal.DownPosBlocked, currentVal.nextBlockedDXpos, currentVal.nextBlockedDYpos));
                            //down path is blocked
                            } else if (nextVal.posX > currentVal.posX) {
//                                    temp.DownPosBlocked = true;
//                                    temp.nextBlockedDXpos = temp1.posX;
//                                    temp.nextBlockedDYpos = temp1.posY;
                                movements.add(movements.size(), new grid(currentVal.posX, currentVal.posY, currentVal.ForwardPosBlocked, currentVal.nextBlockedFXpos, currentVal.nextBlockedFYpos, true, nextVal.posX, nextVal.posY
                                ));
                            }
                            break;
                        }

                    }

                }
            }
            //sleep
            SleepUtilities.nap();
//                }

        } finally {
            LOCK.unlock();
        }
    }

    private void RunDown() {
        LOCK.lock();
        try {
//                while (!diedEnd) {
            grid oldVal = movements.get(movements.size() - 1);
            int nextXPos = oldVal.posX + 1;
            int YPos = oldVal.posY;
            // checking the goal
            // if goal -> deadEnd=true - thread.sleep
            if (maze.isGoal(oldVal.posX, YPos)) {
                diedEnd = true;

            } else if (maze.isSafe(nextXPos, YPos)) {
                if (nextXPos != oldVal.nextBlockedDXpos && YPos != oldVal.nextBlockedDYpos) {
                    oldVal.downThread = true;
                    movements.add(movements.size(), new grid(nextXPos, YPos));
                }

            } else {
                movements.set(movements.size() - 1, new grid(oldVal.posX, oldVal.posY, oldVal.ForwardPosBlocked, oldVal.nextBlockedFXpos, oldVal.nextBlockedFYpos, true, nextXPos, YPos));
                movements.get(movements.size() - 1).downThread = true;
                                movements.get(movements.size() - 1).forwardThread = oldVal.forwardThread;

                if (oldVal.ForwardPosBlocked) {
                    for (int i = movements.size(); i > 0; i--) {
                        if (movements.get(i - 1).DownPosBlocked == false && movements.get(i - 1).ForwardPosBlocked == false) {
                            grid currentVal = movements.get(i - 1);
                            grid nextVal = movements.get(i);
                            if (nextVal.posY > currentVal.posY) {
//                                    temp.ForwardPosBlocked = true;
//                                    temp.nextBlockedFXpos = temp1.posX;
//                                    temp.nextBlockedFYpos = temp1.posY;
                                movements.add(movements.size(), new grid(currentVal.posX, currentVal.posY, true, nextVal.posX, nextVal.posY, currentVal.DownPosBlocked, currentVal.nextBlockedDXpos, currentVal.nextBlockedDYpos));

                            } else if (nextVal.posX > currentVal.posX) {
//                                    temp.DownPosBlocked = true;
//                                    temp.nextBlockedDXpos = temp1.posX;
//                                    temp.nextBlockedDYpos = temp1.posY;
                                movements.add(movements.size(), new grid(currentVal.posX, currentVal.posY, currentVal.ForwardPosBlocked, currentVal.nextBlockedFXpos, currentVal.nextBlockedFYpos, true, nextVal.posX, nextVal.posY
                                ));

                            }
                            break;

                        }
                    }
                }

            }
            SleepUtilities.nap();
//                }

        } finally {
            LOCK.unlock();
        }
    }

    private Runnable Run = new Runnable() {
        @Override
        public void run() {
            String threadId = Thread.currentThread().getName();
            while (!diedEnd) {
                if (threadId.equals("Running farword")) {
                    runForward();
                } else if (threadId.equals("Running Down")) {
                    RunDown();

                }
                System.out.println(threadId);
            }
            printSolution();

        }
    };

    public void StartRunning() {

        try {
            Thread t0 = new Thread(Run, "Running farword");
//            SleepUtilities.nap();
            Thread t1 = new Thread(Run, "Running Down");
//            System.out.println(t0.getName());
            t0.start();
            SleepUtilities.nap();
//            System.out.println(t1.getName());
            t1.start();
            t0.join();
            t1.join();
        } catch (InterruptedException ex) {
        }

    }

}
