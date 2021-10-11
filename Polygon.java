/**
 * Represents a simple polygon (each angle is under 180 degrees) in the cartesian dimension 
 * @author Ramy Bachayev
 * @version 20/6/2020
 */

public class Polygon
{
    private PointNode _head;//represents the list of vertices of a polygon

    //constructors:
    /**
     * Constructs a Polygon object.
     * Construct a new Polygon - the pointer points on next location (null).
     * Space complexity - O(1), Time complexity - O(1). 
     */

    public Polygon()
    {
        _head=null;
    }

    /**
     * Adding a vertex to Polygon.
     * pos need to be bigger than 0.
     * If pos<1 or pos-1>Polygon length the vertex will not add to Polygon and return false.
     * If pos-1=Polygon length the vertex will add to last location in Polygon.
     * If allready there's a vertex in the given pos, the new vertex will add
     * in the given pos in Polygon list and the old vertex will move forward.
     * Time complexity - O(n) - i using max 2 loops (not nested) in every call to this
     * method- 1 loop is length() +(pos>Polygon length or pos<=Polygon length).
     * Space complexity - O(1) - i using a constant number of actions.
     * @param p the Point object
     * @param pos the position i want to add the point to the Polygon list
     * @return true if the vertex can be added to the Polygon list 
     */

    public boolean addVertex(Point p, int pos)
    {   
        int len=length();//using private method length() and avoiding use of loops 

        if(pos<1||pos-1>len)//according to the instructions
            return false;

        if(_head==null)//check if the Polygon list empty
        {
            _head=new PointNode(p);//this vertex will add to first location of Polygon
            return true;
        }

        if(_head!=null||pos-1==len)//check if Polygon list not empty  
        {
            PointNode ptr=_head;//using aliasing to not mess with original list

            if(pos<=len)//if pos pos<=Polygon length
            {                  
                for(int c=1;c<=pos;c++)//check which location need to be replaced
                {//check if pos!=c and this location is not last
                    if(pos!=c&&ptr.getNext()!=null)                                         
                        ptr=ptr.getNext();//pointer moving forward

                    if(c==pos)
                    {//create a copy of old vertex in this location
                        Point copy=new Point(ptr.getPoint());

                        ptr.setPoint(p);//replacing this location vertex in given vertex

                        //move the vertices to be after the given vertex
                        ptr.setNext(new PointNode(copy,ptr.getNext()));

                        break;//end of loop
                    }
                }
                return true;                
            }

            if(pos>len)//if pos is bigger by 1 from Polygon length    
                while (ptr.getNext()!=null)//check if this location is not last               
                    ptr=ptr.getNext();//pointer moving forward

            //creating a new vertex in Polygon list in last location        
            ptr.setNext(new PointNode(p,null)); 
            return true;
        }        
        return false;
    }

    /**
     * Check which vertex in Polygon is higher.
     * If there's more than one highest vertex (y1=y2) the return will be
     * the first vertex in Polygon list.
     * If Polygon is empty the return will be null.
     * Time complexity - O(n) - i using max 1 loop.
     * Space complexity - O(1) - i using a constant number of actions.
     * @return the highest vertex in Polygon list 
     */

    public Point highestVertex()
    {
        if(_head==null)//check if the Polygon list empty
            return null; 

        PointNode ptr=_head;//using aliasing to not mess with original list    
        Point copy=new Point(ptr.getPoint());//create a copy of first vertex in Polygon

        while (ptr.getNext()!=null)//check if this location is not last 
        {//check if copy is under this vertex
            if(copy.isUnder(ptr.getNext().getPoint()))//using method isUnder of Point
                copy=ptr.getNext().getPoint();//copy gets the values of this vertex

            ptr=ptr.getNext();//pointer moving forward        
        }
        return copy;
    }

    /**
     * Calculates the Polygon's perimeter.
     * If Polygon length=2 the return will be the distance between 2 vertices.
     * If Polygon length less than 2 the return will be 0.
     * Time complexity - O(n) - i using max 2 loops (not nested) in every call to this
     * method- 1 loop is length() +(ptr.getNext()!=null).
     * Space complexity - O(1) - i using a constant number of actions.
     * @return the Polygon perimeter 
     */

    public double calcPerimeter ()
    {
        int len=length();//using private method length() and avoiding use of loops

        PointNode ptr=_head;//using aliasing to not mess with original list 

        //(ptr.getPoint().distance(ptr.getNext().getPoint()))- calculation of distance
        //between this vertex to next vertex 

        if(len==2)
            return ptr.getPoint().distance(ptr.getNext().getPoint());//using method distance of Point

        if(len<2)
            return 0;

        double p=0;//defining variable p - perimeter

        while (ptr.getNext()!=null)//check if this location is not last
        {
            p+=ptr.getPoint().distance(ptr.getNext().getPoint());//using method distance of Point
            ptr=ptr.getNext();//pointer moving forward
        }
        //calculates the the distance between vertex in 1st location to last location
        if(ptr.getNext()==null)  
            p+=_head.getPoint().distance(ptr.getPoint());

        return p;
    }

    /**
     * Calculates the Polygon's area.
     * If Polygon length less than 3 the return will be 0.
     * Time complexity - O(n) - i using max 2 loops (not nested) in every call to this
     * method- 1 loop is length() +(ptr.getNext().getNext()!=null).
     * Space complexity - O(1) - i using a constant number of actions.
     * @return the Polygon area 
     */

    public double calcArea()
    {
        int len=length();//using private method length() and avoiding use of loops

        if(len<3)
            return 0;

        PointNode ptr=_head;//using aliasing to not mess with original list    
        double s=0;//defining variable s - area

        while(ptr.getNext().getNext()!=null)//check if next location is not last
        {    
            s+=calcTriangleArea(ptr);//using private method calcTriangleArea 
            ptr=ptr.getNext();//pointer moving forward
        }
        return s;    
    }

    /**
     * Check if this Polygon is bigger than other Polygon.
     * Time complexity - O(n) - according to calcArea() method. 
     * Space complexity - O(1) - i using a constant number of actions.
     * @param other the other Polygon (other!=null) 
     * @return true if this Polygon is bigger than other Polygon  
     */

    public boolean isBigger(Polygon other)
    {
        return this.calcArea()>other.calcArea();
    }

    /**
     * Finds the location of the given Point in Polygon list.
     * If the given point isn't in Polygon list the return will be -1. 
     * Time complexity - O(n) - i using max 1 loop. 
     * Space complexity - O(1) - i using a constant number of actions.
     * @param p the Point object 
     * @return the location of the given Point in Polygon list  
     */

    public int findVertex(Point p)
    {
        PointNode ptr=_head;//using aliasing to not mess with original list
        int c=1;//defining variable c- location of the given point

        while(ptr!=null)//check if the Polygon list not empty
        {
            if(ptr.getPoint().equals(p))//using method equals of Point
                return c;

            c++;
            ptr=ptr.getNext();//pointer moving forward
        }
        return -1;   
    }

    /**
     * Gets the next vertex after the given Point in Polygon list.
     * If the given point isn't in Polygon list or the Polygon is empty the return will be null. 
     * If there's only 1 point in Polygon the return will be the same point.
     * If the given point is the last vertex in Polygon the return will be the 1st vertex. 
     * Time complexity - O(n) - i using max 2 loops (not nested) in every call to this
     * method- 1 loop is length() +(ptr.getNext()!=null).
     * Space complexity - O(1) - i using a constant number of actions.
     * @param p the Point object 
     * @return the next vertex after the given Point in Polygon list  
     */

    public Point getNextVertex(Point p)
    {
        int len=length();//using private method length() and avoiding use of loops

        PointNode ptr=_head;//using aliasing to not mess with original list 

        if(_head==null)//check if the Polygon list not empty
            return null;

        if(len==1)
            return ptr.getPoint();

        while (ptr.getNext()!=null)//check if this location is not last
        {               
            if(ptr.getPoint().equals(p))//using method equals of Point
                return ptr.getNext().getPoint();//gets the next vertex 

            ptr=ptr.getNext();//pointer moving forward        
        }

        //check if this location is last and the given point equals to this
        if(ptr.getNext()==null&&ptr.getPoint().equals(p))
            return _head.getPoint();//gets the 1st vertex

        return null;
    }

    /**
     * Gets the Rectangle (as Polygon) that blocks the Polygon,
     * Rectangle parallel to x+y lines.
     * If Polygon length less than 3 the return will be null. 
     * Time complexity - O(n) - i using max 6 loops (not nested) in every call to this method:
     * 1 loop is length() + (ptr.getNext()!=null) + 4 loops (not nested) according to addVertex method. 
     * Space complexity - O(1) - i using a constant number of actions.    
     * @return the Rectangle (as Polygon)  
     */

    public Polygon getBoundingBox()
    {
        int len=length();//using private method length() and avoiding use of loops

        if(len<3)
            return null;

        PointNode ptr=_head;//using aliasing to not mess with original list

        //creates a copies of the first vertex in Polygon by using Point copy constructor 
        Point a=new Point(ptr.getPoint());
        Point b=new Point(ptr.getPoint());
        Point c=new Point(ptr.getPoint());
        Point d=new Point(ptr.getPoint());

        while(ptr.getNext()!=null)//check if this location is not last
        {
            if(ptr.getNext().getPoint().isLeft(a))//using method isLeft of Point
                a.setX(ptr.getNext().getPoint().getX());//gets the most left x coordinate

            if(ptr.getNext().getPoint().isUnder(a))//using method isUnder of Point
                a.setY(ptr.getNext().getPoint().getY());//gets the most under y coordinate 

            if(ptr.getNext().getPoint().isRight(b))//using method isRight of Point
                b.setX(ptr.getNext().getPoint().getX());//gets the most right x coordinate    

            if(ptr.getNext().getPoint().isAbove(c))//using method isAbove of Point
                c.setY(ptr.getNext().getPoint().getY());//gets the most upper y coordinate 

            ptr=ptr.getNext();//pointer moving forward    
        }

        d.setX(a.getX());//gets the most left x coordinate 
        d.setY(c.getY());//gets the most upper y coordinate
        b.setY(a.getY());//gets the most under y coordinate       
        c.setX(b.getX());//gets the most right x coordinate

        Polygon rectangle = new Polygon();//creates a new Polygon - rectangle 
        //adding 4 vertices to rectangle list in order 
        rectangle.addVertex(a, 1);
        rectangle.addVertex(b, 2);
        rectangle.addVertex(c, 3);
        rectangle.addVertex(d, 4);

        return rectangle;
    }

    /**
     * Return a string representation of Polygon in order (first to last vertex).
     * If Polygon is empty the return is: "The polygon has 0 vertices.".
     * If Polygon isn't empty the return is:
     * "The polygon has "+Polygon length+"vertices:\n"+"((Point),(Point)...)"
     * Time complexity - O(n) - i using max 2 loops (not nested) in every call to this
     * method- 1 loop is length() + (for(;ptr!=null;ptr=ptr.getNext()). 
     * Space complexity - O(1) - i using a constant number of actions.
     * @return String representation of Polygon
     */

    public String toString()
    {
        int len=length();//using private method length() and avoiding use of loops

        PointNode ptr=_head;//using aliasing to not mess with original list

        if(_head==null)//check if Polygon empty
            return "The polygon has 0 vertices.";

        String s="The polygon has "+len+" vertices:\n";//defining beginning String     

        String w="";//defining empty String        

        for(;ptr!=null;ptr=ptr.getNext())//pointer moving forward while Polygon not empty                       
        { 
            if(ptr.getNext()!=null)//check if this location is not last     
                w+=ptr.getPoint()+",";//gets Point object+","

            if(ptr.getNext()==null)//check if this location is last     
                w+=ptr.getPoint();//gets last Point object in Polygon 
        }
        //automatically using method toString() in Point class        

        return s+"("+w+")";
    }

    private int length()
    {
        //Time complexity - O(n) - i using max 1 loop.
        //Space complexity - O(1) - i using a constant number of actions.

        int length=0;//defining variable length
        PointNode current=_head;//using aliasing to not mess with original list
        while (current!=null)//check if the Polygon list not empty
        {
            current=current.getNext();//pointer moving forward
            length++;
        }
        return length;
    }

    private double calcTriangleArea(PointNode current)//current- the given Polygon
    {  
        //Time complexity - O(1) - i don't using loops.
        //Space complexity - O(1) - i using a constant number of actions.        

        double a=0,b=0,c=0;//represents the triangle edges

        //calculates distance between 1st vertex in triangle to 2nd vertex 
        a=current.getNext().getPoint().distance(_head.getPoint());

        current=current.getNext();//pointer moving forward

        //calculates distance between 2nd vertex in triangle to 3rd vertex
        b=current.getPoint().distance(current.getNext().getPoint());

        current=current.getNext();//pointer moving forward

        //calculates distance between 1st vertex in triangle to 3rd vertex
        c=_head.getPoint().distance(current.getPoint());

        double s=(a+b+c)/2;//defining variable s- area of triangle=0.5*(triangle perimeter)

        return Math.sqrt(s*(s-a)*(s-b)*(s-c));//according to Heron's formula
    }
}