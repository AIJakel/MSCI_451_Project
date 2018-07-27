import java.util.ArrayList;
import java.util.List;

public class Node {

    //Probability that this line will occur
    private double probabilityOfOccurring;

    //Expected value is initially null because we don't know it yet
    private double expectedUtility;
    private Node parent;
    private List<Node> children;

    //This creates the root node of the whole tree
    Node(double probability) {
        this.probabilityOfOccurring = probability;
        this.parent = null;
        children = new ArrayList<>();

        //Expected value initially null because we don't know it yet
        this.expectedUtility = Double.NaN;
    }

    //This creates new nodes for the tree (not the parent)
    Node(double probability, Node parent){
        this.probabilityOfOccurring = probability;
        children = new ArrayList<>();
        this.parent = parent;
        parent.children.add(this);
        this.expectedUtility = Double.NaN;
    }

    Node(double probability, Node parent, double eu){
        this.probabilityOfOccurring = probability;
        children = new ArrayList<>();
        this.parent = parent;
        parent.children.add(this);
        this.expectedUtility = eu;
    }

    //adds child node list
    public void addChild(Node child)
    {
        children.add(child);
    }

    //checks to see if the expectedUtility was calculated yet
    public boolean hasCalculatedExpectedValue(){
        return !Double.isNaN(this.expectedUtility);
    }

    public double getProbabilityOfOccurring() {
        return probabilityOfOccurring;
    }

    public void setProbabilityOfOccurring(double probabilityOfOccurring) {
        this.probabilityOfOccurring = probabilityOfOccurring;
    }

    public double getExpectedUtility() {
        return expectedUtility;
    }

    public void setExpectedUtility(double expectedUtility) {
        this.expectedUtility = expectedUtility;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }
}

