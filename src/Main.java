public class Main {
    static double incidentRate = .25;
    static double findingCancerGivenCancer = .75;
    static double findingNoCancerGivenNoCancer = 1;

    public static void main(String[] args) {
        System.out.println("test");
        Node rootNode = new Node(1);
        Node childNode = new Node(0.25, rootNode);
        Node childNode2 = new Node(0.75, rootNode);
        System.out.println(rootNode);
    }

    //generates the probabilites for 1 test over the 10 year period
    private static void ProbGenerator_1Test(int years) {
        double probCancer = 1 - Math.pow(1 - incidentRate, years);

        //get test results
        double probTestPos = probCancer * findingCancerGivenCancer;
        double probTestNeg = probCancer * (1 - findingCancerGivenCancer) + 1 - probCancer;
        double probTrueNeg = (1 - probCancer) / probTestNeg;
        double probFalseNeg = 1 - probTrueNeg;
        double probLive1yrWithCancer = 0;
        double probDiein1yrWithCancer = 0;
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

