/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakemadness;

import environment.ApplicationStarter;

/**
 *
 * @author ZackB
 */
public class SnakeMadness {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   start();
    }

    private static void start() {
        ApplicationStarter.run("Snake Madness!", new SnakeEnvironment());
    }
}
