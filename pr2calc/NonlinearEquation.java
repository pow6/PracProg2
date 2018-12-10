//4J02 池口恭司
/* 線形反復法で得対象の方程式
 * f(x)=x-sqrt(10+α+x)=0とする
 * αは今回10として、コンストラクタにて与える
 * x_k+1=sqrt(20+x_k)である
 */
public class NonlinearEquation{
        
    public static final double EPSILON = 0.001;
    public static final int MAXIMUM_IT = 100;
    public static final double POSITIVE_MAX = 3.0;

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
        while(1){
                mid = (bigX+smallX)/2;
                value = Math.sin(mid + alpha)/(mid + alpha);
                System.out.println("xMid = "+mid+", f(xMid)="+value+", xPastMid = "+pastMid);
                if(value == 0)break;
                if(mid < EPSILON)break;
                if(value*smallX>0){      //f(x_0)とf(x_2)が同符号の場合
                        smallX = mid;
                        //bigX = bigX;
                }else if(value*bigX){
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
    
    public static void main(String[] args) {
        NonlinearEquation eqn = new NonlinearEquation(3.0);
        if(eqn._solveNLEByLinearIteration(10)==1){
                System.out.println("X = "+eqn.answer_+" at iteration "+eqn.iteration_+".");
        }else{
                System.out.println("not found at iteration"+eqn.iteration_+".");
        }

    }
}