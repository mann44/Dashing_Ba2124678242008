/*
/* Author: Manthan Gajjar
** Date: 04/30/2018
**
** Description: This class provides the framework for
** ball objects, with many paramters defined below
**
*/
import java.awt.*;
public class MovingSqare {

	//--------------------------------------------------------
	//					Declare private variables
	//--------------------------------------------------------

	//The size of the square - x and y
	private int squareSizeX,squareSizeY;
	
	
	//The current position of the square - x and y
	private int squareCX, squareCY;
	
	//The previous position of the square - x and y
	private int squarePX, squarePY;
	
	//The applet dimensions - x and y
	private int maxX, maxY;
	
	//The square's color
	Color color;

	//--------------------------------------------------------
	//						Constructor
	//--------------------------------------------------------

	public MovingSqare(int xsize, int ysize, int cX, int cY, int pX, int pY, int windowSizeX, int windowSizeY, Color c)
	{
		//--------------------------------------------------------
		//				  Initialize private variables
		//--------------------------------------------------------
		
		squareSizeX = xsize; //the size of the square - width
		squareSizeY = ysize; //the size of the square - height
		
		squareCX = cX; //the current position - x value
		squareCY = cY; //the current position - y value
		
		squarePX = pX; //the previous position - x value
		squarePY = pY; //the previous position - y value
		
		maxX = windowSizeX; //the size of the screen - x value
		maxY = windowSizeY; //the size of the screen - y value
		
		color = c; //the color of the square
	}

	//--------------------------------------------------------
	//						   Getter methods
	//--------------------------------------------------------
	
	//---------------
	// square Size
	//---------------
	
	public int getSquareSizeX()
	{
		return squareSizeX;
	}
	
	public int getSquareSizeY()
	{
		return squareSizeY;
	}
	
	//------------------
	// Current positions
	//------------------
	
	public int getCX()
	{
		return squareCX;
	}
	
	public int getCY()
	{
		return squareCY;
	}
	
	//-------------------
	// Previous positions
	//-------------------
	
	public int getPX()
	{
		return squarePX;
	}
	
	public int getPY()
	{
		return squarePY;
	}
	
	//---------------
	// Ball Color
	//---------------
	
	public Color getSquareColor()
	{
		return color;
	}
	
	//--------------------------------------------------------
	//						   Setter methods
	//--------------------------------------------------------
	
	//------------------
	// Current positions
	//------------------
	
	public void setCX(int i)
	{
		squareCX = i;
	}
	
	public void setCY(int i)
	{
		squareCY = i;
	}
	
	//-------------------
	// Previous positions
	//-------------------
	
	public void setPX(int i)
	{
		squarePX = i;
	}
	
	public void setPY(int i)
	{
		squarePY = i;
	}
	
	//--------------------------------------------------------
	//						   Move the square
	//--------------------------------------------------------
	
	//This method updates the position of the ball based on its
	//current position and previous position. The paramaters are
	//the lowerY and the upperY for the gap in the middle of the game
	//so that the ball knows to bounce off that bar, but not if 
	//is at the position where the gap is. In that case, it goes
	//through the gap.
	public void moveSquare(int lowY, int upY)
	{
		//Just making new variables with shorter names
		int cx = getCX();
		int cy = getCY();
		int px = getPX();
		int py = getPY();
		
		//These values represent the change that will be made
		//to the ball. There are four possible directions 
		//the ball can go. These variables are used to determine
		//that direction
		int x = 0;
		int y = 0;
		
		//--------------------------------------------------------
		//						  Middle Bar Corners
		//--------------------------------------------------------
		
		//if the ball hits the top left corner of the middle bar
		if( (cx>=325) && (cx<=330) && (cy<=5) && (cy<=upY || cy>=lowY) )
		{
			x = 5;
			y = 5;
			cx = 327;
			cy = 2;
		}
		
		//if the ball hits the bottom left corner of the middle bar
		else if( (cx>=325) && (cx<=330) && (cy>=475-squareSizeY) && (cy<=upY || cy>=lowY) )
		{
			x = 5;
			y = -5;
			cx = 327;
			cy = maxY-squareSizeY-2;
		}
		
		//if the ball hits the top right corner of the middle bar
		else if( (cx>=310-squareSizeX) && (cx<=315-squareSizeX) && (cy<=5) && (cy<=upY || cy>=lowY) )
		{
			x = -5;
			y = 5;
			cx = 310-squareSizeX-2;
			cy = 2;
		}
		
		//if the ball hits the bottom right corner of the middle bar
		else if( (cx>=310-squareSizeX) && (cx<=315-squareSizeX) && (cy>=475-squareSizeY) && (cy<=upY || cy>=lowY) )
		{
			x = -5;
			y = -5;
			cx = 315-squareSizeX-2;
			cy = maxY-squareSizeY-2;
		}
		
		//--------------------------------------------------------
		//				 		 Middle Bar Non Corners
		//--------------------------------------------------------
		
		//if the ball hits the left side of the middle bar, and it is going from [down right] to [down left]
		else if( ( (cx>=315-squareSizeX && cx<=325-squareSizeX) && (cy<=upY || cy>=lowY )) && (cx-px>=5) && (cy-py>=5) )
		{
			x-=5;
			y+=5;
		}
		
		//if the ball hits the left side of the middle bar, and it is giong from [up right] to [up left]
		else if( ( (cx>=315-squareSizeX && cx<=325-squareSizeX) && (cy<=upY || cy>=lowY ) ) && (cx-px>=5) && (py-cy>=5) ) 
		{
			x-=5;
			y-=5;
		}
		
		//if the ball hits the right side of the middle bar, and it is going from [down left] to [down right]
		else if( ( (cx>=315 && cx<=325) && (cy<=upY || cy>=lowY ) ) && (px-cx>=5) && (cy-py>=5) )
		{
			x+=5;
			y+=5;
		}
		
		//if the ball hits the right side of the middle bar, and it is going from [up left] to [up right]
		else if( ( (cx>=315 && cx<=325) && (cy<=upY || cy>=lowY ) ) && (px-cx>=5) && (py-cy>=5) )
		{
			x+=5;
			y-=5;
		}
		
		//--------------------------------------------------------
		//				 Normal Motion -- Not hitting sides
		//--------------------------------------------------------
		
		//if the ball is moving [down right]
		else if( !(cy>=maxY-squareSizeY) && !(cx>=maxX-squareSizeX) && !(cy<=0) && !(cx<=0) && (cx>=5) && (cy>=5) && (cx-px>=5) && (cy-py>=5) )
		{
			x+=5;
			y+=5;
		}
		
		//if the ball is moving [up right]
		else if( !(cy>=maxY-squareSizeY) && !(cx>=maxX-squareSizeX) && !(cy<=0) && !(cx<=0) && (cx>=5)&& (cy>=5) && (cx-px>=5) && (py-cy>=5) )
		{
			x+=5;
			y-=5;
		}
		
		//if the ball is moving [down left]
		else if( !(cy>=maxY-squareSizeY) && !(cx>=maxX-squareSizeX) && !(cy<=0) && !(cx<=0) && (cx>=5)&& (cy>=5) && (px-cx>=5) && (cy-py>=5) )
		{
			x-=5;
			y+=5;
		}
		
		//if the ball is moving [up left]
		else if( !(cy>=maxY-squareSizeY) && !(cx>=maxX-squareSizeX) && !(cy<=0) && !(cx<=0) && (cx>=5)&& (cy>=5) && (px-cx>=5) && (py-cy>=5) )
		{
			x-=5;
			y-=5;		
		}	
		
		//--------------------------------------------------------
		//			 Normal Corners -- Non Barrier Corners
		//--------------------------------------------------------
		
		//if the ball hits the top left corner
		else if( (cx<=4) && (cy<=4) ) 
		{
			x = 5;
			y = 5;
			cx = 2;
			cy = 2;
		}
		
		//if the ball hits the bottom left corner
		else if( (cx<=4) && (cy>=maxY-squareSizeY-4) )
		{
			x = 5;
			y = -5;
			cx = 2;
			cy = maxY-squareSizeY-2;
		}
		
		//if the ball hits the top right corner
		else if( (cy<=4) && (cx>=maxX-squareSizeX-4) )
		{
			x = -5;
			y = 5;
			cx = maxX-squareSizeX-2;
			cy = 2;
		}
		
		//if the ball hits the bottom right corner
		else if( (cy>=maxY-squareSizeY-4) && (cx>=maxX-squareSizeX-4) )
		{
			x = -5;
			y = -5;
			cx = maxX-squareSizeX-2;
			cy = maxY-squareSizeY-2;
		}
		
		//--------------------------------------------------------
		//			 Normal Sides - Non Barrier Sides
		//--------------------------------------------------------
		
		//if the ball hits the bottom side and goes from [down right] to [up right]
		else if( (cy >= 480-squareSizeY) && (cx-px>= 5) && (cy-py>=5) )
		{	
			x+=5;
			y-=5;
		}
		
		//if the ball hits the bottom side and goes from [down left] to [up left]
		else if( (cy>=480-squareSizeY) && (px-cx>=5) && (cy-py>=5) )
		{
			x-=5;
			y-=5;
		}
		
		//if the ball hits the top side and goes from [up right] to [down right]
		else if( (cy <= 4) && (cx-px >=5) && (py-cy>=5) )
		{
			x+=5;
			y+=5;
		}
		
		//if the ball hits the top side and goes from [up left] to [down left]
		else if( (cy<=4) &&  (px-cx>=5) && (py-cy>=5) )
		{
			x-=5;
			y+=5;
		}
		
		//if the ball hits the right side and goes from [down right] to [down left]
		else if( (cx>=640-squareSizeX) &&  (cx-px>=5) && (cy-py>=5) )
		{
			x-=5;
			y+=5;
		}
		
		//if the ball hits the right side and goes from [up right] to [up left]
		else if( (cx>=640-squareSizeX) && (cx-px>=5) && (py-cy>=5) )
		{
			x-=5;
			y-=5;
		}
		
		//if the ball hits the left side and goes from [down left] to [down right]
		else if( (cx<=4)  && (px-cx>=5) && (cy-py>=5) )
		{	
			x+=5;
			y+=5;
		}
		
		//if the ball hits the left side and goes from [up left] to [up right]
		else if( (cx<=4) &&  (px-cx>=5) && (py-cy>=5) )
		{
			x+=5;
			y-=5;
		}
		
		//Now that we have determined what direction the ball should go
		//based on its current and previoud psotitions, we need to update
		//the current positions and previous positions. The NEW previous 
		//positions will be the OLD current positions
		setPX(cx);
		setPY(cy);
		setCX(cx+x);
		setCY(cy+y);
	}
	
	//--------------------------------------------------------
	//							Sleep method
	//--------------------------------------------------------
	
	//Stalls the program so that program slows down

	public void sleep(int i)
	{
		try 
		{
			Thread.sleep (i); 
		}
		catch (Exception e) { }
	}
}
