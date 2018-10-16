package pr2calc;
import java.io.*;
public class SourceExample{  

    public int a;            // 左の3変数・・・フィールド変数（メソッドの外にある）。
    public int[][] b;        // クラス内の、main()メソッドを除く全メソッドで利用可能
    public String str;       

    /* 以下のメソッド（関数）はコンストラクタ。オブジェクトの生成（& 初期化）を行う 
    * 引数の設定は自由。引数が異なれば、複数用意しても良い
    */
    public SourceExample(){   // 各変数の初期化を実行（例なので、中身は適当です）
        int h,i;    // メソッド内で宣言される変数は、メソッド内のみで利用可

        this.a = -1;    // フィールド変数として定義された変数は、クラス内どこでも利用可（this.a ・・・ "この(= this)オブジェクト(インスタンス)自身の持つ変数a"）
        this.b = new int[2][2];
    /* java では、配列と文字列はオブジェクトとして用意されているため、新しい配列、
    * 文字列を宣言する時には "new" を使う(上の例では、b を2×2の整数型二次元配列
    * として宣言している）
    */

        for(h=0;h<this.b.length;h++){		// 二次元配列の値を -1 で初期化
            for(i=0;i<this.b[0].length;i++)		// 配列に関しては、"new" を用いずに
                this.b[h][i] = -1;			// 初期化することも可能（詳細はmain
        }						// メソッド内を参照）

    // this.b.length : 配列の第一要素の要素数、this.b[0].length : 配列の第二要素の要素数

        this.str = "";	// 文字列型はオブジェクトとして用意されると記述したが、非常によく
    }			// 用いられる型であるため、左のような記法も認められている
                // （ new String(""); という書き方でも当然OK ）


    // フィールド変数a, b, strの値を内部で初期化（設定）するコンストラクタをもう一つ記述する
    public SourceExample(int a,int[][] b,String str){
        this.a = a;
        this.b = b;
        this.str = str;
    }

    //課題２　ファイルからのデータ入力で変数を初期化するコンストラクタ
    public SourceExample(String fileName){
        try{
            loadData(fileName);
        }catch(IOException e){System.out.println("ファイルからの入力に失敗しました");}
    }

    /* 以降、mainメソッド以外のメソッドは、コンストラクタで生成されたオブジェクト
    * を介して実行される
    */

    public void setA(int value){    // メソッドを通して、フィールド変数の値を設定する
        this.a = value;		// （setterと呼ばれる）
    }

    public int getA(){		// メソッドを通して、フィールド変数の値を返す
        return this.a;		// （getterと呼ばれる）
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
    // 各自で必要な内容を記述すること
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

    // inputData に、ファイルから文字列を一行分読み込む
    // 得られた文字列データを、スペース(= "\\s") で区切り、配列 inputValue へ格納
    inputData=fin.readLine();
    if(inputData==NULL)break;
    inputValue=data.split("\\s");
    if(inputValue.length != 1)
        return false;
    else{
        // フィールド（インスタンス）変数 a に、inputValueの最初（0番目）の要素を代入
        // もう一行読み込み、スペース区切りで inputValue へデータ(次に読み込む行列の行数＆列数）を格納
        this.a=inputValue[0];
        inputData=fin.readLine();
        if(data==NULL)break;
        inputValue=data.split("\\s");
        if(inputValue.length != 2)	// 行数＆列数の双方が格納されていなければ
            return false;
        else{
            // 変数 row に行数のデータ、column に列数のデータを代入
            row=3;
            column=4;
            this.b = new int[row][column];
            // 配列 b のh行i列目の要素に、読みんだファイルのh行目、(左から）i番目のデータを格納
            // 最後に一行読み込み、スペース区切りで inputValue へデータ(文字列）を格納
            // フィールド（インスタンス）変数 str に、格納した文字列を代入
            b[h][i]=inputValue[(int)(i*4+h)*1.5];
            inputValue=fin.readLine();
            str=inputValue;
        }
    }
    fin.close(); 
    return true;
	}

    /* このプログラムを実行すると、以下の main メソッドに記述された動作が実行される */
public static void main(String[] args){
        SourceExample    ex;    // SourceExample クラスのオブジェクト ex を宣言
        int value[][] = {{1,2,3},{4,5,6},{7,8,9}}; // "new"を用いない、二次元配列を初期化する書き方

    //    ex = new SourceExample();    // SourceExample クラスのオブジェクト ex を生成
    //    ex.setA(10);    // ex オブジェクトのフィールド this.a の値を10に変更；

    // 上の二行をコメントアウトした上で、aを3、bを配列 value、strを文字列"Hello World."として,初期化するコンストラクタを用いて ex を生成せよ
        ex = new SourceExample("sample.dat");
        //ex = new SourceExample();
        System.out.println("オブジェクトのaフィールドの値は"+ex.getA()+"です");
        System.out.println();
        ex.showAllContentsOfB();
        System.out.println();
        System.out.println(ex.getStr());
    }
}

