import java.awt.Color;
import becker.robots.City;
import becker.robots.Direction;
public class MazeSolverFromBottomRight extends MazeSolverAbstract{
	public MazeSolverFromBottomRight(City city, int i, int j, Direction south, int k) {
		// TODO Auto-generated constructor stub
		super(city, i, j, south, k);
	}
	
	
	public void solveMaze() {
		int mazeAttempts = 0;
		
		this.setSpeed(10); // increases default speed
		this.move();
		this.turnRight();
		this.move();
		if (canPickThing() == true) { // checks to see if it should try and pick up a thing on its initial location, so that the robot can properly progress and navigate throughout the maze
			this.pickThing();
		}
		this.move();
		this.putThing(); // placing a thing by default to ensure that it will not get stuck and be confused as to where it should move initially
		do { // should continue attempting to solve the maze
			
			if (thingNotInFront() == false){ // in front of wall ahead immediately
				turnLeft();
				this.move();
				if (thingNotInFront() == false){ // scenario when wall adjacent to robot on left is blocked
					turnAround();
					this.move();
					if (thingNotInFront() == false){
						turnRight();
						this.move();
					} else {
						if (thingNotInFront() == false) {
							this.move();
						}
					}
				} else {
					if (thingNotInFront() == false) {
						this.move();
					}
				}
			} else {
				turnLeft();
				this.move();
				if (thingNotInFront() == true) {
					this.move();
				} else {
					turnRight();
					this.move();
				}
			}
			
			mazeAttempts += 1;
		//	System.out.println(mazeAttempts); // this can be used if you want to see how long it takes the robot to complete the maze
		} while (this.getStreet() != 0 && mazeAttempts < 150); // this code determines however long the robot should be moving until it can reach its final destination
		this.setColor(Color.BLUE); // changes colour and picks up the wall once it has reached the top of the maze
	}
	
	
	
	
	public boolean thingNotInFront() { // note that thingNotInFront is the same as frontIsClear
		
		if (this.canPickThing() == true) { // is going to check if it is currently on a thing, if it is not, it should turn around to its current location and progress throughout the code either returning a boolean value to the method solving the maze that there is or is not a thing in front of it
			this.turnAround();
			super.move();
			this.turnAround();
			return false; // there is a thing in front of the robot
		} else {
			return true;
		}
		
	}
	
	
	
	
}
