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
            System.out.printf("x%d = %.2f, ",i+1,this.answers[i]);
        }
        System.out.printf("x%d = %.2f. \n",i,this.answers[i]);
    }

    //�K�E�X�̏����@���������郁�\�b�h
    public void solveByGauss(){
        int i,j,counter;
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
    //�����I��@
    public void solveByGaussWithPartialSelection(){
        int i,j,counter;
       //�O�i����
        for(i=0;i<this.numOfColumn-2;i++){
            this.exchangeRows(i, this.selectPivotFromRow(i));
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

    protected void exchangeRows(int row1, int row2) {
        double tmp;
        for(int i=0;i<this.numOfColumn;i++){
            tmp=this.m[row1][i];
            this.m[row1][i]=this.m[row2][i];
            this.m[row2][i]=tmp;
        }
    }

    protected int selectPivotFromRow(int targetColmn) {
        int top=targetColmn;
        for(int i=targetColmn+1;i<this.numOfRow;i++){
            if(Math.abs(this.m[top][targetColmn])<Math.abs(this.m[i][targetColmn])){
                top=i;
            }
        }
        return top;
    } 
    protected int selectPivotFromRow(int targetColmn,int start) {
        int top=start;
        for(int i=start+1;i<this.numOfRow;i++){
            if(Math.abs(this.m[top][targetColmn])<Math.abs(this.m[i][targetColmn])){
                top=i;
            }
        }
        return top;
    }
    //���S�I��@
    public void solveByGaussWithCompleteSelection(){
        int i,j,counter;
        int z,y,topCol,topRow;
        int[] answersOrder = new int[this.numOfRow];
        int tmp;
        double[] tmpArray = new double[this.numOfRow];
        for(i=0;i<this.numOfRow;i++){
            answersOrder[i]=i;
        }
       //�O�i����
        for(i=0;i<this.numOfColumn-2;i++){  //�s��̍Ō�͕������̘a�A�܂��A���̈�O�͌v�Z����K�v���Ȃ�����-2
            topCol=i;
            topRow=i;
            for(y=i+1;y<this.numOfColumn-1;y++){
                if(Math.abs(this.m[topRow][topCol])<Math.abs(this.m[selectPivotFromRow(y,i)][y])){
                    topCol=y;
                    topRow=selectPivotFromRow(y,i);
                }
            }
            //��������y�����ւ��ɂ��A���̏��Ԃ̕ύX�̕ۑ��z
            exchangeRowsAndColumns(i, i, topRow, topCol);
            tmp=answersOrder[i];
            answersOrder[i]=answersOrder[topCol];
            answersOrder[topCol]=tmp;
            //�����܂�
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
        //��������y���̏��Ԃ𐳂��z
        for(i=0;i<this.numOfRow;i++){
            tmpArray[answersOrder[i]]=answers[i];
        }
        for(i=0;i<this.numOfRow;i++){
            answers[i]=tmpArray[i];
        }
        //�����܂�

    }

    protected void exchangeRowsAndColumns(int row1, int column1, int row2, int column2){
        double tmp;
        this.exchangeRows(row1, row2);
        for(int i=0;i<this.numOfRow;i++){
            tmp=this.m[i][column1];
            this.m[i][column1]=this.m[i][column2];
            this.m[i][column2]=tmp;
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
        mat.display(); System.out.println();
        mat.solveByGaussWithCompleteSelection();
        mat.displayAns();
    }
}


/***** ���s���� ******/
/** ���S�I��@ ***
[2.00 1.00 3.00 4.00 2.00]
[3.00 2.00 5.00 2.00 12.00]
[3.00 4.00 1.00 -1.00 3.00]
[-1.00 -3.00 1.00 3.00 -1.00]

[5.00 2.00 3.00 2.00 12.00]
[0.00 -0.20 0.20 2.80 -5.20]
[0.00 3.60 2.40 -1.40 0.60]
[0.00 -3.40 -1.60 2.60 -3.40]

[5.00 2.00 3.00 2.00 12.00]
[0.00 2.80 0.20 -0.20 -5.20]
[0.00 0.00 2.50 3.50 -2.00]
[0.00 0.00 -1.79 -3.21 1.43]

[5.00 2.00 2.00 3.00 12.00]
[0.00 2.80 -0.20 0.20 -5.20]
[0.00 0.00 3.50 2.50 -2.00]
[0.00 0.00 0.00 0.51 -0.41]

Answer:
x1 = -0.80, x2 = 0.00, x3 = 3.60, x3 = -1.80.
*/
