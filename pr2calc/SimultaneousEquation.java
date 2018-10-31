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
        answers[row]/=answers[row]/org;
        for(i=0;i<=row;i++){
            this.m[row][i]=this.m[row][i]/org;
        }
    }
    //�Ίp�������P�ƂȂ����s�S�̂ɓK�؂Ȓl���悶���l��p���āA���̑��̍s�̑Ή������̗v�f���O�ƂȂ�悤�ɍ����������\�b�h
    //subtractRowFrom(���������s,�����������s)
    public void subtractRowFrom(int rowFrom,int rowTo){
           int i;
           double scale;
           scale=this.m[rowTo][rowFrom]/this.m[rowFrom][rowFrom];
           answers[rowTo]=answers[rowTo]-answers[rowFrom]*scale;
           for(i=0;i<=rowFrom;i++){
                this.m[rowTo][i]-=this.m[rowFrom][i]*scale;
           }
    }
    //�|���o���@�����s����֐�
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
            System.out.print("x"+i+" = "+this.answers[i]+", ");
        }
        System.out.println("x"+i+" = "+this.answers[i]+".");
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
        mat.solveByGaussJordan();
        mat.displayAns();
    }
}


//���s���ʁi�T?�Q�̗��̂悤�Ȍv�Z�ߒ��̏o�͂��s���A�����Ɏ��s���ʂ�\��t����j
/*
[2.0 1.0 3.0 4.0 2.0]
[3.0 2.0 5.0 2.0 12.0]
[3.0 4.0 1.0 -1.0 3.0]
[-1.0 -3.0 1.0 3.0 -1.0]

0�s0��ڂ�1�ƂȂ�悤�Ɋ���A���̍s��0��ڂ�0�ƂȂ�悤�Ɉ���
[1.0 1.0 3.0 4.0 2.0]
[0.0 2.0 5.0 2.0 12.0]
[0.0 4.0 1.0 -1.0 3.0]
[0.0 -3.0 1.0 3.0 -1.0]

1�s1��ڂ�1�ƂȂ�悤�Ɋ���A���̍s��1��ڂ�0�ƂȂ�悤�Ɉ���
[1.0 0.0 3.0 4.0 2.0]
[0.0 1.0 5.0 2.0 12.0]
[0.0 0.0 1.0 -1.0 3.0]
[0.0 0.0 1.0 3.0 -1.0]

2�s2��ڂ�1�ƂȂ�悤�Ɋ���A���̍s��2��ڂ�0�ƂȂ�悤�Ɉ���
[1.0 0.0 0.0 4.0 2.0]
[0.0 1.0 0.0 2.0 12.0]
[0.0 0.0 1.0 -1.0 3.0]
[0.0 0.0 0.0 3.0 -1.0]

3�s3��ڂ�1�ƂȂ�悤�Ɋ���A���̍s��3��ڂ�0�ƂȂ�悤�Ɉ���
[1.0 0.0 0.0 0.0 2.0]
[0.0 1.0 0.0 0.0 12.0]
[0.0 0.0 1.0 0.0 3.0]
[0.0 0.0 0.0 1.0 -1.0]

Answer:
x0 = -15.0, x1 = -9.0, x2 = 4.0, x3 = 3.0.
*/