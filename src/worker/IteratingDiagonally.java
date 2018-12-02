package worker;

import java.util.ArrayList;
import worker.DynamicFold;

public class IteratingDiagonally {

    public static void main(String[] args)
    {
		ArrayList<Node> nodeChains = DynamicFold.splitString("hhhh");
		int size = nodeChains.size();
	
        //Generate the initial structures for the nth element in the split here.
        ArrayList<ArrayList<ArrayList<Node>>> grid = new ArrayList<ArrayList<ArrayList<Node>>>(size);
		ArrayList<ArrayList<Node>> foldedNodeChains = DynamicFold.selfFold(nodeChains);
		
		System.out.println("Node Chains");
		for (int i=0; i<foldedNodeChains.size(); i++)
		{
			System.out.println(i+1);
			for (int j=0; j<foldedNodeChains.get(i).size(); j++)
				foldedNodeChains.get(i).get(j).printChain();
		}
			
		
        for (int i=0; i<size; i++)
        {
        	ArrayList<ArrayList<Node>> row = new ArrayList<ArrayList<Node>>(size);
        	for (int j=0; j<size; j++)
        		row.add(new ArrayList<Node>());
        	row.set(i, foldedNodeChains.get(i));
        	grid.add(row);
        }
        System.out.println("");
        
        System.out.println("Cell target info");
        int diagonalSize = size - 1;
        for(int delta = 1; delta <= size-1; delta++)
        {
            for(int i=0; i<diagonalSize; i++)
            {            
                //Generate Structures from cells grid[i][i+j] and grid[i+j+1][i+delta].
                //Structures with the lowest energy levels should be added to grid[i][i+delta]
                for(int j=0; j<delta; j++)
                {
                	System.out.println("i:"+i+", j:"+j+", delta:"+delta+"\t target1:["+i+"]["+(i+j)+"] target2:["+(i+j+1)+"]["+(i+delta)+"]");
                	ArrayList<Node> target1 = grid.get(i).get(i+j);
                	ArrayList<Node> target2 = grid.get(i+j+1).get(i+delta);
                	ArrayList<Node> results = new ArrayList<Node>();
                	for (int k=0; k<target1.size(); k++)
                	{
                		for( int l=0; l<target2.size(); l++)
                		{
                			DynamicFold.generateCombinations(target1.get(k), target2.get(l));
                			results.addAll(DynamicFold.validChains);
                			DynamicFold.validChains.clear();
                		}
                	}
                	grid.get(i).set(i+delta, results);
                }

            }
            diagonalSize--;
        }
        
        //Cell grid[0][size-1] should now contain the optimal structure.
        System.out.println("results");
        ArrayList<Node> results = grid.get(0).get(grid.size()-1);
        System.out.println(results.size());
        for (int i=0; i<results.size(); i++)
	        results.get(i).printChain();

    }

}
