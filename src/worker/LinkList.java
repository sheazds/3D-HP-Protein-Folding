package worker;

public class LinkList { 
	private Node head;
	
	public LinkList(){  
	        head=null;  
	       
	}  
	public int length(){ 
		int length = 0;
        Node curNode = head;
        while(curNode != null){
            length++;
            curNode = curNode.next;
        }
        return length;
       
    } 
	//add node
	public void addNode(int x,int y, String value){
		Node newNode = new Node(x,y,value);
		if(head == null){
            head = newNode;
            return;
        }
		Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
	}
	//print out the link
	public void printLink(){
        Node curNode = head;
        while(curNode !=null){
            System.out.print(curNode.hpValue+" ");
            curNode = curNode.next;
        }
        System.out.println();
    }
	//get last node
	public Node getLastNode(){
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp;
    }
	
	
	public class Node {
		private Node next;
		private Node prev;
		private int x,y,z;
		private String hpValue=null;
	
		//constructor
		public Node() {
			// TODO Auto-generated constructor stub
		
		}
		public Node(int x, int y, String value) {
			this.x=x;
			this.y=y;
			hpValue=value;
		}
		public Node(int x, int y, String value, Node next) {
			this.x=x;
			this.y=y;
			hpValue=value;
			this.next=next;
		}
	
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next= next;
		}
		public Node getPrev() {
			return prev;
		}
		public void setPrev(Node prev) {
			this.prev= prev;
		}
		public String getValue() {
			return hpValue;
		}
		public void setValue(String value) {
			hpValue=value;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public void setX(int x) {
			this.x=x;
		}
		public void setY(int x) {
			this.y=y;
		}
	
}
}
