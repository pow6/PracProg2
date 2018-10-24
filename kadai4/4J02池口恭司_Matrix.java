//4J02 s15015 池口恭司
package pr2calc;
import java.util.Arrays;
import java.math.*;

//m[行][列]
public class Matrix {
    // 必要なフィールド（インスタンス）変数を宣言せよ
    double[][] m;
    int numOfRow;
    int numOfColumn;
    public Matrix() {
        // 行列の行数,列数を格納するインスタンス変数の値を0に初期化
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
        // 二次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：配列 inputの0行0列目の値を、行列の0行0列目とする)
        this.numOfColumn=input[0].length;
        this.numOfRow=input.length;
        this.m = new double[this.numOfRow][this.numOfColumn];   //javaの二次元配列はこんな感じでやんないとだめ
        for(int i=0;i<this.numOfRow;i++){
            for(int j=0;j<this.numOfColumn;j++){
                this.m[i][j]=input[i][j];
            }
        }
    }

    public Matrix(double[] input) {
        // 一次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：行数は1、列数はinputの要素数とする)
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
        // 指定した範囲が存在しない場合
        if (0<=rowIndex && rowIndex<this.numOfRow && 0<=columnIndex && columnIndex<this.numOfColumn) {
       }else{
            System.out.println("【エラー】指定する要素は存在しません.");
            System.out.printf("【エラー】numOfRow=%d numOfColumn=%d => rowIndex=%d columnIndex=%d\n",this.numOfRow,this.numOfColumn,rowIndex,columnIndex);
            System.exit(0);
       }
        // 指定された要素に対応する値を返す
        return this.m[rowIndex][columnIndex];
    }

    public void display() {
        // 行列内容の表示処理を実装せよ
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

    // ベクトルAとBの内積 A・Bの結果を返す
    public double getInnerProduct(Matrix B) {
        // Aが列ベクトルである場合、エラーメッセージを表示させて System.exit(0)
        // A, B 双方とも行ベクトル、かつ、要素数が等しければ内積を計算
        // Aが行ベクトル、Bが列ベクトルで、要素数が等しければ内積を計算
        // 内積計算が可能な条件を満たさない場合は、エラーメッセージを表示させてSystem.out.exit(0)
        // 計算結果を返す
        double sum=0;
        int elementsOfA,elementsOfB;
        elementsOfA=this.numOfRow*this.numOfColumn;
        elementsOfB=B.numOfRow*B.numOfColumn;
        if(this.numOfColumn==1 && this.numOfRow>1){
            System.out.println("【エラー】Aが列ベクトルなため、A・Bの内積を計算できませんでした");
            System.exit(0);
        }
        if(B.numOfColumn==1 && elementsOfA==elementsOfB){
        }else if(B.numOfRow==1 && elementsOfA==elementsOfB){
        }else{
            System.out.println("【エラー】AとBの次元数（要素数）が等しくなく、また計算可能な組み合わせではありませんでした");
            System.exit(0);
        }
        for(int j=0;j<B.numOfColumn;j++){
                sum+=this.showsComponentOf(0,j)*B.showsComponentOf(0,j);
        }
        return sum;
    }

    // 行列同士、もしくは行列とベクトルとの積を計算する
    public Matrix multiplyMatrix(Matrix target){
        Matrix result;
        result = new Matrix(this.numOfRow,target.numOfColumn);
		// 掛けられる行列の列数と掛ける行列の行数が等しいなら
		if(this.numOfColumn==target.numOfRow){
            // 積の計算処理を実装せよ
            for(int i=0;i<result.numOfRow;i++){
                for(int j=0;j<result.numOfColumn;j++){
                   for(int z=0;z<this.numOfColumn;z++){ 
                        result.m[i][j]+=this.showsComponentOf(i, z)*target.showsComponentOf(z, j);
                    }
                }
            }
        }else{
            System.out.println("要素数が計算できる組み合わせとなっていません");
            System.exit(0);
        }
        // 積の結果をMatrix型で返す
        return result;
    }

    public boolean multipliable(Matrix y){
        if(this.numOfColumn==y.numOfRow && this.numOfColumn>1);
        else{
            System.out.println("要素数が計算できる組み合わせとなっていません.");
            return false;
        }
        return true;
    }

    public Matrix transpose(){
        Matrix result;
        result = new Matrix(this.getNumOfColumn(),this.getNumOfRow());  //転置なので、行と列が逆
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
            System.out.println("【エラー】角度の指定が不適当です");
            System.exit(0);
        }else if(column>1){
            System.out.println("【エラー】与えられたベクトルが列ベクトルではありません");
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
        System.out.println("1.該当行列をディスプレイに表示");
        mat0.display();
        System.out.println("2.該当行列の転置行列をディスプレイに表示");
        mat0.transpose().display();
        System.out.println("3.（-3.0,3.0）を45°回転");
        System.out.println("回転前"); mat1.display();
        System.out.println("回転後"); mat1.rotate(45).display();
        System.out.println("4.(2.0,-3.464)を60°回転");
        System.out.println("回転前"); mat2.display();
        System.out.println("回転後"); mat2.rotate(60).display();;
        

    }

}