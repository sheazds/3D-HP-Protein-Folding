package worker;

import java.util.ArrayList;
import java.util.Scanner;

public class SplitString {
	public static void main(String []args) {
		ArrayList<ArrayList<Node>> output= SplitString();
		for(ArrayList<Node> t:output) {
			for(Node n: t) {
				System.out.println("charge: "+n.getCharge()
				+ "  "+n.getCoords().toString());
			}
		}
		
		
	}
	private static ArrayList<ArrayList<Node>> SplitString() {
		ArrayList<ArrayList<Node>> myList= new ArrayList<ArrayList<Node>>();
		Scanner sc = new Scanner(System.in);
		String str = null;
		System.out.println("Please enter your sequence: ");
		str = sc.nextLine();
		
		System.out.println("your sequence is: "+"\n"+str);
		String[] ss= new String[100];
		int count = 0;
		ss[count]="";
		for(int i=0; i<str.length();i++) {
			char a = str.charAt(i);
			String b = ""+ a;
			if(a!=72 && a!= 104 && a!=80 && a!=112) {
				System.out.println("please enter a hp sequence.");
				System.out.println(a+" is not h or p");
				break;
			}
			if( a==72 || a==104 ){
				count++;
				ss[count]=b;
			}else{
				ss[count]=ss[count].concat(b);
			}
			
		} 
		//System.out.println(ss[0]+"....");
		
		if(ss[0]=="") {
			for(int i=0; i<ss.length-1;i++) {
				
				ss[i]=ss[i+1];
			}
			count--;
		}
		if(ss[0]=="") {
			for(int i=0; i<ss.length-1;i++) {
				
				ss[i]=ss[i+1];
			}
			count--;
		}
		
		for( int i = 0 ; i <=count ; i++){
			ArrayList<Node> myNodeList= new ArrayList<Node>();
			Node node= new Node();
			Coords coords= new Coords();
			//System.out.println("ss["+i+"]="+ss[i]);
			// the first line if exist, only contain Ps.
			if(ss[i].charAt(0)=='p') {
				
				for(int j=0; j<ss[0].length();j++) {
					coords= new Coords(j,0,0);
					node=new Node('p',coords);
					myNodeList.add(node);
					
				}
				//System.out.println();
				myList.add(myLinkList);
			}else if(ss[i].charAt(0)=='h'){
				//start from second line, the start char must be h
				for(int j = 0; j<ss[i].length();j++) {
					coords= new Coords(j,0,0);
					if(ss[i].charAt(j)=='p') {
						node=new Node('p',coords);
					}else if(ss[i].charAt(j)=='h') {
						node= new Node('h',coords);
					}
					
					myNodeList.add(node);
					
				}
				myList.add(myNodeList);
			
			}
			}
		return myList;
	}
	 

}
