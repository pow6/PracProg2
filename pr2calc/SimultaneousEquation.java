//4J02 s15015 池口恭司
package pr2calc;
import java.util.Arrays;

public class SimultaneousEquation extends Matrix{
    //インスタンス(thisで使う)
    double[] answers;


    //Matrixクラスから継承（superで使う）
    public SimultaneousEquation(double[][] input){
        super(input);    //Matrixのコンストラクタ呼び出し。このとき、[配列]+[解]の配列とする。
        //行列計算の結果を保存する配列resultを１で埋める。
        this.answers=new double[this.numOfRow];
        Arrays.fill(this.answers,1);
    }

    //行を指定し、特定の値を乗じて対角成分を１にする
    public void normalize(int row){
        int i;
        double org;
        org=this.m[row][row];  //１にするまえの対角成分の値を保存
        answers[row]/=answers[row]/org;
        for(i=0;i<=row;i++){
            this.m[row][i]=this.m[row][i]/org;
        }
    }
    //対角成分が１となった行全体に適切な値を乗じた値を用いて、その他の行の対応する列の要素が０となるように差し引くメソッド
    //subtractRowFrom(差し引く行,差し引かれる行)
    public void subtractRowFrom(int rowFrom,int rowTo){
           int i;
           double scale;
           scale=this.m[rowTo][rowFrom]/this.m[rowFrom][rowFrom];
           answers[rowTo]=answers[rowTo]-answers[rowFrom]*scale;
           for(i=0;i<=rowFrom;i++){
                this.m[rowTo][i]-=this.m[rowFrom][i]*scale;
           }
    }
    //掃き出し法を実行する関数
    public void solveByGaussJordan(){
        this.display();
        System.out.println();
       int i;
        for(i=0;i<this.numOfRow;i++){
            this.normalize(i);
            System.out.printf("%d行%d列目が1となるように割り、他の行の%d列目が0となるように引く\n",i,i,i);
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

    //メイン関数
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


//実行結果（５?２の例題のような計算過程の出力を行い、ここに実行結果を貼り付ける）
/*
[2.0 1.0 3.0 4.0 2.0]
[3.0 2.0 5.0 2.0 12.0]
[3.0 4.0 1.0 -1.0 3.0]
[-1.0 -3.0 1.0 3.0 -1.0]

0行0列目が1となるように割り、他の行の0列目が0となるように引く
[1.0 1.0 3.0 4.0 2.0]
[0.0 2.0 5.0 2.0 12.0]
[0.0 4.0 1.0 -1.0 3.0]
[0.0 -3.0 1.0 3.0 -1.0]

1行1列目が1となるように割り、他の行の1列目が0となるように引く
[1.0 0.0 3.0 4.0 2.0]
[0.0 1.0 5.0 2.0 12.0]
[0.0 0.0 1.0 -1.0 3.0]
[0.0 0.0 1.0 3.0 -1.0]

2行2列目が1となるように割り、他の行の2列目が0となるように引く
[1.0 0.0 0.0 4.0 2.0]
[0.0 1.0 0.0 2.0 12.0]
[0.0 0.0 1.0 -1.0 3.0]
[0.0 0.0 0.0 3.0 -1.0]

3行3列目が1となるように割り、他の行の3列目が0となるように引く
[1.0 0.0 0.0 0.0 2.0]
[0.0 1.0 0.0 0.0 12.0]
[0.0 0.0 1.0 0.0 3.0]
[0.0 0.0 0.0 1.0 -1.0]

Answer:
x0 = -15.0, x1 = -9.0, x2 = 4.0, x3 = 3.0.
*/