/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FootballSimulator;

import java.awt.Color;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Stephan
 */
public class FootballSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MatchFrame frame = new MatchFrame();                // create new frame
        frame.setLocationRelativeTo(null);                  // opens frame on center of screen
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);      // exits when X is clicked
        frame.getContentPane().setBackground(Color.WHITE);  // sets background color
        frame.setVisible(true);                             // set frame as visible
    }

}
