//4J02 s15015 �r�����i
package pr2calc;
import java.io.*;
public class SourceExample{  

    public int a;            // ����3�ϐ��E�E�E�t�B�[���h�ϐ��i���\�b�h�̊O�ɂ���j�B
    public int[][] b;        // �N���X���́Amain()���\�b�h�������S���\�b�h�ŗ��p�\
    public String str;       

    /* �ȉ��̃��\�b�h�i�֐��j�̓R���X�g���N�^�B�I�u�W�F�N�g�̐����i& �������j���s�� 
    * �����̐ݒ�͎��R�B�������قȂ�΁A�����p�ӂ��Ă��ǂ�
    */
    public SourceExample(){   // �e�ϐ��̏����������s�i��Ȃ̂ŁA���g�͓K���ł��j
        int h,i;    // ���\�b�h���Ő錾�����ϐ��́A���\�b�h���݂̂ŗ��p��

        this.a = -1;    // �t�B�[���h�ϐ��Ƃ��Ē�`���ꂽ�ϐ��́A�N���X���ǂ��ł����p�ithis.a �E�E�E "����(= this)�I�u�W�F�N�g(�C���X�^���X)���g�̎��ϐ�a"�j
        this.b = new int[2][2];
    /* java �ł́A�z��ƕ�����̓I�u�W�F�N�g�Ƃ��ėp�ӂ���Ă��邽�߁A�V�����z��A
    * �������錾���鎞�ɂ� "new" ���g��(��̗�ł́Ab ��2�~2�̐����^�񎟌��z��
    * �Ƃ��Đ錾���Ă���j
    */

        for(h=0;h<this.b.length;h++){		// �񎟌��z��̒l�� -1 �ŏ�����
            for(i=0;i<this.b[0].length;i++)		// �z��Ɋւ��ẮA"new" ��p������
                this.b[h][i] = -1;			// ���������邱�Ƃ��\�i�ڍׂ�main
        }						// ���\�b�h�����Q�Ɓj

    // this.b.length : �z��̑��v�f�̗v�f���Athis.b[0].length : �z��̑��v�f�̗v�f��

        this.str = "";	// ������^�̓I�u�W�F�N�g�Ƃ��ėp�ӂ����ƋL�q�������A���ɂ悭
    }			// �p������^�ł��邽�߁A���̂悤�ȋL�@���F�߂��Ă���
                // �i new String(""); �Ƃ����������ł����ROK �j


    // �t�B�[���h�ϐ�a, b, str�̒l������ŏ������i�ݒ�j����R���X�g���N�^��������L�q����
    public SourceExample(int a,int[][] b,String str){
        this.a = a;
        this.b = b;
        this.str = str;
    }

    //�ۑ�Q�@�t�@�C������̃f�[�^���͂ŕϐ�������������R���X�g���N�^
    public SourceExample(String fileName){
        try{
            loadData(fileName);
        }catch(IOException e){
            System.out.println("�t�@�C������̓��͂Ɏ��s���܂���");
            System.exit(0);
        }
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
        //System.out.println(Arrays.deepToString(this.b));
        for(int i=0;i<this.b.length;i++){
            for(int j=0;j<this.b[i].length;j++){
                System.out.print(b[i][j]);
                if(j!=this.b[i].length-1){
                    System.out.print(" , ");
                }
            }
            System.out.println();
    
        }
    }
    private boolean loadData(String fileName) throws IOException{
    int h,i;
    int row, column;
    BufferedReader fin = new BufferedReader(new FileReader(fileName));
    String inputData;
    String[] inputValue;

    // inputData �ɁA�t�@�C�����當�������s���ǂݍ���
    // ����ꂽ������f�[�^���A�X�y�[�X(= "\\s") �ŋ�؂�A�z�� inputValue �֊i�[
    inputData=fin.readLine();
    inputValue=inputData.split("\\s");
    if(inputValue.length != 1)
        return false;
    else{
        // �t�B�[���h�i�C���X�^���X�j�ϐ� a �ɁAinputValue�̍ŏ��i0�Ԗځj�̗v�f����
        // ������s�ǂݍ��݁A�X�y�[�X��؂�� inputValue �փf�[�^(���ɓǂݍ��ލs��̍s�����񐔁j���i�[
        this.a=Integer.parseInt(inputValue[0]);
        inputData=fin.readLine();
        inputValue=inputData.split("\\s");
        if(inputValue.length != 2)	// �s�����񐔂̑o�����i�[����Ă��Ȃ����
            return false;
        else{
            // �ϐ� row �ɍs���̃f�[�^�Acolumn �ɗ񐔂̃f�[�^����
            row=Integer.parseInt(inputValue[0]);
            column=Integer.parseInt(inputValue[1]);
            this.b = new int[row][column];
            // �z�� b ��h�si��ڂ̗v�f�ɁA�ǂ݂񂾃t�@�C����h�s�ځA(������ji�Ԗڂ̃f�[�^���i�[
            // �Ō�Ɉ�s�ǂݍ��݁A�X�y�[�X��؂�� inputValue �փf�[�^(������j���i�[
            // �t�B�[���h�i�C���X�^���X�j�ϐ� str �ɁA�i�[�������������
            for(i=0;i<column;i++){
                inputData=fin.readLine();
                inputValue=inputData.split("\\s");
                for(h=0;h<row;h++){
                    b[i][h]=Integer.parseInt(inputValue[h]);
                }
            }
            inputData=fin.readLine();
            inputValue=inputData.split("\\s");
            str=String.join(" ", inputValue);
        }
    }
    fin.close(); 
    return true;
	}

    /* ���̃v���O���������s����ƁA�ȉ��� main ���\�b�h�ɋL�q���ꂽ���삪���s����� */
public static void main(String[] args) throws Exception{
        SourceExample    ex;    // SourceExample �N���X�̃I�u�W�F�N�g ex ��錾
        if(args.length!=1){
            System.out.println("�������w�肳��܂���ł���");
            return false;
        }
        if(args[0]!="1"){
            System.out.println("�������s�K���ł��B�f�[�^���͗p�̃t�@�C�������Ďw�肵�Ă��������B");
            String fn = null;
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(System.in));
            fn = in.readLine();
            ex = new SourceExample(fn);
        }else{
            ex = new SourceExample(args[0]);
        }
        
        System.out.println("�I�u�W�F�N�g��a�t�B�[���h�̒l��"+ex.getA()+"�ł�");
        System.out.println();
        ex.showAllContentsOfB();
        System.out.println();
        System.out.println(ex.getStr());
    }
}

