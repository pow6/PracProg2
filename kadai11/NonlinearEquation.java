//4J02 池口恭司　課題10
/* alpha：3.05
 * 
実行結果

 */
public class NonlinearEquation{
        
    public static final double EPSILON = 0.001;
    public static final int MAXIMUM_IT = 100;
    public static final double POSITIVE_MAX = 5.0;
    public static final double NEGATIVE_MAX = 0.0;

    private double initialValue_;
    private double answer_;
    private int iteration_;

    // コンストラクタ(最低一つ用意せよ）
    public NonlinearEquation(double init){
            // 処理を実装せよ
            this.initialValue_ = init;
            this.answer_ = 0;
            this.iteration_ = 0; 
    }

    private double _solveNLEByNewton(double alpha){
        double xNext,fxNext,xPast;
        xPast = this.initialValue_;
        while(true){
                xNext = calcSlope(alpha,xPast);
                fxNext = Math.exp(xNext) - alpha * xNext;
                System.out.println("xNext = "+xNext+", f(xNext)="+fxNext);
                if(fxNext == 0)break;
                if(Math.abs(xNext - xPast) < EPSILON)break;
                
                xPast = xNext;
                iteration_++;
                if(iteration_ == MAXIMUM_IT)return -1;
        }
        this.answer_ = xNext;
        //解が見つかったら１，見つからなかったら-1を返す
        return 1;
    }
    
    public double calcSlope(double alpha,double x){
        double numerator,denominator,h;
        h = 0.001; //微小値
        numerator = Math.exp(x) - alpha * x;
        denominator = (Math.exp(x+h)-Math.exp(x))/h;
        return numerator/denominator;
    }

    public static void main(String[] args) {
        NonlinearEquation eqn = new NonlinearEquation(1.19);
        if(eqn._solveNLEByNewton(3.05)==1){
                System.out.println("X = "+eqn.answer_+" at iteration "+eqn.iteration_+".");
        }else{
                System.out.println("not found at iteration"+eqn.iteration_+".");
        }

    }
}