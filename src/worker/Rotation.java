import java.util.ArrayList;

public class Rotation {
    
    /*
    Example of Code Running:
    Node n1 = new Node( 'h', new Coords(0,0,0));
    Node n2 = new Node( 'h', new Coords(1,0,0));
    Node n3 = new Node( 'h', new Coords(1,1,0));
    Node n4 = new Node( 'h', new Coords(2,1,0));

    n1.setNext(n2);
    n2.setNext(n3);
    n3.setNext(n4);

    n4.setPrevious(n3);
    n3.setPrevious(n2);
    n2.setPrevious(n1);

    Node s1 = new Node( 'h', new Coords(0,0,0));
    Node s2 = new Node( 'h', new Coords(1,0,0));
    Node s3 = new Node( 'h', new Coords(1,-1,0));

    s1.setNext(s2);
    s2.setNext(s3);

    s3.setPrevious(s2);
    s2.setPrevious(s1);

    generateCombinations(n1, s1);

    System.out.println(validChains.size() + "\n\n\n");


    for(Node n: validChains) {
        printChain(n);
        System.out.println("\n\n");
    }
    */


    static ArrayList<Node> validChains = new ArrayList<Node>();
    static int currentMaxEnergyLevel = Integer.MAX_VALUE;

    /**
     * Generate the combinations by iterating over the rotations and then shifting the second chain to the end of the
     * first chain. Rotations are calculated by applying two functions: roll and turn. Using them, the first 12
     * rotations are generated using the sequence RTTTRTTTRTTT. Then a transformation of RTR is needed to reposition
     * the chain. Then the second 12 are generated using the same sequence. For each of the 24 options, call the check
     * function which will then do the shifting.
     * @param frontChain - The chain at the beginning which we append to.
     * @param behindChain - The chain at the end which is being appended.
     */
    public static void generateCombinations(Node frontChain, Node behindChain) {

        for(int i=0; i<2; i++) {
            for(int j=0; j<3; j++) {
                roll(behindChain);
                check(frontChain, behindChain);
                for(int k=0; k<3; k++) {
                    turn(behindChain);
                    check(frontChain, behindChain);
                }
            }
            roll(behindChain);
            turn(behindChain);
            roll(behindChain);
        }
    }

    /**
     * Given two chains, try to attach the second chain to the 6 side of the last node in the first chain. A combination
     * is only valid if the nodes do not overlap together.
     * @param frontChain
     * @param behindChain
     */
    public static void check(Node frontChain, Node behindChain) {

        int[][] shifts = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};

        //Find the last node in the chain so we can append the second chain to it.
        Node lastNode = frontChain.getLast();

        //For each rotations, the front node of the second chain can be attached to any of the 4 adjacent cells of
        // the last node of the first chain.
        for(int[] shift: shifts) {
            Node transformedChain = shift(behindChain, lastNode.getCoords().getX()+shift[0], lastNode.getCoords().getY()+shift[1], lastNode.getCoords().getZ()+shift[2]);

            //If the combination is valid (meaning that the chains do not overlap), then add it to the valid nodes.
            if(isValidCombination(frontChain, transformedChain)) {

                //Create a new chain by appending the transformed chain to the back of the front chain.
                Node newFrontChain = duplicate(frontChain);
                Node frontChainLastNode = newFrontChain.getLast();

                //Update the next and previous references
                frontChainLastNode.setNext(transformedChain);
                transformedChain.setPrevious(frontChainLastNode);

                //Calculate the energy level of the structure.
                int energyLevel = calculateEnergyLevel(newFrontChain);

                //If it equals the max energy level found, add it to the valid chains.
                if(energyLevel == currentMaxEnergyLevel) {
                    validChains.add(newFrontChain);
                }
                //If it is less then, then reset the valid chains and add it since we only care about the lowest ones.
                else if(energyLevel < currentMaxEnergyLevel) {
                    validChains = new ArrayList<Node>();
                    validChains.add(newFrontChain);
                    currentMaxEnergyLevel = energyLevel;
                }
                //If the energy level is less then the max, ignore it since the algorithm is greedy and only cares
                // about finding the shapes with the lowest energy levels.
            }
        }
    }


    /**
     * Function that rolls the chain where roll rotates the chain 90 degrees away from you. This means the bottom is now
     * the side facing you.
     * @param frontChain - Head node of the chain.
     */
    public static void roll(Node frontChain) {

        Node nextNode = frontChain;

        do {
            int tempX = nextNode.getCoords().getX();
            int tempY = nextNode.getCoords().getY();
            int tempZ = nextNode.getCoords().getZ();

            nextNode.getCoords().setX(tempX);
            nextNode.getCoords().setY(tempZ);
            nextNode.getCoords().setZ(-tempY);

            nextNode = nextNode.getNext();
        } while (nextNode != null);

    }

    /**
     * Function that turns the chain where turn rotates the chain 90 degrees about the z axis. So the top side becomes
     * the left side of the new chain.
     * @param frontChain - Head node of the chain.
     */
    public static void turn(Node frontChain) {

        Node nextNode = frontChain;

        do {
            int tempX = nextNode.getCoords().getX();
            int tempY = nextNode.getCoords().getY();
            int tempZ = nextNode.getCoords().getZ();

            nextNode.getCoords().setX(-tempY);
            nextNode.getCoords().setY(tempX);
            nextNode.getCoords().setZ(tempZ);

            nextNode = nextNode.getNext();
        } while (nextNode != null);

    }

    public static boolean isValidCombination(Node frontChain, Node behindChain) {
        return true;
    }

    public static int calculateEnergyLevel(Node chainHead) {
        return 0;
    }

    /**
     * Returns a new copy of the node chain with the given shift applied to it.
     * @param nodeChain - The first node in the chain.
     * @param shiftX - How much to shift the chain in the x direction.
     * @param shiftY - How much to shift the chain in the y direction.
     * @return The first node in the shifted chain.
     */
    public static Node shift(Node nodeChain, int shiftX, int shiftY, int shiftZ) {
        Node newHead = duplicate(nodeChain);
        Node nextNode = newHead;
        do {
            nextNode.getCoords().setX(nextNode.getCoords().getX()+shiftX);
            nextNode.getCoords().setY(nextNode.getCoords().getY()+shiftY);
            nextNode.getCoords().setZ(nextNode.getCoords().getZ()+shiftZ);
            nextNode = nextNode.getNext();
        } while(nextNode != null);

        return  newHead;
    }

    /**
     * Duplicates the given node chain.
     * @param oldHead - The first node in the chain to be duplicated.
     * @return The first node in the new chain.
     */
    public static Node duplicate(Node oldHead) {
        Node newHead = oldHead.clone();

        Node oldTemp = oldHead;
        Node newTemp = newHead;

        while(oldTemp.hasNext()) {
            Node oldNext = oldTemp.getNext();
            Node newNext = oldNext.clone();
            newTemp.setNext(newNext);
            newNext.setPrevious(newTemp);
            oldTemp = oldNext;
            newTemp = newNext;
        }

        return newHead;
    }

    public static void printChain(Node nodeChain) {

        do {
            System.out.println(nodeChain);
            nodeChain = nodeChain.getNext();
        } while(nodeChain != null);
    }
}

