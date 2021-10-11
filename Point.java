/**
 * Represents a point in the cartesian dimension 
 * Space complexity - O(1): in every method i using a constant number of actions. 
 * Time complexity - O(1): i don't using loops in this methods.
 * @author Ramy Bachayev
 * @version 20/6/2020
 */

public class Point
{
    //instance variables:
    private double _x;//represents the location of point in x line
    private double _y;//represents the location of point in y line

    //constructors:
    /**
     * Constructs a Point object.
     * Construct a new point instance with the specified coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    
    public Point(double x, double y)
    {//get point coordinates
        _x=x;
        _y=y;
    }

    /**
     * Copy constructor for Point.
     * Constructs a point with the same variables as another point.
     * @param other The point object from which to construct the new point
     */
    
    public Point (Point other)
    {//creates a copy of this point
        _x=other._x; 
        _y=other._y;    
    }

    /**
     * Returns the x coordinate
     * @return the x coordinate
     */
    
    public double getX()
    {
        return _x;   
    }

    /**
     * Returns the y coordinate
     * @return the y coordinate
     */
    
    public double getY()
    {
        return _y;   
    }

    /**
     * Changes the x coordinate.
     * @param num the value of the new x coordinate 
     */
    
    public void setX(double num)
    {
        _x=num;    
    }

    /**
     * Changes the y coordinate.
     * @param num the value of the new y coordinate 
     */
    
    public void setY(double num)
    {
        _y=num;    
    }

    /**
     * Returns a string representation of this point ("x,y")
     * @return a string representation of this time ("x,y")
     */
    
    public String toString()
    {
        return "("+_x+","+_y+")";        
    }

    /**
     * Checks if the received point is equal to this point.
     * @param other The point to be compared with this point
     * @return true if both points are equal 
     */
    
    public boolean equals (Point other)
    {
        return (_x==other._x)&&(_y==other._y);    
    }

    /**
     * Checks if this point is above other point.
     * @param other The point to check if this point is above
     * @return true if this point is above the other point  
     */
    
    public boolean isAbove (Point other)
    {
        if(_y>other._y)
            return true;

        return false;    
    }

    /**
     * Checks if this point is under other point.
     * @param other The point to check if this point is under
     * @return true if this point is under the other point  
     */
    
    public boolean isUnder (Point other)
    {//using the opposite method - isAbove
        return other.isAbove(this);    
    }

    /**
     * Checks if this point is left from other point.
     * @param other The point to check if this point is left from
     * @return true if this point is left from the other point  
     */
    
    public boolean isLeft (Point other)
    {
        if(_x<other._x)
            return true;

        return false;    
    }

    /**
     * Checks if this point is right from other point.
     * @param other The point to check if this point is right from
     * @return true if this point is right from the other point  
     */
    
    public boolean isRight (Point other)
    {//using the opposite method - isLeft
        return other.isLeft(this);    
    }

    /**
     * Calculates the distance between 2 points.
     * @param p the point i want to measure the distance 
     * @return the distance between 2 points  
     */
    
    public double distance (Point p)
    {//calculation between 2 points
        return Math.sqrt(Math.pow(_y-p._y,2)+Math.pow(_x-p._x,2));
    }

    /**
     * Moving the point in any direction in the dimension.
     * @param dx left/right direction
     * @param dy up/down direction 
     */
    
    public void move (double dx, double dy)
    {
        _x+=dx;
        _y+=dy;
    }    
}