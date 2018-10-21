//4J02 s15015 �r�����i
package pr2calc;
import java.util.Arrays;

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

    public static void main(String[] args) {
        /*
         * main ���\�b�h���ō���쐬�������όv�Z���\�b�h��s�񓯎m�A�x�N�g���ƍs��A �s��ƃx�N�g���̐ς��v�Z���郁�\�b�h������ɓ����Ă��邩���m�F����B
         */

        // �s��E�x�N�g����`�A����щ��Z�����̈�� �i�����܂ň��ł��j �ۑ�̗v���𖞂����悤�A�e���ŉ��M�E�C�����Ă�������

        Matrix mat_vr0, mat_vr1, mat_vl0, mat_vl1, mat_m0, mat_m1, mat_m2, mat_m3, mat_m4;

        double[] vr0 = { 2.0, -3.0, 7.0 },
                vr1 = { -1.0, -2.0, 2.0};
        double[][] m0 = { { 1.0, 2.0, 3.0 }, { 3.0, 2.0, -1.0 }, { 4.0, 2.0, 6.0 } },
                m1 = { { 5.0, 3.0, 1.0 }, { 3.0, -3.0, 2.0}},
                m2 = { { 8.0, 2.0 }, { -3.0, 2.0 }, { 1.0, 6.0 } },
                m3 = { { 2.0, -3.0 }, { 4.0, 2.0 } },
                m4 = { { -5.0, -3.0, 1.0 }, { -3.0, 3.0, 2.0}},
                vl0 = { { 3.0 }, { -2.0 } },
                vl1 = { { 3.0, 7.0, -5.0, 2.0 } };

        mat_vr0 = new Matrix(vr0);
        mat_vr1 = new Matrix(vr1);
        mat_vl0 = new Matrix(vl0);
        mat_vl1 = new Matrix(vl1);
        mat_m0 = new Matrix(m0);
        mat_m1 = new Matrix(m1);
        mat_m2 = new Matrix(m2);
        mat_m3 = new Matrix(m3);
        mat_m4 = new Matrix(m4);

        System.out.println("���(1) X�x�N�g����Y�x�N�g���̓���");
        System.out.println("X�x�N�g��:");  mat_vr0.display();
        System.out.println("Y�x�N�g���F");  mat_vr1.display();
        System.out.printf("���ρF %f%n",mat_vr0.getInnerProduct(mat_vr1));
        System.out.println();

        System.out.println("���(2) �s��A,B�̐�");
        System.out.println("�s��A:");   mat_m0.display();
        System.out.println("�s��B�F");  mat_m1.display();
        if(mat_m0.multipliable(mat_m1)==true){
            System.out.println("�s��A,B�̐ρF");
            (mat_m0.multiplyMatrix(mat_m1)).display();
        }
        System.out.println();

        System.out.println("���(3) �s��A,B�̐�");
        System.out.println("�s��A:");  mat_vl0.display();
        System.out.println("�s��B�F");  mat_vl1.display();
        if(mat_vl0.multipliable(mat_vl1)==true){
            System.out.println("�s��A,B�̐ρF");
            (mat_vl0.multiplyMatrix(mat_vl1)).display();
        }
        System.out.println();

        System.out.println("���(4) �s��A,B�̐�");
        System.out.println("�s��A:");  mat_m0.display();
        System.out.println("�s��B�F");  mat_m2.display();
        if(mat_m0.multipliable(mat_m2)==true){
            System.out.println("�s��A,B�̐ρF");
            (mat_m0.multiplyMatrix(mat_m2)).display();
        }
        System.out.println();

        System.out.println("���(5) �s��A,B�̐�");
        System.out.println("�s��A:");  mat_m3.display();
        System.out.println("�s��B�F");  mat_m4.display();
        if(mat_m3.multipliable(mat_m4)==true){
            System.out.println("�s��A,B�̐ρF");
            (mat_m3.multiplyMatrix(mat_m4)).display();
        }
        System.out.println();


    }

}