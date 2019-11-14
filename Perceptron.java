public class Perceptron {
    public static double[] ZERO_IN = {0,1,1,1,0, 
                                    1,0,0,0,1,
                                    0,0,0,0,1,
                                    1,0,0,0,1,
                                    1,0,0,0,1,
                                    1,0,0,0,1,
                                    0,1,1,1,0};
    public static double[] ONE_IN = {0,0,1,0,0, 
                                    0,1,1,0,0,
                                    0,0,1,0,0,
                                    0,0,1,0,0,
                                    0,0,1,0,0,
                                    0,0,1,0,0,
                                    1,1,1,1,1};
    public static double[] TWO_IN = {0,1,1,1,0, 
                                    1,0,0,0,1,
                                    0,0,0,0,1,
                                    0,0,0,1,0,
                                    0,0,1,0,0,
                                    0,1,0,0,0,
                                    1,1,1,1,1};
    public static double[] THREE_IN = {0,1,1,1,0, 
                                    1,0,0,0,1,
                                    0,0,0,1,0,
                                    0,0,1,0,0,
                                    0,0,0,1,0,
                                    1,0,0,0,1,
                                    0,1,1,1,0};
    public static double[] FOUR_IN = {0,0,1,1,0, 
                                    0,1,0,1,0,
                                    1,0,0,1,0,
                                    1,1,1,1,1,
                                    0,0,0,1,0,
                                    0,0,0,1,0,
                                    0,0,0,1,0};
    public static double[] FIVE_IN = {1,1,1,1,1, 
                                    1,0,0,0,0,
                                    1,0,0,0,0,
                                    1,1,1,1,0,
                                    0,0,0,0,1,
                                    0,0,0,0,1,
                                    1,1,1,1,0};
    public static double[] SIX_IN = {0,1,1,1,0, 
                                    1,0,0,0,1,
                                    1,0,0,0,0,
                                    1,1,1,1,0,
                                    1,0,0,0,1,
                                    1,0,0,0,1,
                                    0,1,1,1,0};
    public static double[] SEVEN_IN = {1,1,1,1,1, 
                                    0,0,0,0,1,
                                    0,0,0,1,0,
                                    0,0,1,0,0,
                                    0,0,1,0,0,
                                    0,0,1,0,0,
                                    0,0,1,0,0};
    public static double[] EIGHT_IN = {0,1,1,1,0, 
                                    1,0,0,0,1,
                                    1,0,0,0,1,
                                    0,1,1,1,0,
                                    1,0,0,0,1,
                                    1,0,0,0,1,
                                    0,1,1,1,0};
    public static double[] NINE_IN = {0,1,1,1,0, 
                                    1,0,0,0,1,
                                    1,0,0,0,1,
                                    0,1,1,1,1,
                                    0,0,0,0,1,
                                    1,0,0,0,1,
                                    0,1,1,1,0};

    public static double[] ZERO_OUT = {-1,-1,-1,-1};
    public static double[] ONE_OUT = {-1,-1,-1,1};
    public static double[] TWO_OUT = {-1,-1,1,-1};
    public static double[] THREE_OUT = {-1,-1,1,1};
    public static double[] FOUR_OUT = {-1,1,-1,-1};
    public static double[] FIVE_OUT = {-1,1,-1,1};
    public static double[] SIX_OUT = {-1,1,1,-1};
    public static double[] SEVEN_OUT = {-1,1,1,1};
    public static double[] EIGHT_OUT = {1,-1,-1,-1};
    public static double[] NINE_OUT = {1,-1,-1,1}; 

    public static double NITA = 0.01;
    public static int threshold = 0;

    double[][] weight = new double[4][35];
    double[] NET = new double[4];
    double[] testNET = new double[4];
    boolean[] correctFlag = new boolean[10];
    int incorrect = 0;
    int iteration = 0;

    public static void main(String[] args) {
        Perceptron nd = new Perceptron();

        nd.initWeight();
        
        System.out.println("학습 시작");

        while (true) {
            nd.initCorrectFlag();
            nd.incorrect = 0;
            nd.iteration++;
            
            System.out.println("<" + nd.iteration + "번째 Cycle>");

            nd.updateWeight(ZERO_IN, ZERO_OUT);
            nd.updateWeight(ONE_IN, ONE_OUT);
            nd.updateWeight(TWO_IN, TWO_OUT);
            nd.updateWeight(THREE_IN, THREE_OUT);
            nd.updateWeight(FOUR_IN, FOUR_OUT);
            nd.updateWeight(FIVE_IN, FIVE_OUT);
            nd.updateWeight(SIX_IN, SIX_OUT);
            nd.updateWeight(SEVEN_IN, SEVEN_OUT);
            nd.updateWeight(EIGHT_IN, EIGHT_OUT);
            nd.updateWeight(NINE_IN, NINE_OUT);

            if (nd.incorrect == 0)
                break;
        }

        System.out.println(nd.iteration + "번 반복 후 학습 완료");
        System.out.println("테스트 시작");

        nd.checkNum(ZERO_IN);
        nd.checkNum(ONE_IN);
        nd.checkNum(TWO_IN);
        nd.checkNum(THREE_IN);
        nd.checkNum(FOUR_IN);
        nd.checkNum(FIVE_IN);
        nd.checkNum(SIX_IN);
        nd.checkNum(SEVEN_IN);
        nd.checkNum(EIGHT_IN);
        nd.checkNum(NINE_IN);

        System.out.println("테스트 완료");
    }

    void initCorrectFlag() {
        for (int i=0; i<10; i++) 
            this.correctFlag[i] = false;
    }

    void initWeight() {
        for (int i=0; i<4; i++) {
            for (int j=0; j<35; j++) 
                this.weight[i][j] = Math.random()*10 +1;
        }
    }

    void initNET() {
        for (int i=0; i<4; i++)
            this.NET[i] = 0;
    }

    void initTestNET() {
        for (int i=0; i<4; i++)
            this.testNET[i] = 0;
    }

    void updateWeight(double[] d_in, double[] d_out) {
        boolean incorrect_flag = false;

        this.initNET();

        for (int i=0; i<4; i++) {
            for (int j=0; j<35; j++) {
                this.NET[i] += d_in[j] * this.weight[i][j];
            }
        }

        this.netToPerceptron();

        for (int i=0; i<4; i++) {
            if (d_out[i] != NET[i]) {
                for (int j=0; j<35; j++) {
                    this.weight[i][j] = this.weight[i][j] + NITA * (d_out[i] - this.NET[i]) * d_in[j];
                }
                incorrect_flag = true;
            }
        }
        if (incorrect_flag)
            this.incorrect++;
        System.out.println("다른 문자 개수 : " + this.incorrect);
    }

    void netToPerceptron() {
        for (int i=0; i<4; i++) {
            if (this.NET[i] >= 0)
                this.NET[i] = 1;
            else
                this.NET[i] = -1;
        }
    }

    void testNetToPerceptron() {
        for (int i=0; i<4; i++) {
            if (this.testNET[i] >= 0)
                this.testNET[i] = 1;
            else
                this.testNET[i] = -1;
        }
    }

    void checkNum(double[] test_in) {
        this.initTestNET();

        for (int i=0; i<4; i++) {
            for (int j=0; j<35; j++) {
                this.testNET[i] += test_in[j] * this.weight[i][j];
            }
            System.out.print(this.testNET[i] + " ");
        }
        System.out.println();

        this.testNetToPerceptron();

        if ((testNET[0]==ZERO_OUT[0])&&(testNET[1]==ZERO_OUT[1])&&(testNET[2]==ZERO_OUT[2])&&(testNET[3]==ZERO_OUT[3]))
            System.out.println("0과 일치");
        else if ((testNET[0]==ONE_OUT[0])&&(testNET[1]==ONE_OUT[1])&&(testNET[2]==ONE_OUT[2])&&(testNET[3]==ONE_OUT[3]))
            System.out.println("1과 일치");
        else if ((testNET[0]==TWO_OUT[0])&&(testNET[1]==TWO_OUT[1])&&(testNET[2]==TWO_OUT[2])&&(testNET[3]==TWO_OUT[3]))
            System.out.println("2과 일치");
        else if ((testNET[0]==THREE_OUT[0])&&(testNET[1]==THREE_OUT[1])&&(testNET[2]==THREE_OUT[2])&&(testNET[3]==THREE_OUT[3]))
            System.out.println("3과 일치");
        else if ((testNET[0]==FOUR_OUT[0])&&(testNET[1]==FOUR_OUT[1])&&(testNET[2]==FOUR_OUT[2])&&(testNET[3]==FOUR_OUT[3]))
            System.out.println("4과 일치");
        else if ((testNET[0]==FIVE_OUT[0])&&(testNET[1]==FIVE_OUT[1])&&(testNET[2]==FIVE_OUT[2])&&(testNET[3]==FIVE_OUT[3]))
            System.out.println("5과 일치");
        else if ((testNET[0]==SIX_OUT[0])&&(testNET[1]==SIX_OUT[1])&&(testNET[2]==SIX_OUT[2])&&(testNET[3]==SIX_OUT[3]))
            System.out.println("6과 일치");
        else if ((testNET[0]==SEVEN_OUT[0])&&(testNET[1]==SEVEN_OUT[1])&&(testNET[2]==SEVEN_OUT[2])&&(testNET[3]==SEVEN_OUT[3]))
            System.out.println("7과 일치");
        else if ((testNET[0]==EIGHT_OUT[0])&&(testNET[1]==EIGHT_OUT[1])&&(testNET[2]==EIGHT_OUT[2])&&(testNET[3]==EIGHT_OUT[3]))
            System.out.println("8과 일치");
        else if ((testNET[0]==NINE_OUT[0])&&(testNET[1]==NINE_OUT[1])&&(testNET[2]==NINE_OUT[2])&&(testNET[3]==NINE_OUT[3]))
            System.out.println("9과 일치");
        else 
            System.out.println("일치하는 것이 없음");
    }
}