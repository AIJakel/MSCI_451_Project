import java.util.ArrayList;
import java.util.List;

public class Node {
    private double probabilityOfOccurring;
    private double expectedValue;
    private Node parent;
    private List<Node> children;

    public Node(double probability) {
        this.probabilityOfOccurring = probability;
        this.parent = null;
        children = new ArrayList<>();
        this.expectedValue = Double.NaN;
    }

    public Node(double probability, Node parent){
        this.probabilityOfOccurring = probability;
        children = new ArrayList<>();
        this.parent = parent;
        parent.children.add(this);
        this.expectedValue = Double.NaN;
    }

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

