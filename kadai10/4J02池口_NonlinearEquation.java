//4J02 池口恭司　課題10
/* alpha：1.0
 * POSITIVE_MAX:5.0
 * 
実行結果
xNext = 4.737797636355764, f(xNext)=-0.09040917653742735, xPastNext = 0.0
xNext = 4.278145847776519, f(xNext)=-0.15993933175130398, xPastNext = 4.737797636355764
xNext = 3.594865701054022, f(xNext)=-0.21613299470460137, xPastNext = 4.278145847776519
xNext = 2.8602153928282474, f(xNext)=-0.1705471191102443, xPastNext = 3.594865701054022
xNext = 2.3782067277726964, f(xNext)=-0.0693895935643059, xPastNext = 2.8602153928282474
xNext = 2.197034326452006, f(xNext)=-0.017332711817567747, xPastNext = 2.3782067277726964
xNext = 2.1526929210955448, f(xNext)=-0.0035208121532872352, xPastNext = 2.197034326452006
xNext = 2.143723334140801, f(xNext)=-6.77756504755059E-4, xPastNext = 2.1526929210955448
xNext = 2.1419980778258125, f(xNext)=-1.2903388699502918E-4, xPastNext = 2.143723334140801
xNext = 2.1416696672604867, f(xNext)=-2.4513611796899844E-5, xPastNext = 2.1419980778258125
X = 2.1416696672604867 at iteration 9.
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
    public NonlinearEquation(){
        // 処理を実装せよ
        this.initialValue_ = 0;
        this.answer_ = 0;
        this.iteration_ = 0; 
    }

    private int _solveNLEByLinearIteration(double alpha){
        double value,      // x_k に対応
                pastValue;  // x_{k-1} に対応（初回のpastValue = x_0とする）
        pastValue = this.initialValue_;
        value = Math.sqrt(pastValue+10+alpha);
        while(Math.abs(value-pastValue)>=EPSILON){
                System.out.println("value = "+value+" , pastValue = "+pastValue);
                pastValue =value;
                value = Math.sqrt(pastValue+10+alpha);
                iteration_++;
                if(iteration_==MAXIMUM_IT)return -1;
        }
        this.answer_ = value;
        //解が見つかったら１、見つからなかったら-1を返す
        return 1;
    }
    
    private double _solveNLEByBisectionMethod(double alpha){
        double mid,smallX,bigX,value,pastMid=0.0;
        double fx0,fx1;
        bigX = POSITIVE_MAX;
        pastMid = NEGATIVE_MAX;
        smallX = NEGATIVE_MAX;
        while(true){
//                mid = (bigX+smallX)/2;
                fx0 = calcSin(smallX, alpha);
                fx1 = calcSin(bigX, alpha);
                mid = (smallX*fx1-bigX*fx0)/(fx1-fx0);
                value = calcSin(mid,alpha);
                System.out.println("xNext = "+mid+", f(xNext)="+value+", xPastNext = "+pastMid);
                if(value == 0)break;
                if(Math.abs(mid - pastMid) < EPSILON)break;
                if(Math.signum(value) == Math.signum(calcSin(smallX, alpha))){      //f(x_0)とf(x_2)が同符号の場合
                        smallX = mid;
                        //bigX = bigX;
                }else if(Math.signum(value) == Math.signum(calcSin(bigX, alpha))){
                        //smallX = smallX;
                        bigX = mid;
                }
                pastMid = mid;
                iteration_++;
                if(iteration_ == MAXIMUM_IT)return -1;
        }
        this.answer_ = mid;
        //解が見つかったら１，見つからなかったら-1を返す
        return 1;
    }
    
    private double calcSin(double mid,double alpha){
        if(mid + alpha == 0){
                return 1.0;
        }else{
                return Math.sin(mid + alpha)/(mid + alpha);
        }
    }

    public static void main(String[] args) {
        NonlinearEquation eqn = new NonlinearEquation();
        if(eqn._solveNLEByBisectionMethod(1.0)==1){
                System.out.println("X = "+eqn.answer_+" at iteration "+eqn.iteration_+".");
        }else{
                System.out.println("not found at iteration"+eqn.iteration_+".");
        }

    }
}