package worker;

public class Direction {
	
	static String calculateDir(Node node) {
		String Dir = "";
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
		return Dir;
	}

}
