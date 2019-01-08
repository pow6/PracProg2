//4J02 池口恭司
/* alpha = 3.0
 * POSITIVE_MAX = 3.0
 */
public class NonlinearEquation{
        
    public static final double EPSILON = 0.001;
    public static final int MAXIMUM_IT = 100;
    public static final double POSITIVE_MAX = 3.0;
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
        bigX = POSITIVE_MAX;
        smallX = 0;
        while(true){
                mid = (bigX+smallX)/2;
                value = calcSin(mid,alpha);
                System.out.println("xMid = "+mid+", f(xMid)="+value+", xPastMid = "+pastMid);
                if(value == NEGATIVE_MAX)break;
                if(Math.abs(bigX - smallX) < EPSILON)break;
                if(value*calcSin(smallX, alpha)>0){      //f(x_0)とf(x_2)が同符号の場合
                        smallX = mid;
                        //bigX = bigX;
                }else if(value*calcSin(bigX, alpha)>0){
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
        NonlinearEquation eqn = new NonlinearEquation(3.0);
        if(eqn._solveNLEByBisectionMethod(3.0)==1){
                System.out.println("X = "+eqn.answer_+" at iteration "+eqn.iteration_+".");
        }else{
                System.out.println("not found at iteration"+eqn.iteration_+".");
        }

    }
}
/*実行結果
xMid = 1.5, f(xMid)=-0.21722891503668823, xPastMid = 0.0
xMid = 0.75, f(xMid)=-0.152416351664625, xPastMid = 1.5
xMid = 0.375, f(xMid)=-0.06853149997096943, xPastMid = 0.75
xMid = 0.1875, f(xMid)=-0.014397246516950494, xPastMid = 0.375
xMid = 0.09375, f(xMid)=0.015458393324604481, xPastMid = 0.1875
xMid = 0.140625, f(xMid)=3.0810855762221834E-4, xPastMid = 0.09375
xMid = 0.1640625, f(xMid)=-0.007100983511811885, xPastMid = 0.140625
xMid = 0.15234375, f(xMid)=-0.0034104431975624946, xPastMid = 0.1640625
xMid = 0.146484375, f(xMid)=-0.001554656346030668, xPastMid = 0.15234375
xMid = 0.1435546875, f(xMid)=-6.241445899367854E-4, xPastMid = 0.146484375
xMid = 0.14208984375, f(xMid)=-1.5823549435152535E-4, xPastMid = 0.1435546875
xMid = 0.141357421875, f(xMid)=7.488218659417961E-5, xPastMid = 0.14208984375
xMid = 0.1417236328125, f(xMid)=-4.16902432041752E-5, xPastMid = 0.141357421875
X = 0.1417236328125 at iteration 12.
*/
