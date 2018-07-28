import java.util.HashMap;
import java.util.List;

public class Main {
    static double incidentRate = .124;
    static double findingCancerGivenCancer = .87;
    static double findingNoCancerGivenNoCancer = 1;
    //these are constants for living at least x years with or without cancer
    static double live1yearnoCancer = 0.996614;
    static double live1yearwithCancer = .63;
    static double live5yearnoCancer = 0.994476;
    static double live5yearwithCancer = .87;
    static double live15yearnoCancer = 0.98765;
    static double live15yearwithCancer = .50;
    static double live25yearnoCancer = 0.974518;
    static double live25yearwithCancer = .50;

    public static void main(String[] args) {

        /*
        //This creates the root of the tree
        Node rootNode = new Node("p",1);

        //This creates children of the root node
        Node childNode = new Node("c1",0.25, rootNode);
        Node childNode2 = new Node("c2",0.75, rootNode);

        //This creates a child of a child
        Node childNode3 = new Node("c3",0.25, childNode);
        List<Node> x = rootNode.getChildren();
        */
        //current bug generating nodes twice for some reason.
        Node root = ProbGenerator_1Test(1);
        System.out.println("");
        System.out.println("");
        System.out.println("The expected value of the decision tree is: " + Math.round(root.getExpectedUtility()));
        System.out.println("");
    }


    private static Node ProbGenerator_3Test (int years)
    {
        Node root = null;

        return root;
    }


    /**
     * Generates a descision tree for 1 mamography over a given set of years.
     * @param years the number years that the tree needs to be generated for
     * @return root node of the tree
     */
    private static Node ProbGenerator_1Test(int years) {
        //create the nodes
        Node root = null;
        Node previous = null;
        Node current = null;
        double probLive1yr = 0;
        for (int i = 1; i <= years; i++)
        {
            HashMap<String,Double> probs = getProbs(i);

            //create decision nodes year 1 has prob 1 of occurring
            if (i == 1)
            {
                current = new Node("year1",1);
                root = current;
            }
            else
            {
                Node previousDiffer = previous.getChild("differ1yr");
                current = new Node("year"+i, probLive1yr,previousDiffer); //this is the child of the previous's differ-> node
            }

            //test node and its children set up
            Node test = new Node("test",1,current);
            //test pos and negative node
            Node testPos = new Node("testPos",probs.get("probTestPos"),test);
            Node testNeg = new Node("testNeg",probs.get("probTestNeg"),test);

            //test true negative and false negative
            Node testNegTrue = new Node("testNegTrue", probs.get("probTrueNeg"),testNeg);
            Node testNegFalse = new Node("testNegFalse", probs.get("probFalseNeg"), testNeg);

            //attach utilities
            testPos = generateUtilities(testPos,false);
            testNegTrue = generateUtilities(testNegTrue,false);
            testNegFalse = generateUtilities(testNegFalse,true);

            //assumption patient has to get the test done in the last year
            if (i != years)
            {
                //no test node
                Node differ1yr = new Node("differ1yr", 1,current);
                Node die = new Node("die",probs.get("probDiein1yrUnknown"),differ1yr,0);
            }

            //set up for next iteration
            previous = current;
            probLive1yr = probs.get("probLive1yrUnkown");
        }
        return root;
    }

    /**
     * Generates the Utility nodes for a second till end node.
     * @param genFor the node to generate values for
     * @param cancer true if patient has cancer
     * @return the given node with utility nodes as children
     */
    private static Node generateUtilities(Node genFor, boolean cancer)
    {
        Node x = null;
        if (cancer)
        {
            x = new Node("5cancer", live5yearwithCancer,genFor,50);
            x = new Node("15cancer", live15yearwithCancer,genFor,80);
            x = new Node("25cancer", live25yearwithCancer,genFor,100);
        }
        else
        {
            x = new Node("5Nocancer", live5yearnoCancer,genFor,50);
            x = new Node("15NoCancer", live15yearnoCancer,genFor,80);
            x = new Node("25NoCancer", live25yearnoCancer,genFor,100);
        }
        return genFor;
    }


    /**
     * Generates all the probabilities for a single year node.
     * @param years the year the probabilities need to be generated for.
     * @return maps of probabilities
     */
    private static HashMap<String,Double> getProbs (int years)
    {
        HashMap<String,Double> probs = new HashMap<>();

        //generates the probabilities -> turn into function
        probs.put("probCancer",1 - Math.pow(1 - incidentRate, years));

        //get test results
        probs.put("probTestPos",probs.get("probCancer") * findingCancerGivenCancer);
        probs.put("probTestNeg",probs.get("probCancer") * (1 - findingCancerGivenCancer) + 1 - probs.get("probCancer"));

        //true neg and pos results
        probs.put("probTrueNeg",(1 - probs.get("probCancer")) / probs.get("probTestNeg"));
        probs.put("probFalseNeg",1 - probs.get("probTrueNeg"));

        //for defferal probs note the live 1 yr thing is the next roots prior prob need.
        probs.put("probLive1yrUnkown",(1 - probs.get("probCancer"))*live1yearnoCancer + probs.get("probCancer") * live1yearwithCancer);
        probs.put("probDiein1yrUnknown",1-probs.get("probLive1yrUnkown"));

        return probs;
    }

    /*
>>>>if no test prob cancer changes to
prob cancer = 1-rate^years deffered -> depending on sideways action affects how this num works
find prob test + or - updated with prob of cancer vs no cancer

***if test neg:
test neg true is 1 - prob cancer * test neg?
test neg false is (prob cancer) * test neg?


***if test pos:
>prob of living = prob of living without cancer
>prob of dying = prob of dying without cancer

*/

//need incident rate
//prob of mammography finding cancer given cancer
//prob of mammography finding cancer given no cancer = 0
//prob of surviving for 1,5,15,25 years with and without cancer
//Assume treatment always works and any deaths due to treatment are captured in death rate
//


//assumtion: uncussessful treatment is like living with cancer. ie not cured does o
//likelihood of dying in 1 year with or without cancer
// living year end nodes are ranges ie 0-5, 5-15 etc.
}

