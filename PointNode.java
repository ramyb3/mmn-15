/**
 * Represents a list of vertices of a polygon in cartesian dimension 
 * Space complexity - O(1): in every method i using a constant number of actions. 
 * Time complexity - O(1): i don't using loops in this methods.
 * @author Ramy Bachayev
 * @version 20/6/2020
 */

public class PointNode
{
    //instance variables:
    private Point _point;//represents a Point object
    private PointNode _next;//represents the next PointNode object

    //constructors:
    /**
     * Constructs a PointNode object.
     * Construct a new PointNode instance with the specified Point object.
     * @param p the Point object
     */
    
    public PointNode (Point p)
    {
        _point=new Point(p);//avoiding point aliasing
        _next=null;//next location in the list is null
    }

    /**
     * Constructs a PointNode object.
     * Construct a PointNode instance with the specified Point object and
     * a pointer to next Point object.
     * @param p the Point object
     * @param n the PointNode object
     */
    
    public PointNode (Point p, PointNode n)
    {   
        setPoint(p);//using setPoint to create new Point object         
        _next=n;//pointer to next Point object         
    }

    /**
     * Copy constructor for PointNode.
     * Constructs a PointNode object with the same variables as another PointNode.
     * @param p the PointNode object
     */
    
    public PointNode (PointNode p)
    {   
        //using setPoint to create new Point object - get's the point of this pointer
        setPoint(p.getPoint()); 
        _next=p.getNext();//the pointer now moving forward
    }
    
    /**
     * Returns the copy of point coordinates
     * @return the copy of point coordinates
     */
    
    public Point getPoint()
    {
        Point other=new Point(_point);//avoiding point aliasing
        return other;
    }

    /**
     * Returns the next pointer point coordinates
     * @return the next pointer point coordinates
     */
    
    public PointNode getNext()
    {
        return _next;
    }

    /**
     * Changes the point coordinates.
     * @param p the value of the new point coordinates 
     */
    
    public void setPoint(Point p)
    {
        _point=new Point(p);//avoiding point aliasing       
    }

    /**
     * Moves the pointer forward to next location.
     * @param next the next location of the pointer 
     */
    
    public void setNext(PointNode next)
    {
        _next=next;        
    }    
}