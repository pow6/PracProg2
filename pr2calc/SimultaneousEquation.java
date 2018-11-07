//4J02 s15015 �r�����i
package pr2calc;
import java.util.Arrays;

public class SimultaneousEquation extends Matrix{
    //�C���X�^���X(this�Ŏg��)
    double[] answers;


    //Matrix�N���X����p���isuper�Ŏg���j
    public SimultaneousEquation(double[][] input){
        super(input);    //Matrix�̃R���X�g���N�^�Ăяo���B���̂Ƃ��A[�z��]+[��]�̔z��Ƃ���B
        //�s��v�Z�̌��ʂ�ۑ�����z��result���P�Ŗ��߂�B
        this.answers=new double[this.numOfRow];
        Arrays.fill(this.answers,1);
    }

    //�s���w�肵�A����̒l���悶�đΊp�������P�ɂ���
    public void normalize(int row){
        int i;
        double org;
        org=this.m[row][row];  //�P�ɂ���܂��̑Ίp�����̒l��ۑ�
        for(i=0;i<=numOfRow;i++){
            this.m[row][i]=this.m[row][i]/org;
        }
    }
    //�Ίp�������P�ƂȂ����s�S�̂ɓK�؂Ȓl���悶���l��p���āA���̑��̍s�̑Ή������̗v�f���O�ƂȂ�悤�ɍ����������\�b�h
    //subtractRowFrom(���������s,�����������s)
    public void subtractRowFrom(int rowFrom,int rowTo){
           int i;
           double scale;
           scale=this.m[rowTo][rowFrom]/this.m[rowFrom][rowFrom];
           for(i=0;i<=numOfRow;i++){
                this.m[rowTo][i]-=this.m[rowFrom][i]*scale;
           }
    }
    //�|���o���@�����s���郁�\�b�h
    public void solveByGaussJordan(){
        this.display();
        System.out.println();
        int i;
        for(i=0;i<this.numOfRow;i++){
            this.normalize(i);
            System.out.printf("%d�s%d��ڂ�1�ƂȂ�悤�Ɋ���A���̍s��%d��ڂ�0�ƂȂ�悤�Ɉ���\n",i,i,i);
            for(int j=0;j<this.numOfRow;j++){
                if(j==i){
                }else{
                    this.subtractRowFrom(i,j);
                }
            }
            this.display();
            System.out.println();
        }
    }
    
    public void displayAns(){
        int i;
        System.out.println("Answer:");
        for(i=0;i<this.numOfRow-1;i++){
            System.out.printf("x%d = %.2f, ",i,this.answers[i]);
        }
        System.out.printf("x%d = %.2f. \n",i,this.answers[i]);
    }

    //�K�E�X�̏����@���������郁�\�b�h
    public void solveByGauss(){
        int i,j,counter;
        this.display();
        System.out.println();
       //�O�i����
        for(i=0;i<this.numOfColumn-2;i++){
            for(j=i+1;j<this.numOfRow;j++){   //���łɌv�Z�����s��ň����Ȃ��悤��j=i����n�߂�
                if(j==this.numOfRow)break;
                this.subtractRowFrom(i, j);
            }
            this.display();
            System.out.println();
        }
        //��ޑ��
        counter=0;
        for(i=this.numOfRow-1;i>=0;i--){
            answers[i]=this.m[i][this.numOfColumn-1];
            for(j=0;j<counter;j++){
                if(counter==0)break;
                answers[i]-=this.m[i][this.numOfColumn-j-2]*answers[this.numOfColumn-j-2];
            }
            answers[i]=answers[i]/this.m[i][i];
            counter++;
        }

    }

    //���C���֐�
    public static void main(String[] args){
        SimultaneousEquation mat;
        double[][] m={
            {2,1,3,4,2},
            {3,2,5,2,12},
            {3,4,1,-1,3},
            {-1,-3,1,3,-1}};
        mat = new SimultaneousEquation(m);
        mat.solveByGauss();
        mat.displayAns();
    }
}


/***** ���s���� ******/
/*
[2.00 1.00 3.00 4.00 2.00]
[3.00 2.00 5.00 2.00 12.00]
[3.00 4.00 1.00 -1.00 3.00]
[-1.00 -3.00 1.00 3.00 -1.00]

[2.00 1.00 3.00 4.00 2.00]
[0.00 0.50 0.50 -4.00 9.00]
[0.00 2.50 -3.50 -7.00 0.00]
[0.00 -2.50 2.50 5.00 0.00]

[2.00 1.00 3.00 4.00 2.00]
[0.00 0.50 0.50 -4.00 9.00]
[0.00 0.00 -6.00 13.00 -45.00]
[0.00 0.00 5.00 -15.00 45.00]

[2.00 1.00 3.00 4.00 2.00]
[0.00 0.50 0.50 -4.00 9.00]
[0.00 0.00 -6.00 13.00 -45.00]
[0.00 0.00 0.00 -4.17 7.50]

Answer:
x0 = -0.80, x1 = -0.00, x2 = 3.60, x3 = -1.80.
*/