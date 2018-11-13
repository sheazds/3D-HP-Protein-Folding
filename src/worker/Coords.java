package worker;

/**
 * 
 * Coords.java
 *
 * This class holds coordinates of a node in the form of an x, y and z integer
 *
 * @author Aaron Germuth
 * @date 2013-03-16
 */
@SuppressWarnings("rawtypes")
public class Coords implements Comparable{
	private int x; 
	private int y;
	private int z;
	
	public Coords(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Coords(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString(){
		return "Coords: x = " + this.x + ", y = " + this.y + ", z = " + this.z;
	}
	
	public void print(){
		System.out.println("x: " + this.x);
		System.out.println("y: " + this.y);
		System.out.println("z: " + this.z);
	}
	
	@Override
	public boolean equals(Object another)	{
		if (!(another instanceof Coords))
			return false;
		if(this.x == ((Coords) another).x 
				&& this.y == ((Coords) another).y
				&& this.z == ((Coords) another).z){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode()
	{
		return x+y+z;
	}
	
	/**
	 * Compare method, not implemented as i'm not sure we will need it
	 * TODO look into this
	 * 
	 * @param another
	 * @return not sure
	 */
	@Override
	public int compareTo(Object arg0) {
		System.err.println("compare methods of compare method was called, it has not yet been implemented");
		return -1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}	
}
