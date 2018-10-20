package pr2calc;
import java.util.Arrays;

public class Matrix {
        // �K�v�ȃt�B�[���h�i�C���X�^���X�j�ϐ���錾����
    double m[][];
    int numOfRow;
    int numOfColumn;
    public Matrix() {
        // �s��̍s��,�񐔂��i�[����C���X�^���X�ϐ��̒l��0�ɏ�����
        numOfRow=0;
        numOfColumn=0;
    }

    public Matrix(double[][] input) {
        // �񎟌��z�� input �̓��e�ŁA�s��i�C���X�^���X�ϐ��j������������(��F�z�� input��0�s0��ڂ̒l���A�s���0�s0��ڂƂ���)
        numOfColumn=input.length;
        numOfRow=input[0].length;
        for(int i=0;i<numOfColumn;i++){
            for(int j=0;j<numOfRow;j++){
                m[i][j]=input[i][j];
            }
        }
    }

    public Matrix(double[] input) {
        // �ꎟ���z�� input �̓��e�ŁA�s��i�C���X�^���X�ϐ��j������������(��F�s����1�A�񐔂�input�̗v�f���Ƃ���)
        numOfColumn=input.length;
        numOfRow=1;
        for(int i=0;i<numOfColumn;i++){
            m[i][0]=input[i];
        }
    }

    public int getNumOfRow() {
        return this.m[0].length;
    }

    public int getNumOfColumn() {
        return this.m.length;
    }

    public double showsComponentOf(int rowIndex, int columnIndex) {
        // �w�肵���͈͂����݂��Ȃ��ꍇ
        if (0<rowIndex && rowIndex<this.numOfRow && 0<columnIndex && columnIndex<this.numOfColumn) {
            System.out.println("�w�肷��v�f�͑��݂��܂���.");
            System.exit(0);
        }
        // �w�肳�ꂽ�v�f�ɑΉ�����l��Ԃ�
        return this.m[columnIndex][rowIndex];
    }

    public void display() {
        // �s����e�̕\����������������
        for(int i=0;i<this.numOfColumn;i++){
            for(int j=0;j<this.numOfRow;j++){
                System.out.print(this.m[i][j]+" ");
            }
            System.out.println();
        }
    }

    // �x�N�g��A��B�̓��� A�EB�̌��ʂ�Ԃ�
    public double getInnerProduct(Matrix B) {
        // A����x�N�g���ł���ꍇ�A�G���[���b�Z�[�W��\�������� System.exit(0)
        // A, B �o���Ƃ��s�x�N�g���A���A�v�f������������Γ��ς��v�Z
        // A���s�x�N�g���AB����x�N�g���ŁA�v�f������������Γ��ς��v�Z
        // ���όv�Z���\�ȏ����𖞂����Ȃ��ꍇ�́A�G���[���b�Z�[�W��\��������System.out.exit(0)
        // �v�Z���ʂ�Ԃ�
        int sum=0;
        int elementsOfA,elementsOfB;
        elementsOfA=this.numOfRow*B.numOfColumn;
        elementsOfB=this.numOfRow*B.numOfColumn;
        if(this.numOfRow==1){
            System.out.println("A����x�N�g���Ȃ��߁AA�EB�̓��ς��v�Z�ł��܂���ł���");
            System.exit(0);
        }
        if(B.numOfColumn==1 && elementsOfA==elementsOfB){
            B=this.multiplyMatrix(B);
        }else if(B.numOfRow==1 && elementsOfA==elementsOfB){
            B=this.multiplyMatrix(B);
        }else{
            System.out.println("���όv�Z���\�ȏ����𖞂����Ă��܂���");
            System.exit(0);
        }
        for(int i=0;i<B.numOfColumn;i++){
            for(int j=0;j<B.numOfRow;j++){
                sum+=B.showsComponentOf(j,i);
            }
        }
        return sum;
    }

    // �s�񓯎m�A�������͍s��ƃx�N�g���Ƃ̐ς��v�Z����
    public Matrix multiplyMatrix(Matrix target){
        Matrix result;
        result = new Matrix();
		// �|������s��̗񐔂Ɗ|����s��̍s�����������Ȃ�
		if(this.numOfRow==target.numOfColumn){
            // �ς̌v�Z��������������
            result.numOfRow=this.numOfRow;
            result.numOfColumn=target.numOfColumn;
            for(int i=0;i<result.numOfColumn;i++){
                for(int j=0;j<result.numOfRow;j++){
                    result.m[i][j]+=this.showsComponentOf(j, i)*target.showsComponentOf(i, j);
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
        if(this.numOfRow==y.numOfColumn);
        else if(this.numOfRow>1);
        else if(this.numOfColumn*this.numOfRow==y.numOfColumn*y.numOfRow);
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

        Matrix mat0, mat1, mat2;

        double[] v0 = { 2.0, -3.0, 7.0 };
        double[][] m0 = { { 1.0, 2.0, 3.0 }, { 3.0, 2.0, -1.0 }, { 4.0, 2.0, 6.0 } },

                m1 = { { 8.0, 2.0 }, { -3.0, 2.0 }, { 1.0, 6.0 } },

                v1 = { { 3.0 }, { -2.0 } },

                d;

        mat0 = new Matrix(v0);
        mat1 = new Matrix(m1);
        mat2 = new Matrix(v1);
        // �ȉ��́A�s��E�x�N�g�����Z�̎��s�����ʕ\���̈��D�s�v�ł���΍폜���C�ۑ�̏����𖞂����L�q��V���ɒǉ����邱��

        System.out.println("v0 = ");
        mat0.display();
        System.out.println("m1 = ");
        mat1.display();
        System.out.println("v1 = ");
        mat2.display();

        System.out.println("���0a");
        System.out.println("v0 * m1 = ");
        if (mat0.multipliable(mat1) == true)
            (mat0.multiplyMatrix(mat1)).display();

        System.out.println("���0b");
        System.out.println("v0 * v1 = ");
        if (mat0.multipliable(mat2) == true)
            (mat0.multiplyMatrix(mat2)).display();
    }

}