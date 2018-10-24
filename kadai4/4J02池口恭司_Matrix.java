//4J02 s15015 �r�����i
package pr2calc;
import java.util.Arrays;
import java.math.*;

//m[�s][��]
public class Matrix {
    // �K�v�ȃt�B�[���h�i�C���X�^���X�j�ϐ���錾����
    double[][] m;
    int numOfRow;
    int numOfColumn;
    public Matrix() {
        // �s��̍s��,�񐔂��i�[����C���X�^���X�ϐ��̒l��0�ɏ�����
        this.numOfRow=0;
        this.numOfColumn=0;
   }

    public Matrix(int row, int column){
        this.numOfColumn = column;
        this.numOfRow = row;
        this.m = new double[this.numOfRow][this.numOfColumn];
        for(int i=0;i<this.numOfRow;i++){
            for(int j=0;j<this.numOfColumn;j++){
                this.m[i][j]=0;
            }
        }
    }

    public Matrix(double[][] input) {
        // �񎟌��z�� input �̓��e�ŁA�s��i�C���X�^���X�ϐ��j������������(��F�z�� input��0�s0��ڂ̒l���A�s���0�s0��ڂƂ���)
        this.numOfColumn=input[0].length;
        this.numOfRow=input.length;
        this.m = new double[this.numOfRow][this.numOfColumn];   //java�̓񎟌��z��͂���Ȋ����ł��Ȃ��Ƃ���
        for(int i=0;i<this.numOfRow;i++){
            for(int j=0;j<this.numOfColumn;j++){
                this.m[i][j]=input[i][j];
            }
        }
    }

    public Matrix(double[] input) {
        // �ꎟ���z�� input �̓��e�ŁA�s��i�C���X�^���X�ϐ��j������������(��F�s����1�A�񐔂�input�̗v�f���Ƃ���)
        this.numOfColumn=input.length;
        this.numOfRow=1;
        this.m = new double[this.numOfRow][this.numOfColumn];
        for(int i=0;i<this.numOfColumn;i++){
            this.m[0][i]=input[i];
        }
    }

    public int getNumOfRow() {
        return this.m.length;
    }

    public int getNumOfColumn() {
        return this.m[0].length;
    }

    public double showsComponentOf(int rowIndex, int columnIndex) {
        // �w�肵���͈͂����݂��Ȃ��ꍇ
        if (0<=rowIndex && rowIndex<this.numOfRow && 0<=columnIndex && columnIndex<this.numOfColumn) {
       }else{
            System.out.println("�y�G���[�z�w�肷��v�f�͑��݂��܂���.");
            System.out.printf("�y�G���[�znumOfRow=%d numOfColumn=%d => rowIndex=%d columnIndex=%d\n",this.numOfRow,this.numOfColumn,rowIndex,columnIndex);
            System.exit(0);
       }
        // �w�肳�ꂽ�v�f�ɑΉ�����l��Ԃ�
        return this.m[rowIndex][columnIndex];
    }

    public void display() {
        // �s����e�̕\����������������
        for(int i=0;i<this.numOfRow;i++){
            System.out.print("[");
            for(int j=0;j<this.numOfColumn;j++){
                System.out.print(this.m[i][j]);
                if(j!=this.numOfColumn-1)System.out.print(" ");
            }
            System.out.println("]");
        }
        //System.out.println();
    }

    // �x�N�g��A��B�̓��� A�EB�̌��ʂ�Ԃ�
    public double getInnerProduct(Matrix B) {
        // A����x�N�g���ł���ꍇ�A�G���[���b�Z�[�W��\�������� System.exit(0)
        // A, B �o���Ƃ��s�x�N�g���A���A�v�f������������Γ��ς��v�Z
        // A���s�x�N�g���AB����x�N�g���ŁA�v�f������������Γ��ς��v�Z
        // ���όv�Z���\�ȏ����𖞂����Ȃ��ꍇ�́A�G���[���b�Z�[�W��\��������System.out.exit(0)
        // �v�Z���ʂ�Ԃ�
        double sum=0;
        int elementsOfA,elementsOfB;
        elementsOfA=this.numOfRow*this.numOfColumn;
        elementsOfB=B.numOfRow*B.numOfColumn;
        if(this.numOfColumn==1 && this.numOfRow>1){
            System.out.println("�y�G���[�zA����x�N�g���Ȃ��߁AA�EB�̓��ς��v�Z�ł��܂���ł���");
            System.exit(0);
        }
        if(B.numOfColumn==1 && elementsOfA==elementsOfB){
        }else if(B.numOfRow==1 && elementsOfA==elementsOfB){
        }else{
            System.out.println("�y�G���[�zA��B�̎������i�v�f���j���������Ȃ��A�܂��v�Z�\�ȑg�ݍ��킹�ł͂���܂���ł���");
            System.exit(0);
        }
        for(int j=0;j<B.numOfColumn;j++){
                sum+=this.showsComponentOf(0,j)*B.showsComponentOf(0,j);
        }
        return sum;
    }

    // �s�񓯎m�A�������͍s��ƃx�N�g���Ƃ̐ς��v�Z����
    public Matrix multiplyMatrix(Matrix target){
        Matrix result;
        result = new Matrix(this.numOfRow,target.numOfColumn);
		// �|������s��̗񐔂Ɗ|����s��̍s�����������Ȃ�
		if(this.numOfColumn==target.numOfRow){
            // �ς̌v�Z��������������
            for(int i=0;i<result.numOfRow;i++){
                for(int j=0;j<result.numOfColumn;j++){
                   for(int z=0;z<this.numOfColumn;z++){ 
                        result.m[i][j]+=this.showsComponentOf(i, z)*target.showsComponentOf(z, j);
                    }
                }
            }
        }else{
            System.out.println("�v�f�����v�Z�ł���g�ݍ��킹�ƂȂ��Ă��܂���");
            System.exit(0);
        }
        // �ς̌��ʂ�Matrix�^�ŕԂ�
        return result;
    }

    public boolean multipliable(Matrix y){
        if(this.numOfColumn==y.numOfRow && this.numOfColumn>1);
        else{
            System.out.println("�v�f�����v�Z�ł���g�ݍ��킹�ƂȂ��Ă��܂���.");
            return false;
        }
        return true;
    }

    public Matrix transpose(){
        Matrix result;
        result = new Matrix(this.getNumOfColumn(),this.getNumOfRow());  //�]�u�Ȃ̂ŁA�s�Ɨ񂪋t
        for(int i=0;i<result.numOfRow;i++){
            for(int j=0;j<result.numOfColumn;j++){
                result.m[i][j]=this.showsComponentOf(j, i);
            }
        }
        return result;
    }

    public Matrix rotate(double deg){
        Matrix turn;
        int row,column;
        double rad;
        row = this.getNumOfRow();
        column = this.getNumOfColumn();
        if(deg<-360 || deg>360){
            System.out.println("�y�G���[�z�p�x�̎w�肪�s�K���ł�");
            System.exit(0);
        }else if(column>1){
            System.out.println("�y�G���[�z�^����ꂽ�x�N�g������x�N�g���ł͂���܂���");
            System.exit(0);
        }
        turn = new Matrix(2,2);
        rad = convertIntoRadian(deg);
        turn.m[0][0]=Math.cos(rad);
        turn.m[0][1]=(-1)*Math.sin(rad);
        turn.m[1][0]=Math.sin(rad);
        turn.m[1][1]=Math.cos(rad);
        return turn.multiplyMatrix(this);
    }

    public static double convertIntoRadian(double theta){
        return Math.toRadians(theta);
        //return 2*Math.PI*360/theta;
    }

    public static void main(String[] args) {
        Matrix mat0,mat1,mat2;
        double[][] m0 = { { 1.0, 2.0}, { 3.0, 2.0}, { 4.0, 2.0} },
                v0 = { {-3.0}, {3.0}},
                v1 = { {2.0}, {-3.464}};
        mat0 = new Matrix(m0);
        mat1 = new Matrix(v0);
        mat2 = new Matrix(v1);
        System.out.println("1.�Y���s����f�B�X�v���C�ɕ\��");
        mat0.display();
        System.out.println("2.�Y���s��̓]�u�s����f�B�X�v���C�ɕ\��");
        mat0.transpose().display();
        System.out.println("3.�i-3.0,3.0�j��45����]");
        System.out.println("��]�O"); mat1.display();
        System.out.println("��]��"); mat1.rotate(45).display();
        System.out.println("4.(2.0,-3.464)��60����]");
        System.out.println("��]�O"); mat2.display();
        System.out.println("��]��"); mat2.rotate(60).display();;
        

    }

}