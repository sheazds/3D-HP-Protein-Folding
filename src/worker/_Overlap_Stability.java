package worker;
import java.util.ArrayList;

public class _Overlap_Stability
{
	public static void main (String args[])
	{
		Node start = new Node('H', new Coords(0,0,0));
		Node next = start;
		
		for(int i=1; i<3; i++)
		{
			next.setNext(new Node('H', new Coords(0,i,0)));
			next = next.getNext();
		}
		Node second = new Node('H', new Coords(1,10,0));
		//next = second;
		for(int i=2; i>=0; i--)
		{
			next.setNext(new Node('H', new Coords(1,i,0)));
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
		System.out.println("Energy:" + energy(start));
		
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
	
	public static int energy(Node start)
	{
		int energy = 0;
		// Matrix access order: X, Y, Z
		ArrayList<Coords> chargeLocs = new ArrayList<Coords>();
		
		Node next = start;
		while (next != null)
		{
			//if node is H, add to list of charges
			if (next.getCharge() == 'H')
				chargeLocs.add(next.getCoords());
			//if more than 1 charge check for neighbors in list of charges
			if (chargeLocs.size() > 1)
			{	//(-1,0,0)
				Coords testCoords = new Coords(
						next.getCoords().getX()-1,
						next.getCoords().getY(),
						next.getCoords().getZ());
				if (chargeLocs.contains(testCoords))
					energy--;
				//(+1,0,0)
				testCoords.setX(testCoords.getX()+2);
				if (chargeLocs.contains(testCoords))
					energy--;
				//(0,-1,0)
				testCoords.setX(testCoords.getX()-1);
				testCoords.setY(testCoords.getY()-1);
				if (chargeLocs.contains(testCoords))
					energy--;
				//(0,+1,0)
				testCoords.setY(testCoords.getY()+2);
				if (chargeLocs.contains(testCoords))
					energy--;
				//(0,0,-1)
				testCoords.setY(testCoords.getY()-1);
				testCoords.setZ(testCoords.getZ()-1);
				if (chargeLocs.contains(testCoords))
					energy--;
				//(0,0,1)
				testCoords.setZ(testCoords.getZ()-1);
				if (chargeLocs.contains(testCoords))
					energy--;
			}			
			next = next.getNext();
		}
		return energy;
	}
}
