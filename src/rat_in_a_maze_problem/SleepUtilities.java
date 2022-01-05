/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rat_in_a_maze_problem;

/**
 *
 * @author Peter
 */
public class SleepUtilities {
      private static final int NAP_TIME = 2;

    /**
     * Nap between zero and NAP_TIME seconds.
     */
    public static void nap() {
        nap(NAP_TIME);
    }

    /**
     * Nap between zero and duration seconds.
     */
    public static void nap(int duration) {
        int sleeptime = (int) (NAP_TIME * Math.random());
        try {
            Thread.sleep(sleeptime * 1000);
        } catch (InterruptedException e) {
        }
        
    }
}
