import java.util.HashMap;

public class Main {
    static double incidentRate = .124;
    static double findingCancerGivenCancer = .87;
    static double findingNoCancerGivenNoCancer = 1;
    //these are constants
    static double live1yearnoCancer = 0.996614;
    static double live1yearwithCancer = .63;
    static double live5yearnoCancer = 0.98765;
    static double live5yearwithCancer = .87;
    static double live15yearnoCancer = 0.98765;
    static double live15yearwithCancer = .50;
    static double live25yearnoCancer = 0.974518;
    static double live25yearwithCancer = .50;

    public static void main(String[] args) {

        //This creates the root of the tree
        Node rootNode = new Node(1);

        //This creates children of the root node
        Node childNode = new Node(0.25, rootNode);
        Node childNode2 = new Node(0.75, rootNode);

        //This creates a child of a child
        Node childNode3 = new Node(0.25, childNode);

        System.out.println("test");
    }

    //generates the probabilities. for 1 test over the 10 year period
    private static void ProbGenerator_1Test(int years) {
        //create the nodes
        Node previous = null;
        Node current = null;
        for (int i = 1; i <= years; i++)
        {
            HashMap<String,Double> probs = getProbs(i);

            //create decision nodes year 1 has prob 1 of occurring
            if (i == 1)
            {
                current = new Node(1);
            }
            else if (previous != null)
            {
                current = new Node(.2);
            }

            //test node
            Node test = new Node(1,current);
            //test pos and negative node
            Node testPos = new Node(probs.get("probTestPos"),test);
            Node testNeg = new Node(probs.get("probTestNeg"),test);

            //test true negative and false negative
            Node testNegTrue = new Node(probs.get("probTrueNeg"),testNeg);
            Node testNegFalse = new Node(probs.get("probFalseNeg"), testNeg);

            //add children nodes
            test.addChild(testNeg);
            test.addChild(testPos);
            testNeg.addChild(testNegTrue);
            testNeg.addChild(testNegFalse);

            testPos.addChild(new Node(live5yearnoCancer,testPos,5));
            testPos.addChild(new Node());

            previous = current;
        }
    }

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
        probs.put("probLive1yrWithCancer",(1 - probs.get("probCancer"))*live1yearnoCancer + probs.get("probCancer") * live1yearwithCancer);
        probs.put("probDiein1yrWithCancer",1-probs.get("probLive1yrWithCancer"));

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
//
}

