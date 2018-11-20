import java.util.*;

public class Selft {
	
	public static ArrayList<ArrayList<Node>> selfList = new ArrayList<ArrayList<Node>>();

	public static void main(String[] args) {
		
		Node fourth = new Node(3,0,"P",null);
		Node third = new Node(2,0,"P",fourth);
		Node second = new Node(1,0,"P",third);
		Node head = new Node(0,0,"H",second);
		
		ArrayList<Node> al = new ArrayList<Node>();
		al.add(head);
		al.add(second);
		al.add(third);
		al.add(fourth);
		
		if(al.size() <= 2) {
			ArrayList<Node> temp = new ArrayList<Node>();
			for(int i=0; i<al.size(); i++) {
				temp.add(al.get(i));
			}
			selfList.add(temp);
		}else {
			ArrayList<Node> temp = new ArrayList<Node>();
			temp.add(al.get(0));
			temp.add(al.get(1));
			selfList.add(temp);
			updateSelflist(al);
		}
		System.out.println(selfList.size());
		System.out.println("--------------------------");
		for(int j=0; j<selfList.size(); j++) {
			for(int k=0; k<selfList.get(j).size(); k++) {
				selfList.get(j).get(k).displayNode();
				
			}
			System.out.println("--------------------------");
			
		}
		

	}

	private static void updateSelflist(ArrayList<Node> al) {
		while(selfList.get(0).size() != al.size()) {
			if(selfList.get(0).get(selfList.get(0).size()-1).y == selfList.get(0).get(selfList.get(0).size()-2).y &&  selfList.get(0).get(selfList.get(0).size()-1).x > selfList.get(0).get(selfList.get(0).size()-2).x) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod1 = new Node();
				nod1.x = newAl1.get(newAl1.size()-1).x + 1;
				nod1.y = newAl1.get(newAl1.size()-1).y;
				nod1.str = newAl1.get(newAl1.size()-1).next.str;
				nod1.next = newAl1.get(newAl1.size()-1).next.next;
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod2 = new Node();
				nod2.x = newAl2.get(newAl2.size()-1).x;
				nod2.y = newAl2.get(newAl2.size()-1).y + 1;
				nod2.str = newAl2.get(newAl2.size()-1).next.str;
				nod2.next = newAl2.get(newAl2.size()-1).next.next;
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod3 = new Node();
				nod3.x = newAl3.get(newAl3.size()-1).x;
				nod3.y = newAl3.get(newAl3.size()-1).y - 1;
				nod3.str = newAl3.get(newAl3.size()-1).next.str;
				nod3.next = newAl3.get(newAl3.size()-1).next.next;
				newAl3.add(nod3);
				selfList.add(newAl3);

				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).y == selfList.get(0).get(selfList.get(0).size()-2).y &&  selfList.get(0).get(selfList.get(0).size()-1).x < selfList.get(0).get(selfList.get(0).size()-2).x) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod1 = new Node();
				nod1.x = newAl1.get(newAl1.size()-1).x - 1;
				nod1.y = newAl1.get(newAl1.size()-1).y;
				nod1.str = newAl1.get(newAl1.size()-1).next.str;
				nod1.next = newAl1.get(newAl1.size()-1).next.next;
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod2 = new Node();
				nod2.x = newAl2.get(newAl2.size()-1).x;
				nod2.y = newAl2.get(newAl2.size()-1).y + 1;
				nod2.str = newAl2.get(newAl2.size()-1).next.str;
				nod2.next = newAl2.get(newAl2.size()-1).next.next;
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod3 = new Node();
				nod3.x = newAl3.get(newAl3.size()-1).x;
				nod3.y = newAl3.get(newAl3.size()-1).y - 1;
				nod3.str = newAl3.get(newAl3.size()-1).next.str;
				nod3.next = newAl3.get(newAl3.size()-1).next.next;
				newAl3.add(nod3);
				selfList.add(newAl3);

				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).y > selfList.get(0).get(selfList.get(0).size()-2).y) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod1 = new Node();
				nod1.x = newAl1.get(newAl1.size()-1).x - 1;
				nod1.y = newAl1.get(newAl1.size()-1).y;
				nod1.str = newAl1.get(newAl1.size()-1).next.str;
				nod1.next = newAl1.get(newAl1.size()-1).next.next;
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod2 = new Node();
				nod2.x = newAl2.get(newAl2.size()-1).x + 1;
				nod2.y = newAl2.get(newAl2.size()-1).y;
				nod2.str = newAl2.get(newAl2.size()-1).next.str;
				nod2.next = newAl2.get(newAl2.size()-1).next.next;
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod3 = new Node();
				nod3.x = newAl3.get(newAl3.size()-1).x;
				nod3.y = newAl3.get(newAl3.size()-1).y + 1;
				nod3.str = newAl3.get(newAl3.size()-1).next.str;
				nod3.next = newAl3.get(newAl3.size()-1).next.next;
				newAl3.add(nod3);
				selfList.add(newAl3);

				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).y < selfList.get(0).get(selfList.get(0).size()-2).y){
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod1 = new Node();
				nod1.x = newAl1.get(newAl1.size()-1).x - 1;
				nod1.y = newAl1.get(newAl1.size()-1).y;
				nod1.str = newAl1.get(newAl1.size()-1).next.str;
				nod1.next = newAl1.get(newAl1.size()-1).next.next;
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod2 = new Node();
				nod2.x = newAl2.get(newAl2.size()-1).x + 1;
				nod2.y = newAl2.get(newAl2.size()-1).y;
				nod2.str = newAl2.get(newAl2.size()-1).next.str;
				nod2.next = newAl2.get(newAl2.size()-1).next.next;
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Node nod3 = new Node();
				nod3.x = newAl3.get(newAl3.size()-1).x;
				nod3.y = newAl3.get(newAl3.size()-1).y - 1;
				nod3.str = newAl3.get(newAl3.size()-1).next.str;
				nod3.next = newAl3.get(newAl3.size()-1).next.next;
				newAl3.add(nod3);
				selfList.add(newAl3);

				selfList.remove(0);
			}
		}
		
	}



}
