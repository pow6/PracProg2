//4J02 �r�����i
/* ���`�����@�œ��Ώۂ̕�����
 * f(x)=x-sqrt(10+��+x)=0�Ƃ���
 * ���͍���10�Ƃ��āA�R���X�g���N�^�ɂė^����
 * x_k+1=sqrt(20+x_k)�ł���
 */
public class NonlinearEquation{
        
    public static final double EPSILON = 0.001;
    public static final int MAXIMUM_IT = 100;
    
    private double initialValue_;
    private double answer_;
    private int iteration_;

    // �R���X�g���N�^(�Œ��p�ӂ���j
    public NonlinearEquation(double init){
            // ��������������
            this.initialValue_ = init;
            this.answer_ = 0;
            this.iteration_ = 0; 
    }
    
    private int _solveNLEByLinearIteration(double alpha){
        double value,      // x_k �ɑΉ�
                pastValue;  // x_{k-1} �ɑΉ��i�����pastValue = x_0�Ƃ���j
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
        //��������������P�A������Ȃ�������-1��Ԃ�
        return 1;
    }
    
    
    public static void main(String[] args) {
        NonlinearEquation eqn = new NonlinearEquation(4.0); //������������4.0�ɐݒ�
        if(eqn._solveNLEByLinearIteration(10)==1){
                System.out.println("X = "+eqn.answer_+" at iteration "+eqn.iteration_+".");
        }else{
                System.out.println("not found at iteration"+eqn.iteration_+".");
        }

    }
}