package worker;

public class Direction {
	
	private Node node;
	private String Dir = "";
	
	/*test
	public static void main(String[] args) {
		
		Coords c1 = new Coords(0,0,0);
		Coords c2 = new Coords(1,0,0);
		Coords c3 = new Coords(1,1,0);
		Coords c4 = new Coords(0,1,0);
		Coords c5 = new Coords(0,1,1);
		
		Node n1 =  new Node('H', c1);
		Node n2 =  new Node('P', c2);
		Node n3 =  new Node('P', c3);
		Node n4 =  new Node('P', c4);
		Node n5 =  new Node('P', c5);
		
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5);
		
		Direction d = new Direction(n1);
		System.out.println(d.getDirection());
		
		
	}
	*/
	public Direction(Node head) {
		this.node = head;
		calculateDir();
		
	}
	
	public String getDirection() {
		return Dir;
	}
	
	private void calculateDir() {
		while(node.hasNext()) {
			if(node.getNext().getCoords().getX() > node.getCoords().getX()) {
				Dir = Dir + "r";
			}else if(node.getNext().getCoords().getX() < node.getCoords().getX()) {
				Dir = Dir + "l";
			}else if(node.getNext().getCoords().getY() > node.getCoords().getY()) {
				Dir = Dir + "u";
			}else if(node.getNext().getCoords().getY() < node.getCoords().getY()) {
				Dir = Dir + "d";
			}else if(node.getNext().getCoords().getZ() > node.getCoords().getZ()) {
				Dir = Dir + "o";
			}else {
				Dir = Dir + "i";
			}
			node = node.getNext();
		}
	}

}
