package worker;

import java.util.ArrayList;
import java.util.Scanner;

public class Dynamic_Fold
{
	public static void main(String[] args)
	{
		Node start = new Node('H', new Coords(0,0,0));
		Node next = start;
		
		for(int i=1; i<2; i++)
		{
			next.setNext(new Node('H', new Coords(i,0,0)));
			next = next.getNext();
		}
		
		Node second = new Node('H', new Coords(0,0,0));
		next = second;
		
		for(int i=1; i<2; i++)
		{
			next.setNext(new Node('H', new Coords(i,0,0)));
			next = next.getNext();
		}
		
		ArrayList<Node> folds = append(start, second);
		
		for (int i=0; i<folds.size(); i++)
		{
			next = folds.get(i);
			while (next !=null)
			{
				System.out.print("["+next.getCoords().getX()+","+next.getCoords().getY()+","+next.getCoords().getZ()+"],");
				next = next.getNext();
			}
			System.out.println();
		}
		
	}
	
	
	//Returns true if node sequence contains an overlap
	private static boolean overlaps(Node start)
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
	private static boolean willOverlap(Node first, Node second)
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
	
	private static int energy(Node start)
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
	
	private static ArrayList<LinkList> SplitString()
	{
		ArrayList<LinkList> myList= new ArrayList<LinkList>();
		Scanner sc = new Scanner(System.in);
		String str = null;
		System.out.println("Please enter your sequence: ");
		str = sc.nextLine();
		
		System.out.println("your sequence is: "+"\n"+str);
		String[] ss = new String[50];
		int count = 0;
		ss[count]="";
		for(int i=0; i<str.length();i++)
		{
			char a = str.charAt(i);
			String b = ""+ a;
			if(a!=72 && a!= 104 && a!=80 && a!=112)
			{
				System.out.println("please enter a hp sequence.");
				System.out.println(a+" is not h or p");
				break;
			}
			if( a==72 || a==104 )
			{
				count++;
				ss[count]=b;
			}else
				ss[count]=ss[count].concat(b);
			
		}
		
		for( int i = 0 ; i <=count ; i++)
		{
			LinkList myLinkList= new LinkList();
			
			//System.out.println("ss["+i+"]="+ss[i]);
			// the first line if exist, only contain Ps.
			if(ss[i].charAt(0)=='p')
			{
				for(int j=0; j<ss[0].length();j++)
					myLinkList.addNode(0, j, "p");
					
				//System.out.println();
				myList.add(myLinkList);
			}
			else
			{
				//start from second line, the start char must be h
				for(int j = 0; j<ss[i].length();j++)
					myLinkList.addNode(0, j, ss[i].substring(j));
				myList.add(myLinkList);
			}
		}
		return myList;
	}

	private static Node rotatePlus90XY(Node curNode)
	{
		Node rotated = new Node(curNode.getCharge(),
				new Coords(	curNode.getCoords().getY()*-1,
							curNode.getCoords().getX(),
							curNode.getCoords().getZ()));
		if (curNode.getNext() != null)
			rotated.setNext(rotatePlus90XY(curNode.getNext()));
		
		return rotated;
	}
	private static Node rotateMin90XY(Node curNode)
	{
		Node rotated = new Node(curNode.getCharge(),
				new Coords(	curNode.getCoords().getY(),
							curNode.getCoords().getX()*-1,
							curNode.getCoords().getZ()));
		if (curNode.getNext() != null)
			rotated.setNext(rotateMin90XY(curNode.getNext()));
		
		return rotated;
	}
	private static Node rotatePlus90XZ(Node curNode)
	{
		Node rotated = new Node(curNode.getCharge(),
				new Coords(	curNode.getCoords().getZ()*-1,
							curNode.getCoords().getY(),
							curNode.getCoords().getX()));
		if (curNode.getNext() != null)
			rotated.setNext(rotatePlus90XZ(curNode.getNext()));
		
		return rotated;
	}
	private static Node rotateMin90XZ(Node curNode)
	{
		Node rotated = new Node(curNode.getCharge(),
				new Coords(	curNode.getCoords().getZ(),
							curNode.getCoords().getY(),
							curNode.getCoords().getX()*-1));
		if (curNode.getNext() != null)
			rotated.setNext(rotateMin90XZ(curNode.getNext()));
		
		return rotated;
	}
	private static Node rotatePlus90YZ(Node curNode)
	{
		Node rotated = new Node(curNode.getCharge(),
				new Coords(	curNode.getCoords().getX(),
							curNode.getCoords().getZ()*-1,
							curNode.getCoords().getY()));
		if (curNode.getNext() != null)
			rotated.setNext(rotatePlus90YZ(curNode.getNext()));
		
		return rotated;
	}
	private static Node rotateMin90YZ(Node curNode)
	{
		Node rotated = new Node(curNode.getCharge(),
				new Coords(	curNode.getCoords().getX(),
							curNode.getCoords().getZ(),
							curNode.getCoords().getY()*-1));
		if (curNode.getNext() != null)
			rotated.setNext(rotateMin90YZ(curNode.getNext()));
		
		return rotated;
	}
	private static ArrayList<Node> getRotations(Node start)
	{
		ArrayList<Node> rotations = new ArrayList<Node>();
		
		rotations.add(rotatePlus90XY(start));
		rotations.add(rotatePlus90XZ(start));
		rotations.add(rotatePlus90YZ(start));
		rotations.add(rotateMin90XY(start));
		rotations.add(rotateMin90XZ(start));
		rotations.add(rotateMin90YZ(start));
		
		return rotations;
	}
	
	private static Node moveNode(Node curNode, Coords newPos)
	{
		Node moved = new Node(curNode.getCharge(),
				new Coords(	curNode.getCoords().getX()+newPos.getX(),
							curNode.getCoords().getY()+newPos.getY(),
							curNode.getCoords().getZ()+newPos.getZ()));
		if (curNode.getNext() != null)
			moved.setNext(moveNode(curNode.getNext(), newPos));
		
		return moved; 
	}
	
	private static ArrayList<Node> append(Node first, Node second)
	{
		ArrayList<Node> possibleFolds = new ArrayList<Node>();
		Node end = first;
		while(end.getNext() != null)
			end = end.getNext();
		
		ArrayList<Coords> testPositions = getSurroundingCoords(end);
		ArrayList<Node> secondRotations = getRotations(second);
		
		for(int i=0; i<testPositions.size(); i++)
		{
			for (int j=0; j<secondRotations.size(); j++)
			{
				Node testNode = moveNode(secondRotations.get(j), testPositions.get(i));
				if (!willOverlap(first, testNode))
				{
					Node newFold = first.clone();
					newFold.getLast().setNext(testNode);
					possibleFolds.add(newFold);
				}
			}
				
		}
		return possibleFolds;
	}
	
	private static ArrayList<Coords> getSurroundingCoords(Node end)
	{
		ArrayList<Coords> surroundingCoords = new ArrayList<Coords>();
		int x = end.getCoords().getX();
		int y = end.getCoords().getY();
		int z = end.getCoords().getZ();
		
		surroundingCoords.add(new Coords(x+1, y, z));
		surroundingCoords.add(new Coords(x-1, y, z));
		surroundingCoords.add(new Coords(x, y+1, z));
		surroundingCoords.add(new Coords(x, y-1, z));
		surroundingCoords.add(new Coords(x, y, z+1));
		surroundingCoords.add(new Coords(x, y, z-1));
		
		return surroundingCoords;
	}
}
