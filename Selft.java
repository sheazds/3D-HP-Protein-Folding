import java.util.*;

public class Selft {
	
	public static ArrayList<ArrayList<Node>> selfList = new ArrayList<ArrayList<Node>>();

	public static void main(String[] args) {
		
		Coords cohead = new Coords(0,0,0);
		Coords cosecond = new Coords(1,0,0);
		Coords cothird = new Coords(2,0,0);
		Coords cofourth = new Coords(3,0,0);
		Coords cofifth = new Coords(4,0,0);
		
		Node fifth = new Node('p',cofifth);
		Node fourth = new Node('p',cofourth);
		Node third = new Node('p',cothird);
		Node second = new Node('p',cosecond);
		Node head = new Node('h',cohead);
		
		head.setNext(second);
		second.setNext(third);
		third.setNext(fourth);
		fourth.setNext(fifth);
		
		fifth.setPrevious(fourth);
		fourth.setPrevious(third);
		third.setPrevious(second);
		second.setPrevious(head);
		
		ArrayList<Node> al = new ArrayList<Node>();
		al.add(head);
		al.add(second);
		al.add(third);
		al.add(fourth);
		al.add(fifth);
	
		Selft st = new Selft(al);
		System.out.println(st.getResult().size());

	}

	private static void updateSelflist(ArrayList<Node> al) {
		while(selfList.get(0).size() != al.size()) {
			if(selfList.get(0).get(selfList.get(0).size()-1).getCoords().getX() > selfList.get(0).get(selfList.get(0).size()-2).getCoords().getX()) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod1 = new Coords();
				Node nod1 = new Node();
				nod1.setCoords(cod1);
				nod1.setCharge(newAl1.get(newAl1.size()-1).getNext().getCharge());
				nod1.getCoords().setX(newAl1.get(newAl1.size()-1).getCoords().getX() + 1);
				nod1.getCoords().setY(newAl1.get(newAl1.size()-1).getCoords().getY());
				nod1.getCoords().setZ(newAl1.get(newAl1.size()-1).getCoords().getZ());
				nod1.setNext(newAl1.get(newAl1.size()-1).getNext().getNext());
				nod1.setPrevious(newAl1.get(newAl1.size()-1));
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod2 = new Coords();
				Node nod2 = new Node();
				nod2.setCoords(cod2);
				nod2.setCharge(newAl2.get(newAl2.size()-1).getNext().getCharge());
				nod2.getCoords().setX(newAl2.get(newAl2.size()-1).getCoords().getX());
				nod2.getCoords().setY(newAl2.get(newAl2.size()-1).getCoords().getY() + 1);
				nod2.getCoords().setZ(newAl2.get(newAl2.size()-1).getCoords().getZ());
				nod2.setNext(newAl2.get(newAl2.size()-1).getNext().getNext());
				nod2.setPrevious(newAl2.get(newAl2.size()-1));
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod3 = new Coords();
				Node nod3 = new Node();
				nod3.setCoords(cod3);
				nod3.setCharge(newAl3.get(newAl3.size()-1).getNext().getCharge());
				nod3.getCoords().setX(newAl3.get(newAl3.size()-1).getCoords().getX());
				nod3.getCoords().setY(newAl3.get(newAl3.size()-1).getCoords().getY() - 1);
				nod3.getCoords().setZ(newAl3.get(newAl3.size()-1).getCoords().getZ());
				nod3.setNext(newAl3.get(newAl3.size()-1).getNext().getNext());
				nod3.setPrevious(newAl3.get(newAl3.size()-1));
				newAl3.add(nod3);
				selfList.add(newAl3);
				
				ArrayList<Node> newAl4 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod4 = new Coords();
				Node nod4 = new Node();
				nod4.setCoords(cod4);
				nod4.setCharge(newAl4.get(newAl4.size()-1).getNext().getCharge());
				nod4.getCoords().setX(newAl4.get(newAl4.size()-1).getCoords().getX());
				nod4.getCoords().setY(newAl4.get(newAl4.size()-1).getCoords().getY());
				nod4.getCoords().setZ(newAl4.get(newAl4.size()-1).getCoords().getZ() + 1);
				nod4.setNext(newAl4.get(newAl4.size()-1).getNext().getNext());
				nod4.setPrevious(newAl4.get(newAl4.size()-1));
				newAl4.add(nod4);
				selfList.add(newAl4);
				
				ArrayList<Node> newAl5 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod5 = new Coords();
				Node nod5 = new Node();
				nod5.setCoords(cod5);
				nod5.setCharge(newAl5.get(newAl5.size()-1).getNext().getCharge());
				nod5.getCoords().setX(newAl5.get(newAl5.size()-1).getCoords().getX());
				nod5.getCoords().setY(newAl5.get(newAl5.size()-1).getCoords().getY());
				nod5.getCoords().setZ(newAl5.get(newAl5.size()-1).getCoords().getZ() - 1);
				nod5.setNext(newAl5.get(newAl5.size()-1).getNext().getNext());
				nod5.setPrevious(newAl5.get(newAl5.size()-1));
				newAl5.add(nod5);
				selfList.add(newAl5);

				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).getCoords().getX() < selfList.get(0).get(selfList.get(0).size()-2).getCoords().getX()) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod1 = new Coords();
				Node nod1 = new Node();
				nod1.setCoords(cod1);
				nod1.setCharge(newAl1.get(newAl1.size()-1).getNext().getCharge());
				nod1.getCoords().setX(newAl1.get(newAl1.size()-1).getCoords().getX() - 1);
				nod1.getCoords().setY(newAl1.get(newAl1.size()-1).getCoords().getY());
				nod1.getCoords().setZ(newAl1.get(newAl1.size()-1).getCoords().getZ());
				nod1.setNext(newAl1.get(newAl1.size()-1).getNext().getNext());
				nod1.setPrevious(newAl1.get(newAl1.size()-1));
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod2 = new Coords();
				Node nod2 = new Node();
				nod2.setCoords(cod2);
				nod2.setCharge(newAl2.get(newAl2.size()-1).getNext().getCharge());
				nod2.getCoords().setX(newAl2.get(newAl2.size()-1).getCoords().getX());
				nod2.getCoords().setY(newAl2.get(newAl2.size()-1).getCoords().getY() + 1);
				nod2.getCoords().setZ(newAl2.get(newAl2.size()-1).getCoords().getZ());
				nod2.setNext(newAl2.get(newAl2.size()-1).getNext().getNext());
				nod2.setPrevious(newAl2.get(newAl2.size()-1));
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod3 = new Coords();
				Node nod3 = new Node();
				nod3.setCoords(cod3);
				nod3.setCharge(newAl3.get(newAl3.size()-1).getNext().getCharge());
				nod3.getCoords().setX(newAl3.get(newAl3.size()-1).getCoords().getX());
				nod3.getCoords().setY(newAl3.get(newAl3.size()-1).getCoords().getY() - 1);
				nod3.getCoords().setZ(newAl3.get(newAl3.size()-1).getCoords().getZ());
				nod3.setNext(newAl3.get(newAl3.size()-1).getNext().getNext());
				nod3.setPrevious(newAl3.get(newAl3.size()-1));
				newAl3.add(nod3);
				selfList.add(newAl3);
				
				ArrayList<Node> newAl4 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod4 = new Coords();
				Node nod4 = new Node();
				nod4.setCoords(cod4);
				nod4.setCharge(newAl4.get(newAl4.size()-1).getNext().getCharge());
				nod4.getCoords().setX(newAl4.get(newAl4.size()-1).getCoords().getX());
				nod4.getCoords().setY(newAl4.get(newAl4.size()-1).getCoords().getY());
				nod4.getCoords().setZ(newAl4.get(newAl4.size()-1).getCoords().getZ() + 1);
				nod4.setNext(newAl4.get(newAl4.size()-1).getNext().getNext());
				nod4.setPrevious(newAl4.get(newAl4.size()-1));
				newAl4.add(nod4);
				selfList.add(newAl4);
				
				ArrayList<Node> newAl5 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod5 = new Coords();
				Node nod5 = new Node();
				nod5.setCoords(cod5);
				nod5.setCharge(newAl5.get(newAl5.size()-1).getNext().getCharge());
				nod5.getCoords().setX(newAl5.get(newAl5.size()-1).getCoords().getX());
				nod5.getCoords().setY(newAl5.get(newAl5.size()-1).getCoords().getY());
				nod5.getCoords().setZ(newAl5.get(newAl5.size()-1).getCoords().getZ() - 1);
				nod5.setNext(newAl5.get(newAl5.size()-1).getNext().getNext());
				nod5.setPrevious(newAl5.get(newAl5.size()-1));
				newAl5.add(nod5);
				selfList.add(newAl5);

				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).getCoords().getY() > selfList.get(0).get(selfList.get(0).size()-2).getCoords().getY()) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod1 = new Coords();
				Node nod1 = new Node();
				nod1.setCoords(cod1);
				nod1.setCharge(newAl1.get(newAl1.size()-1).getNext().getCharge());
				nod1.getCoords().setX(newAl1.get(newAl1.size()-1).getCoords().getX() - 1);
				nod1.getCoords().setY(newAl1.get(newAl1.size()-1).getCoords().getY());
				nod1.getCoords().setZ(newAl1.get(newAl1.size()-1).getCoords().getZ());
				nod1.setNext(newAl1.get(newAl1.size()-1).getNext().getNext());
				nod1.setPrevious(newAl1.get(newAl1.size()-1));
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod2 = new Coords();
				Node nod2 = new Node();
				nod2.setCoords(cod2);
				nod2.setCharge(newAl2.get(newAl2.size()-1).getNext().getCharge());
				nod2.getCoords().setX(newAl2.get(newAl2.size()-1).getCoords().getX() + 1);
				nod2.getCoords().setY(newAl2.get(newAl2.size()-1).getCoords().getY());
				nod2.getCoords().setZ(newAl2.get(newAl2.size()-1).getCoords().getZ());
				nod2.setNext(newAl2.get(newAl2.size()-1).getNext().getNext());
				nod2.setPrevious(newAl2.get(newAl2.size()-1));
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod3 = new Coords();
				Node nod3 = new Node();
				nod3.setCoords(cod3);
				nod3.setCharge(newAl3.get(newAl3.size()-1).getNext().getCharge());
				nod3.getCoords().setX(newAl3.get(newAl3.size()-1).getCoords().getX());
				nod3.getCoords().setY(newAl3.get(newAl3.size()-1).getCoords().getY() + 1);
				nod3.getCoords().setZ(newAl3.get(newAl3.size()-1).getCoords().getZ());
				nod3.setNext(newAl3.get(newAl3.size()-1).getNext().getNext());
				nod3.setPrevious(newAl3.get(newAl3.size()-1));
				newAl3.add(nod3);
				selfList.add(newAl3);
				
				ArrayList<Node> newAl4 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod4 = new Coords();
				Node nod4 = new Node();
				nod4.setCoords(cod4);
				nod4.setCharge(newAl4.get(newAl4.size()-1).getNext().getCharge());
				nod4.getCoords().setX(newAl4.get(newAl4.size()-1).getCoords().getX());
				nod4.getCoords().setY(newAl4.get(newAl4.size()-1).getCoords().getY());
				nod4.getCoords().setZ(newAl4.get(newAl4.size()-1).getCoords().getZ() + 1);
				nod4.setNext(newAl4.get(newAl4.size()-1).getNext().getNext());
				nod4.setPrevious(newAl4.get(newAl4.size()-1));
				newAl4.add(nod4);
				selfList.add(newAl4);
				
				ArrayList<Node> newAl5 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod5 = new Coords();
				Node nod5 = new Node();
				nod5.setCoords(cod5);
				nod5.setCharge(newAl5.get(newAl5.size()-1).getNext().getCharge());
				nod5.getCoords().setX(newAl5.get(newAl5.size()-1).getCoords().getX());
				nod5.getCoords().setY(newAl5.get(newAl5.size()-1).getCoords().getY());
				nod5.getCoords().setZ(newAl5.get(newAl5.size()-1).getCoords().getZ() - 1);
				nod5.setNext(newAl5.get(newAl5.size()-1).getNext().getNext());
				nod5.setPrevious(newAl5.get(newAl5.size()-1));
				newAl5.add(nod5);
				selfList.add(newAl5);
				
				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).getCoords().getY() < selfList.get(0).get(selfList.get(0).size()-2).getCoords().getY()){
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod1 = new Coords();
				Node nod1 = new Node();
				nod1.setCoords(cod1);
				nod1.setCharge(newAl1.get(newAl1.size()-1).getNext().getCharge());
				nod1.getCoords().setX(newAl1.get(newAl1.size()-1).getCoords().getX() - 1);
				nod1.getCoords().setY(newAl1.get(newAl1.size()-1).getCoords().getY());
				nod1.getCoords().setZ(newAl1.get(newAl1.size()-1).getCoords().getZ());
				nod1.setNext(newAl1.get(newAl1.size()-1).getNext().getNext());
				nod1.setPrevious(newAl1.get(newAl1.size()-1));
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod2 = new Coords();
				Node nod2 = new Node();
				nod2.setCoords(cod2);
				nod2.setCharge(newAl2.get(newAl2.size()-1).getNext().getCharge());
				nod2.getCoords().setX(newAl2.get(newAl2.size()-1).getCoords().getX() + 1);
				nod2.getCoords().setY(newAl2.get(newAl2.size()-1).getCoords().getY());
				nod2.getCoords().setZ(newAl2.get(newAl2.size()-1).getCoords().getZ());
				nod2.setNext(newAl2.get(newAl2.size()-1).getNext().getNext());
				nod2.setPrevious(newAl2.get(newAl2.size()-1));
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod3 = new Coords();
				Node nod3 = new Node();
				nod3.setCoords(cod3);
				nod3.setCharge(newAl3.get(newAl3.size()-1).getNext().getCharge());
				nod3.getCoords().setX(newAl3.get(newAl3.size()-1).getCoords().getX());
				nod3.getCoords().setY(newAl3.get(newAl3.size()-1).getCoords().getY() - 1);
				nod3.getCoords().setZ(newAl3.get(newAl3.size()-1).getCoords().getZ());
				nod3.setNext(newAl3.get(newAl3.size()-1).getNext().getNext());
				nod3.setPrevious(newAl3.get(newAl3.size()-1));
				newAl3.add(nod3);
				selfList.add(newAl3);
				
				ArrayList<Node> newAl4 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod4 = new Coords();
				Node nod4 = new Node();
				nod4.setCoords(cod4);
				nod4.setCharge(newAl4.get(newAl4.size()-1).getNext().getCharge());
				nod4.getCoords().setX(newAl4.get(newAl4.size()-1).getCoords().getX());
				nod4.getCoords().setY(newAl4.get(newAl4.size()-1).getCoords().getY());
				nod4.getCoords().setZ(newAl4.get(newAl4.size()-1).getCoords().getZ() + 1);
				nod4.setNext(newAl4.get(newAl4.size()-1).getNext().getNext());
				nod4.setPrevious(newAl4.get(newAl4.size()-1));
				newAl4.add(nod4);
				selfList.add(newAl4);
				
				ArrayList<Node> newAl5 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod5 = new Coords();
				Node nod5 = new Node();
				nod5.setCoords(cod5);
				nod5.setCharge(newAl5.get(newAl5.size()-1).getNext().getCharge());
				nod5.getCoords().setX(newAl5.get(newAl5.size()-1).getCoords().getX());
				nod5.getCoords().setY(newAl5.get(newAl5.size()-1).getCoords().getY());
				nod5.getCoords().setZ(newAl5.get(newAl5.size()-1).getCoords().getZ() - 1);
				nod5.setNext(newAl5.get(newAl5.size()-1).getNext().getNext());
				nod5.setPrevious(newAl5.get(newAl5.size()-1));
				newAl5.add(nod5);
				selfList.add(newAl5);

				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).getCoords().getZ() > selfList.get(0).get(selfList.get(0).size()-2).getCoords().getZ()) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod1 = new Coords();
				Node nod1 = new Node();
				nod1.setCoords(cod1);
				nod1.setCharge(newAl1.get(newAl1.size()-1).getNext().getCharge());
				nod1.getCoords().setX(newAl1.get(newAl1.size()-1).getCoords().getX() - 1);
				nod1.getCoords().setY(newAl1.get(newAl1.size()-1).getCoords().getY());
				nod1.getCoords().setZ(newAl1.get(newAl1.size()-1).getCoords().getZ());
				nod1.setNext(newAl1.get(newAl1.size()-1).getNext().getNext());
				nod1.setPrevious(newAl1.get(newAl1.size()-1));
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod2 = new Coords();
				Node nod2 = new Node();
				nod2.setCoords(cod2);
				nod2.setCharge(newAl2.get(newAl2.size()-1).getNext().getCharge());
				nod2.getCoords().setX(newAl2.get(newAl2.size()-1).getCoords().getX() + 1);
				nod2.getCoords().setY(newAl2.get(newAl2.size()-1).getCoords().getY());
				nod2.getCoords().setZ(newAl2.get(newAl2.size()-1).getCoords().getZ());
				nod2.setNext(newAl2.get(newAl2.size()-1).getNext().getNext());
				nod2.setPrevious(newAl2.get(newAl2.size()-1));
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod3 = new Coords();
				Node nod3 = new Node();
				nod3.setCoords(cod3);
				nod3.setCharge(newAl3.get(newAl3.size()-1).getNext().getCharge());
				nod3.getCoords().setX(newAl3.get(newAl3.size()-1).getCoords().getX());
				nod3.getCoords().setY(newAl3.get(newAl3.size()-1).getCoords().getY() - 1);
				nod3.getCoords().setZ(newAl3.get(newAl3.size()-1).getCoords().getZ());
				nod3.setNext(newAl3.get(newAl3.size()-1).getNext().getNext());
				nod3.setPrevious(newAl3.get(newAl3.size()-1));
				newAl3.add(nod3);
				selfList.add(newAl3);
				
				ArrayList<Node> newAl4 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod4 = new Coords();
				Node nod4 = new Node();
				nod4.setCoords(cod4);
				nod4.setCharge(newAl4.get(newAl4.size()-1).getNext().getCharge());
				nod4.getCoords().setX(newAl4.get(newAl4.size()-1).getCoords().getX());
				nod4.getCoords().setY(newAl4.get(newAl4.size()-1).getCoords().getY() + 1);
				nod4.getCoords().setZ(newAl4.get(newAl4.size()-1).getCoords().getZ());
				nod4.setNext(newAl4.get(newAl4.size()-1).getNext().getNext());
				nod4.setPrevious(newAl4.get(newAl4.size()-1));
				newAl4.add(nod4);
				selfList.add(newAl4);
				
				ArrayList<Node> newAl5 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod5 = new Coords();
				Node nod5 = new Node();
				nod5.setCoords(cod5);
				nod5.setCharge(newAl5.get(newAl5.size()-1).getNext().getCharge());
				nod5.getCoords().setX(newAl5.get(newAl5.size()-1).getCoords().getX());
				nod5.getCoords().setY(newAl5.get(newAl5.size()-1).getCoords().getY());
				nod5.getCoords().setZ(newAl5.get(newAl5.size()-1).getCoords().getZ() + 1);
				nod5.setNext(newAl5.get(newAl5.size()-1).getNext().getNext());
				nod5.setPrevious(newAl5.get(newAl5.size()-1));
				newAl5.add(nod5);
				selfList.add(newAl5);

				selfList.remove(0);
			}else if(selfList.get(0).get(selfList.get(0).size()-1).getCoords().getZ() < selfList.get(0).get(selfList.get(0).size()-2).getCoords().getZ()) {
				ArrayList<Node> newAl1 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod1 = new Coords();
				Node nod1 = new Node();
				nod1.setCoords(cod1);
				nod1.setCharge(newAl1.get(newAl1.size()-1).getNext().getCharge());
				nod1.getCoords().setX(newAl1.get(newAl1.size()-1).getCoords().getX() - 1);
				nod1.getCoords().setY(newAl1.get(newAl1.size()-1).getCoords().getY());
				nod1.getCoords().setZ(newAl1.get(newAl1.size()-1).getCoords().getZ());
				nod1.setNext(newAl1.get(newAl1.size()-1).getNext().getNext());
				nod1.setPrevious(newAl1.get(newAl1.size()-1));
				newAl1.add(nod1);				
				selfList.add(newAl1);
				
				ArrayList<Node> newAl2 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod2 = new Coords();
				Node nod2 = new Node();
				nod2.setCoords(cod2);
				nod2.setCharge(newAl2.get(newAl2.size()-1).getNext().getCharge());
				nod2.getCoords().setX(newAl2.get(newAl2.size()-1).getCoords().getX() + 1);
				nod2.getCoords().setY(newAl2.get(newAl2.size()-1).getCoords().getY());
				nod2.getCoords().setZ(newAl2.get(newAl2.size()-1).getCoords().getZ());
				nod2.setNext(newAl2.get(newAl2.size()-1).getNext().getNext());
				nod2.setPrevious(newAl2.get(newAl2.size()-1));
				newAl2.add(nod2);
				selfList.add(newAl2);
				
				ArrayList<Node> newAl3 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod3 = new Coords();
				Node nod3 = new Node();
				nod3.setCoords(cod3);
				nod3.setCharge(newAl3.get(newAl3.size()-1).getNext().getCharge());
				nod3.getCoords().setX(newAl3.get(newAl3.size()-1).getCoords().getX());
				nod3.getCoords().setY(newAl3.get(newAl3.size()-1).getCoords().getY() - 1);
				nod3.getCoords().setZ(newAl3.get(newAl3.size()-1).getCoords().getZ());
				nod3.setNext(newAl3.get(newAl3.size()-1).getNext().getNext());
				nod3.setPrevious(newAl3.get(newAl3.size()-1));
				newAl3.add(nod3);
				selfList.add(newAl3);
				
				ArrayList<Node> newAl4 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod4 = new Coords();
				Node nod4 = new Node();
				nod4.setCoords(cod4);
				nod4.setCharge(newAl4.get(newAl4.size()-1).getNext().getCharge());
				nod4.getCoords().setX(newAl4.get(newAl4.size()-1).getCoords().getX());
				nod4.getCoords().setY(newAl4.get(newAl4.size()-1).getCoords().getY() + 1);
				nod4.getCoords().setZ(newAl4.get(newAl4.size()-1).getCoords().getZ());
				nod4.setNext(newAl4.get(newAl4.size()-1).getNext().getNext());
				nod4.setPrevious(newAl4.get(newAl4.size()-1));
				newAl4.add(nod4);
				selfList.add(newAl4);
				
				ArrayList<Node> newAl5 = (ArrayList<Node>) selfList.get(0).clone();
				Coords cod5 = new Coords();
				Node nod5 = new Node();
				nod5.setCoords(cod5);
				nod5.setCharge(newAl5.get(newAl5.size()-1).getNext().getCharge());
				nod5.getCoords().setX(newAl5.get(newAl5.size()-1).getCoords().getX());
				nod5.getCoords().setY(newAl5.get(newAl5.size()-1).getCoords().getY());
				nod5.getCoords().setZ(newAl5.get(newAl5.size()-1).getCoords().getZ() - 1);
				nod5.setNext(newAl5.get(newAl5.size()-1).getNext().getNext());
				nod5.setPrevious(newAl5.get(newAl5.size()-1));
				newAl5.add(nod5);
				selfList.add(newAl5);

				selfList.remove(0);
			}
		}
		
	}
	public ArrayList<Node> Selft(ArrayList<Node> al){
		return null;
	}
	
	public Selft() {
		
	}
	public Selft(ArrayList<Node> al) {
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
	}
	public ArrayList<Node> getResult(){
		ArrayList<Node> result = new ArrayList<Node>();
		for(int i=0; i<selfList.size(); i++) {
			result.add(selfList.get(i).get(0));
		}
		return result;
	}


}
