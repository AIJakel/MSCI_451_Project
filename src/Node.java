import java.util.ArrayList;
import java.util.List;

public class Node {

    //Probability that this line will occur
    private double probabilityOfOccurring;

    //Expected value is initially null because we don't know it yet
    private double expectedValue;
    private Node parent;
    private List<Node> children;

    //This creates the root node of the whole tree
    Node(double probability) {
        this.probabilityOfOccurring = probability;
        this.parent = null;
        children = new ArrayList<>();

        //Expected value initially null because we don't know it yet
        this.expectedValue = Double.NaN;
    }

    //This creates new nodes for the tree (not the parent)
    Node(double probability, Node parent){
        this.probabilityOfOccurring = probability;
        children = new ArrayList<>();
        this.parent = parent;
        parent.children.add(this);
        this.expectedValue = Double.NaN;
    }

    //checks to see if the expectedValue was calculated yet
    public boolean hasCalculatedExpectedValue(){
        return !Double.isNaN(this.expectedValue);
    }

    public double getProbabilityOfOccurring() {
        return probabilityOfOccurring;
    }

    public void setProbabilityOfOccurring(double probabilityOfOccurring) {
        this.probabilityOfOccurring = probabilityOfOccurring;
    }

    public double getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(double expectedValue) {
        this.expectedValue = expectedValue;
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

