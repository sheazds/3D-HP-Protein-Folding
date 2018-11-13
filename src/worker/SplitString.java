package worker;

import java.util.ArrayList;
import java.util.Scanner;

public class SplitString {
	public static void main(String []args) {
		SplitString();
		
	}
	private static ArrayList<LinkList> SplitString() {
		ArrayList<LinkList> myList= new ArrayList<LinkList>();
		Scanner sc = new Scanner(System.in);
		String str = null;
		System.out.println("Please enter your sequence: ");
		str = sc.nextLine();
		
		System.out.println("your sequence is: "+"\n"+str);
		String[] ss= new String[50];
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
		
		for( int i = 0 ; i <=count ; i++){
			LinkList myLinkList= new LinkList();
			
			//System.out.println("ss["+i+"]="+ss[i]);
			// the first line if exist, only contain Ps.
			if(ss[i].charAt(0)=='p') {
				
				for(int j=0; j<ss[0].length();j++) {
					myLinkList.addNode(0, j, "p");
					
				}
				//System.out.println();
				myList.add(myLinkList);
			}else {
				//start from second line, the start char must be h
				for(int j = 0; j<ss[i].length();j++) {
					myLinkList.addNode(0, j, ss[i].substring(j));
					
				}
				myList.add(myLinkList);
			
			}
			}
		return myList;
	}
	 

}
