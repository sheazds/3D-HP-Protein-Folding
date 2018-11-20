package worker;

/**
 * 
 * Node.java
 *
 * This class represent one amino acid of a protein. It can either be
 * hydrophobic "h" or hydrophilic/polar "p"
 * It also stores a pointer to the node before and after and has coordinates
 *
 * @author Aaron Germuth
 * @date 2013-03-16
 */
@SuppressWarnings("rawtypes")
public class Node implements Comparable
{
	private char charge;
	private Node next; 
	private Node previous;
	private Coords coords;
	
	public Node(){
		this.coords = null;
		this.next = null;
		this.previous = null;
	}

	public Node(char charge, Coords coords) {
		this.coords = new Coords();
		this.coords.setX(coords.getX());
		this.coords.setY(coords.getY());
		this.coords.setZ(coords.getZ());
		this.charge = charge;
		this.next = null;
		this.previous = null;
	}
	
	public String toString(){
		return "Node Charge: " + this.charge + ", " + this.coords;
	}
	
	public char getCharge(){
		return this.charge;
	}
	public void setCharge(char charge){
		if(charge != 'h' || charge != 'p'){
			System.out.println("Tried to assign " + charge + " to a node.");
		}
		this.charge = charge;
	}
	
	public Node getLast()
	{
		if (next == null)
			return this;
		else
			return next.getLast();
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}
	
	public Node clone()
	{
		Node clone = new Node(charge, new Coords(coords.getX(), coords.getY(), coords.getZ()));
		if (next != null)
			 clone.setNext(next.clone());
		return clone;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean equals(Object another)
	{
		if (!(another instanceof Node))
			return false;
		if(coords.getX() == ((Node) another).getCoords().getX() 
				&& coords.getY() == ((Node) another).coords.getY()
				&& coords.getZ() == ((Node) another).coords.getZ())
					return next.equals(((Node) another).getNext());
		return false;
	}
	@Override
	public int hashCode()
	{
		int hash = this.coords.getX()+this.coords.getY()+this.coords.getZ();
		if (next != null)
			hash = hash + next.hashCode();
		return hash;
	}
}
