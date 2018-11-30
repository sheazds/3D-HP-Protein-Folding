
public class Node {
	public int x = 0;
	public int y = 0;
	public String str = null;
	public Node next = null;

	
	//constructor
	
	public Node() {
		
	}
	
	public Node(int a, int b, String c, Node d) {
		
		x = a;
		y = b;
		str = c;
		next = d;
		
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public String getStr() {
		return str;
	}
	
	public void setStr(String s) {
		str = s;
	}
	
	public void displayNode() {
		
		System.out.println(x + "," + y + " " + str);
		
	}
	

}
