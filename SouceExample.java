//package pr2calc;
public class SourceExample{  

    public int a;            // ����3�ϐ��E�E�E�t�B�[���h�ϐ��i���\�b�h�̊O�ɂ���j�B
    public int[][] b;        // �N���X���́Amain()���\�b�h�������S���\�b�h�ŗ��p�\
    public String str;       

    /* �ȉ��̃��\�b�h�i�֐��j�̓R���X�g���N�^�B�I�u�W�F�N�g�̐����i& �������j���s�� 
    * �����̐ݒ�͎��R�B�������قȂ�΁A�����p�ӂ��Ă��ǂ�
    */
/*    public SourceExample(){   // �e�ϐ��̏����������s�i��Ȃ̂ŁA���g�͓K���ł��j
        int h,i;    // ���\�b�h���Ő錾�����ϐ��́A���\�b�h���݂̂ŗ��p��

        this.a = -1;    // �t�B�[���h�ϐ��Ƃ��Ē�`���ꂽ�ϐ��́A�N���X���ǂ��ł����p�ithis.a �E�E�E "����(= this)�I�u�W�F�N�g(�C���X�^���X)���g�̎��ϐ�a"�j
        this.b = new int[2][2];
*/
    /* java �ł́A�z��ƕ�����̓I�u�W�F�N�g�Ƃ��ėp�ӂ���Ă��邽�߁A�V�����z��A
    * �������錾���鎞�ɂ� "new" ���g��(��̗�ł́Ab ��2�~2�̐����^�񎟌��z��
    * �Ƃ��Đ錾���Ă���j
    */
/*
        for(h=0;h<this.b.length;h++){		// �񎟌��z��̒l�� -1 �ŏ�����
            for(i=0;i<this.b[0].length;i++)		// �z��Ɋւ��ẮA"new" ��p������
                this.b[h][i] = -1;			// ���������邱�Ƃ��\�i�ڍׂ�main
        }						// ���\�b�h�����Q�Ɓj

    // this.b.length : �z��̑��v�f�̗v�f���Athis.b[0].length : �z��̑��v�f�̗v�f��

        this.str = "";	// ������^�̓I�u�W�F�N�g�Ƃ��ėp�ӂ����ƋL�q�������A���ɂ悭
    }			// �p������^�ł��邽�߁A���̂悤�ȋL�@���F�߂��Ă���
                // �i new String(""); �Ƃ����������ł����ROK �j
*/

    // �t�B�[���h�ϐ�a, b, str�̒l������ŏ������i�ݒ�j����R���X�g���N�^��������L�q����
    public SourceExample(){
        this.a =;
        this.b ;
        this.str;
    }


    /* �ȍ~�Amain���\�b�h�ȊO�̃��\�b�h�́A�R���X�g���N�^�Ő������ꂽ�I�u�W�F�N�g
    * ����Ď��s�����
    */

    public void setA(int value){    // ���\�b�h��ʂ��āA�t�B�[���h�ϐ��̒l��ݒ肷��
        this.a = value;		// �isetter�ƌĂ΂��j
    }

    public int getA(){		// ���\�b�h��ʂ��āA�t�B�[���h�ϐ��̒l��Ԃ�
        return this.a;		// �igetter�ƌĂ΂��j
    }

    public void setB(int[][] value){
    //    this.b = new int[value.length][value[0].length];
        this.b = value;
    }

    public int[][] getB(){
        return this.b;
    }

    public void setStr(String value){
        this.str = value;
    }

    public String getStr(){
        return this.str;
    }

    public void showAllContentsOfB(){
    // �e���ŕK�v�ȓ��e���L�q���邱��
        System.out.println(this.b);
        System.out.println(this.str);
    }

    /* ���̃v���O���������s����ƁA�ȉ��� main ���\�b�h�ɋL�q���ꂽ���삪���s����� */
    public static void main(String[] args){
        SourceExample    ex;    // SourceExample �N���X�̃I�u�W�F�N�g ex ��錾
        int value[][] = {{1,2,3},{4,5,6},{7,8,9}}; // "new"��p���Ȃ��A�񎟌��z������������鏑����

    //    ex = new SourceExample();    // SourceExample �N���X�̃I�u�W�F�N�g ex �𐶐�
    //    ex.setA(10);    // ex �I�u�W�F�N�g�̃t�B�[���h this.a �̒l��10�ɕύX�G

    // ��̓�s���R�����g�A�E�g������ŁAa��3�Ab��z�� value�Astr�𕶎���"Hello World."�Ƃ���
    // ����������R���X�g���N�^��p���� ex �𐶐�����
          ex = new SourceExample();
    //    ex.setA(3);
    //    ex.setB(value);
    //    ex.str("Hello World.");


        System.out.println("�I�u�W�F�N�g��a�t�B�[���h�̒l��"+ex.getA()+"�ł�");
        ex.showAllContentsOfB();
        System.out.println(ex.getStr());

    }

}