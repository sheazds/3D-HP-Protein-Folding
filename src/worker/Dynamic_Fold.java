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
		
		generateCombinations(start, second);
		
		System.out.println(validChains.size());
		next = validChains.get(0);
		for (int i=0; i<validChains.size(); i++)
		{
			next = validChains.get(i);
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
				testCoords.setZ(testCoords.getZ()+2);
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
	
	static ArrayList<Node> validChains = new ArrayList<Node>();
    static int currentMaxEnergyLevel = Integer.MAX_VALUE;
	
	/**
     * Generate the combinations by iterating over the rotations and then shifting the second chain to the end of the
     * first chain. Rotations are calculated by applying two functions: roll and turn. Using them, the first 12
     * rotations are generated using the sequence RTTTRTTTRTTT. Then a transformation of RTR is needed to reposition
     * the chain. Then the second 12 are generated using the same sequence. For each of the 24 options, call the check
     * function which will then do the shifting.
     * @param frontChain - The chain at the beginning which we append to.
     * @param behindChain - The chain at the end which is being appended.
     */
    public static void generateCombinations(Node frontChain, Node behindChain) {
        for(int i=0; i<2; i++) {
            for(int j=0; j<3; j++) {
                roll(behindChain);
                check(frontChain, behindChain);
                for(int k=0; k<3; k++) {
                    turn(behindChain);
                    check(frontChain, behindChain);
                }
            }
            roll(behindChain);
            turn(behindChain);
            roll(behindChain);
        }
    }

    /**
     * Given two chains, try to attach the second chain to the 6 side of the last node in the first chain. A combination
     * is only valid if the nodes do not overlap together.
     * @param frontChain
     * @param behindChain
     */
    public static void check(Node frontChain, Node behindChain) {

        int[][] shifts = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};

        //Find the last node in the chain so we can append the second chain to it.
        Node lastNode = frontChain.getLast();

        //For each rotations, the front node of the second chain can be attached to any of the 4 adjacent cells of
        // the last node of the first chain.
        for(int[] shift: shifts) {
            Node transformedChain = shift(behindChain, lastNode.getCoords().getX()+shift[0], lastNode.getCoords().getY()+shift[1], lastNode.getCoords().getZ()+shift[2]);

            //If the combination is valid (meaning that the chains do not overlap), then add it to the valid nodes.
            if(!willOverlap(frontChain, transformedChain)) {

                //Create a new chain by appending the transformed chain to the back of the front chain.
                Node newFrontChain = duplicate(frontChain);
                Node frontChainLastNode = newFrontChain.getLast();

                //Update the next and previous references
                frontChainLastNode.setNext(transformedChain);
                transformedChain.setPrevious(frontChainLastNode);

                //Calculate the energy level of the structure.
                int energyLevel = energy(newFrontChain);

                //If it equals the max energy level found, add it to the valid chains.
                if(energyLevel == currentMaxEnergyLevel) {
                    validChains.add(newFrontChain);
                }
                //If it is less then, then reset the valid chains and add it since we only care about the lowest ones.
                else if(energyLevel < currentMaxEnergyLevel) {
                    validChains = new ArrayList<Node>();
                    validChains.add(newFrontChain);
                    currentMaxEnergyLevel = energyLevel;
                }
                //If the energy level is less then the max, ignore it since the algorithm is greedy and only cares
                // about finding the shapes with the lowest energy levels.
            }
        }
    }


    /**
     * Function that rolls the chain where roll rotates the chain 90 degrees away from you. This means the bottom is now
     * the side facing you.
     * @param frontChain - Head node of the chain.
     */
    public static void roll(Node frontChain) {

        Node nextNode = frontChain;

        do {
            int tempX = nextNode.getCoords().getX();
            int tempY = nextNode.getCoords().getY();
            int tempZ = nextNode.getCoords().getZ();

            nextNode.getCoords().setX(tempX);
            nextNode.getCoords().setY(tempZ);
            nextNode.getCoords().setZ(-tempY);

            nextNode = nextNode.getNext();
        } while (nextNode != null);

    }

    /**
     * Function that turns the chain where turn rotates the chain 90 degrees about the z axis. So the top side becomes
     * the left side of the new chain.
     * @param frontChain - Head node of the chain.
     */
    public static void turn(Node frontChain) {

        Node nextNode = frontChain;

        do {
            int tempX = nextNode.getCoords().getX();
            int tempY = nextNode.getCoords().getY();
            int tempZ = nextNode.getCoords().getZ();

            nextNode.getCoords().setX(-tempY);
            nextNode.getCoords().setY(tempX);
            nextNode.getCoords().setZ(tempZ);

            nextNode = nextNode.getNext();
        } while (nextNode != null);

    }

    /**
     * Returns a new copy of the node chain with the given shift applied to it.
     * @param nodeChain - The first node in the chain.
     * @param shiftX - How much to shift the chain in the x direction.
     * @param shiftY - How much to shift the chain in the y direction.
     * @return The first node in the shifted chain.
     */
    public static Node shift(Node nodeChain, int shiftX, int shiftY, int shiftZ) {
        Node newHead = duplicate(nodeChain);
        Node nextNode = newHead;
        do {
            nextNode.getCoords().setX(nextNode.getCoords().getX()+shiftX);
            nextNode.getCoords().setY(nextNode.getCoords().getY()+shiftY);
            nextNode.getCoords().setZ(nextNode.getCoords().getZ()+shiftZ);
            nextNode = nextNode.getNext();
        } while(nextNode != null);

        return  newHead;
    }

    /**
     * Duplicates the given node chain.
     * @param oldHead - The first node in the chain to be duplicated.
     * @return The first node in the new chain.
     */
    public static Node duplicate(Node oldHead) {
        Node newHead = oldHead.clone();

        Node oldTemp = oldHead;
        Node newTemp = newHead;

        while(oldTemp.hasNext()) {
            Node oldNext = oldTemp.getNext();
            Node newNext = oldNext.clone();
            newTemp.setNext(newNext);
            newNext.setPrevious(newTemp);
            oldTemp = oldNext;
            newTemp = newNext;
        }

        return newHead;
    }

    public static void printChain(Node nodeChain) {

        do {
            System.out.println(nodeChain);
            nodeChain = nodeChain.getNext();
        } while(nodeChain != null);
    }
}
