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
public class grid {

    public int posX;
    public int posY;
    boolean ForwardPosBlocked;
    public int nextBlockedFXpos;
    public int nextBlockedFYpos;
    boolean DownPosBlocked;
    public int nextBlockedDXpos;
    public int nextBlockedDYpos;
    public boolean forwardThread;
    public boolean downThread;

    public grid(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.nextBlockedFXpos = -1;
        this.nextBlockedFYpos = -1;
        this.nextBlockedDXpos = -1;
        this.nextBlockedDYpos = -1;

    }
    

    public grid() {
    }

    public grid(int posX, int posY, boolean ForwardPosBlocked, int nextBlockedFXpos, int nextBlockedFYpos, boolean DownPosBlocked, int nextBlockedDXpos, int nextBlockedDYpos) {
        this.posX = posX;
        this.posY = posY;
        this.ForwardPosBlocked = ForwardPosBlocked;
        this.nextBlockedFXpos = nextBlockedFXpos;
        this.nextBlockedFYpos = nextBlockedFYpos;
        this.DownPosBlocked = DownPosBlocked;
        this.nextBlockedDXpos = nextBlockedDXpos;
        this.nextBlockedDYpos = nextBlockedDYpos;
    }

}
