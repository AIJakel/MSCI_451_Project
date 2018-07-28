import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

public class Node {

    //Probability that this line will occur
    private double probabilityOfOccurring;

    //Expected value is initially null because we don't know it yet
    private double expectedUtility;
    private Node parent;
    private List<Node> children;
    private String name;

    //This creates the root node of the whole tree
    Node(String name, double probability) {
        this.probabilityOfOccurring = probability;
        this.parent = null;
        children = new ArrayList<>();
        this.name = name;

        //Expected value initially null because we don't know it yet
        this.expectedUtility = 0;
    }

    //This creates new nodes for the tree (not the parent)
    Node(String name, double probability, Node parent) {
        this.probabilityOfOccurring = probability;
        children = new ArrayList<>();
        this.parent = parent;
        parent.children.add(this);
        this.expectedUtility = 0;
        this.name = name;
    }

    Node(String name, double probability, Node parent, double eu) {
        this.probabilityOfOccurring = probability;
        children = new ArrayList<>();
        this.parent = parent;
        parent.children.add(this);
        this.expectedUtility = eu;
        this.name = name;
    }

    //adds child node list
    public void addChild(Node child) {
        children.add(child);
    }

    //checks to see if the expectedUtility was calculated yet
    public boolean hasCalculatedExpectedValue() {
        return !Double.isNaN(this.expectedUtility);
    }

    public double getProbabilityOfOccurring() {
        return probabilityOfOccurring;
    }

    public void setProbabilityOfOccurring(double probabilityOfOccurring) {
        this.probabilityOfOccurring = probabilityOfOccurring;
    }

    public double getExpectedUtility() {
        if (!hasChildren()) {
            System.out.println(expectedUtility);
            return expectedUtility;
        } else {
            double utility = 0;
            for (Node child : this.children) {
                utility += child.getExpectedUtility() * child.getProbabilityOfOccurring();
            }
            return utility;
        }
    }

    public boolean hasChildren() {
        return this.children.size() > 0;
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

    public String getName() {
        return name;
    }

    public Node getChild(String name) {
        for (Node child : this.children) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return new Node("ERROR", 0);
    }
}

