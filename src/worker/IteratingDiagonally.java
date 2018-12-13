package worker;

import java.util.ArrayList;
import worker.DynamicFold;

public class IteratingDiagonally {
	public static void main(String[] args)
	{
    	String sequence = "hpphppphp";
    	iterateDiagonally(sequence, -1, 0, 0);
	}

	public static ArrayList<String> iterateDiagonally(String sequence, int hhScore, int hpScore, int ehScore)
    {
		DynamicFold.hhScore = hhScore;
		DynamicFold.hpScore = hpScore;
		DynamicFold.ehScore = ehScore;
		ArrayList<Node> nodeChains = DynamicFold.splitString(sequence);
		/*
		System.out.println("Sequence: "+sequence);
		System.out.println("SubSequence nodeChains");
		for (int i=0; i<nodeChains.size(); i++)
				nodeChains.get(i).printChain();
		System.out.println("");
		*/
		
        //Generate the initial structures for the nth element in the split here.
		int size = nodeChains.size();
        ArrayList<ArrayList<ArrayList<Node>>> grid = new ArrayList<ArrayList<ArrayList<Node>>>(size);
		ArrayList<ArrayList<Node>> foldedNodeChains = DynamicFold.selfFold(nodeChains);
		
		/*
		System.out.println("Node Chains");
		for (int i=0; i<foldedNodeChains.size(); i++)
		{
			System.out.println("Subsequence " + (i+1) + " folds");
			for (int j=0; j<foldedNodeChains.get(i).size(); j++)
				foldedNodeChains.get(i).get(j).printChain();
		}
        System.out.println("");
		*/
		
        for (int i=0; i<size; i++)
        {
        	ArrayList<ArrayList<Node>> row = new ArrayList<ArrayList<Node>>(size);
        	for (int j=0; j<size; j++)
        		row.add(new ArrayList<Node>());
        	row.set(i, foldedNodeChains.get(i));
        	grid.add(row);
        }
        
        int diagonalSize = size - 1;
        for(int delta = 1; delta <= size-1; delta++)
        {
            for(int i=0; i<diagonalSize; i++)
            {            
                //Generate Structures from cells grid[i][i+j] and grid[i+j+1][i+delta].
                //Structures with the lowest energy levels should be added to grid[i][i+delta]
            	
                for(int j=0; j<delta; j++)
                {
                    //System.out.println("Cell target info");
                	//System.out.println("i:"+i+", j:"+j+", delta:"+delta+"\t target1:["+i+"]["+(i+j)+"] target2:["+(i+j+1)+"]["+(i+delta)+"]");
                	DynamicFold.currentMaxEnergyLevel = Integer.MAX_VALUE;
                	ArrayList<Node> target1 = grid.get(i).get(i+j);
                	ArrayList<Node> target2 = grid.get(i+j+1).get(i+delta);
                	ArrayList<Node> results = new ArrayList<Node>();
                	for (int k=0; k<target1.size(); k++)
                	{
                		for( int l=0; l<target2.size(); l++)
                		{
                        	int lowestEnergy = Integer.MAX_VALUE;
                			DynamicFold.generateCombinations(target1.get(k), target2.get(l));
                			ArrayList<Node> testResults = DynamicFold.validChains;
                			DynamicFold.validChains = new ArrayList<Node>();
                			if (testResults.size() > 0)
                			{
                				int testEnergy = DynamicFold.energy(testResults.get(0));
	                			if (testEnergy < lowestEnergy)
	                			{
	                				lowestEnergy = testEnergy;
	                				results = new ArrayList<Node>();
	                			}
	                			results.addAll(testResults);
                			}
                		}
                	}
                	grid.get(i).set(i+delta, results);
                }
            }
            diagonalSize--;
        }
        //Cell grid[0][size-1] should now contain the optimal structure.
        ArrayList<Node> results = grid.get(0).get(grid.size()-1);
        
        //convert results to strings of directional changes
        ArrayList<String> resultDirections = new ArrayList<String>();
        for (int i=0; i<results.size(); i++)
        	resultDirections.add(Direction.calculateDir(results.get(i)));
        
        
        System.out.println("Results = " + results.size());
        for (int i=0; i<results.size(); i++)
        {
        	System.out.print("Energy: " + DynamicFold.energy(results.get(i)) + " | " + results.get(i).getChainString());
        	System.out.println("\nDirections: " + resultDirections.get(i));
        }
        
        return resultDirections;
    }

}
