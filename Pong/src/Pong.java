/*
Pong 
created by:
Max Ronning
Adam Hoefs

Douglas Dunham
Final Project 
CS 5551 User Interface Design (sec 001) Fall 2015
12/8/15


3/4.  

Since our program heavily relied on animation, there wasn’t much need 
for any complex data structures. The program uses the (x,y) coordinates
of the Pong components (paddles, ball, sides of the panel), as well as 
variables that hold the change in x and y (∆X and ∆Y) for each respective 
component. Using these two parameters, we used a Swing timer to repaint 
the panel as well as the components using the new coordinates.

Data files:
	Pong.java
	GamePanel.java
	Paddle.java
	Ball.java
	
	We also used a custom font for displaying the score and instructions/controls 
	on the screen.

5. 

Our main algorithm revolves around using a Swing timer to repaint the 
components (ball and paddles) as their coordinates change. For the ball, 
we use its current coordinates, designate a constant ∆X and ∆Y,  and 
add these two values together to get the next coordinate. A call to 
repaint() results in the ball moving coordinates. If the ball hits the 
top or bottom of the frame, the ∆Y is inverted, resulting in the “bounce” effect. 
The same goes for when the ball hits the paddle, which then inverts the ∆X, 
returning the ball in the opposite direction. For the movement of the paddles, 
we take in the keyboard key that is pressed and determine which direction the 
paddle should move. Using that, we set the ∆Y to a positive or negative constant 
based on the desired movement of the paddle and add it to the current 
coordinate. Again, a call to repaint() will then update the location.

9. (points as above) Document the all the Extra Credit of your program if you 
	did it (by a demo and comments in the program code). 


15.  

Known Bugs:
Sometimes when both players are issuing commands to the paddles, player 2’s 
paddle can escape the bounds of the drawing panel. 

Possible fixes 

This bug comes from the order in which we update all the game components in
the update() function in GamePanel.java. We call update on player 1’s paddle, 
followed by player 2’s paddle and lastly, the ball. Since player 2 is called 
after, the coordinates are able to be changed and escape our bounds check 
function. We think the solution lies in using threads to isolate each player’s 
input, so they aren’t able to interfere with each other.

16..

Additional Features:

Add a “Start” menu so they players can input their names and save how many times
they have won/lost, or how many points they have scored over multiple games.
Add modifiers (power ups) these would modify the mechanics of the game. 
Examples of this would be things like slow mo, sticky paddles, inverted controls, change ball size, etc.

17. (2 points) Comments: Please write a couple of sentences about what you learned from the project.

During the course of this project we learned a lot about the importance of 
the Swing timer and paintComponent() when it comes to animating our game. 
This was a result of troubleshooting a synchronization problem we were 
having with the timer repainting the game to get the animations working 
smoothly because at first they were choppy and inconsistent. Another notable 
thing we learned was how to use KeyListener to take in keyboard presses and 
use them to control parts of our game.

*/

import javax.swing.JFrame;

public class Pong extends JFrame {

    public final static int WIDTH = 700, HEIGHT = 500;
    private static GamePanel panel;

    public Pong() {
    	
    	panel = new GamePanel(this);
        GamePanel.setGPSize(700,500);
        add(panel); 
        setTitle("Pong");
        setVisible(true);
        setResizable(false);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

    }
    
    //Accessor for drawing panel
    public static GamePanel getPanel() {
        return panel;
    }
   
    public static void main(String[] args) {
        new Pong();
    }
}
