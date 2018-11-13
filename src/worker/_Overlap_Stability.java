package worker;
import java.util.ArrayList;

public class _Overlap_Stability
{
	public static void main (String args[])
	{
		Node start = new Node('h', new Coords(0,0,0));
		Node next = start;
		
		for(int i=1; i<3; i++)
		{
			next.setNext(new Node('h', new Coords(1,i,0)));
			next = next.getNext();
		}
		Node second = new Node('h', new Coords(1,10,0));
		next = second;
		for(int i=9; i>5; i--)
		{
			next.setNext(new Node('h', new Coords(1,i,0)));
			next = next.getNext();
		}
		next = start;
		while (next != null)
		{
			System.out.println(next.getCoords());
			next = next.getNext();
		}
		
		System.out.println("Overlaps? " + overlaps(start));
		System.out.println("willOverlap? " + willOverlap(start, second));
		
	}
	
	//Returns true if node sequence contains an overlap
	public static boolean overlaps(Node start)
	{
		ArrayList<Coords> visited = new ArrayList<Coords>();
		Node next = start;
		while (next != null)
		{
			if (visited.contains(next.getCoords()))
				return true;
			visited.add(next.getCoords());
			next = next.getNext();
		}
		return false;
	}
	
	//Returns true if two Node chains will overlap
	public static boolean willOverlap(Node first, Node second)
	{
		ArrayList<Coords> visited = new ArrayList<Coords>();
		Node next = first;
		while (next != null)
		{
			if (visited.contains(next.getCoords()))
				return true;
			visited.add(next.getCoords());
			next = next.getNext();
		}
		next = second;
		while (next != null)
		{
			if (visited.contains(next.getCoords()))
				return true;
			visited.add(next.getCoords());
			next = next.getNext();
		}
		return false;
	}
	
	public static int stability(Node start)
	{
		int stability = 0;
		// Matrix access order: X, Y, Z
		ArrayList<ArrayList<ArrayList<Node>>> matrix = new ArrayList<ArrayList<ArrayList<Node>>>();
		
		Node next = start;
		//add node to matrix
		while (next != null)
		{
			//get position
			int xLoc = next.getCoords().getX();
			int yLoc = next.getCoords().getY();
			int zLoc = next.getCoords().getZ();
			
			//expand matrix to fit coordinates
			while (matrix.size() < xLoc)
				matrix.add(new ArrayList<ArrayList<Node>>());
			while (matrix.get(xLoc).size() < yLoc)
				matrix.get(xLoc).add(new ArrayList<Node>());
			while (matrix.get(xLoc).get(yLoc).size() < zLoc)
				matrix.get(xLoc).get(yLoc).add(null);
				
			
				
			next = start.getNext();
		}
		
		return stability;
	}
}
