package pr2calc;
import java.util.Arrays;

public class Matrix {
        // 必要なフィールド（インスタンス）変数を宣言せよ
    double m[][];
    int numOfRow;
    int numOfColumn;
    public Matrix() {
        // 行列の行数,列数を格納するインスタンス変数の値を0に初期化
        numOfRow=0;
        numOfColumn=0;
    }

    public Matrix(double[][] input) {
        // 二次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：配列 inputの0行0列目の値を、行列の0行0列目とする)
        numOfColumn=input.length;
        numOfRow=input[0].length;
        for(int i=0;i<numOfColumn;i++){
            for(int j=0;j<numOfRow;j++){
                m[i][j]=input[i][j];
            }
        }
    }

    public Matrix(double[] input) {
        // 一次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：行数は1、列数はinputの要素数とする)
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
        // 指定した範囲が存在しない場合
        if (0<rowIndex && rowIndex<this.numOfRow && 0<columnIndex && columnIndex<this.numOfColumn) {
            System.out.println("指定する要素は存在しません.");
            System.exit(0);
        }
        // 指定された要素に対応する値を返す
        return this.m[columnIndex][rowIndex];
    }

    public void display() {
        // 行列内容の表示処理を実装せよ
        for(int i=0;i<this.numOfColumn;i++){
            for(int j=0;j<this.numOfRow;j++){
                System.out.print(this.m[i][j]+" ");
            }
            System.out.println();
        }
    }

    // ベクトルAとBの内積 A・Bの結果を返す
    public double getInnerProduct(Matrix B) {
        // Aが列ベクトルである場合、エラーメッセージを表示させて System.exit(0)
        // A, B 双方とも行ベクトル、かつ、要素数が等しければ内積を計算
        // Aが行ベクトル、Bが列ベクトルで、要素数が等しければ内積を計算
        // 内積計算が可能な条件を満たさない場合は、エラーメッセージを表示させてSystem.out.exit(0)
        // 計算結果を返す
        int sum=0;
        int elementsOfA,elementsOfB;
        elementsOfA=this.numOfRow*B.numOfColumn;
        elementsOfB=this.numOfRow*B.numOfColumn;
        if(this.numOfRow==1){
            System.out.println("Aが列ベクトルなため、A・Bの内積を計算できませんでした");
            System.exit(0);
        }
        if(B.numOfColumn==1 && elementsOfA==elementsOfB){
            B=this.multiplyMatrix(B);
        }else if(B.numOfRow==1 && elementsOfA==elementsOfB){
            B=this.multiplyMatrix(B);
        }else{
            System.out.println("内積計算が可能な条件を満たしていません");
            System.exit(0);
        }
        for(int i=0;i<B.numOfColumn;i++){
            for(int j=0;j<B.numOfRow;j++){
                sum+=B.showsComponentOf(j,i);
            }
        }
        return sum;
    }

    // 行列同士、もしくは行列とベクトルとの積を計算する
    public Matrix multiplyMatrix(Matrix target){
        Matrix result;
        result = new Matrix();
		// 掛けられる行列の列数と掛ける行列の行数が等しいなら
		if(this.numOfRow==target.numOfColumn){
            // 積の計算処理を実装せよ
            result.numOfRow=this.numOfRow;
            result.numOfColumn=target.numOfColumn;
            for(int i=0;i<result.numOfColumn;i++){
                for(int j=0;j<result.numOfRow;j++){
                    result.m[i][j]+=this.showsComponentOf(j, i)*target.showsComponentOf(i, j);
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
        if(this.numOfRow==y.numOfColumn);
        else if(this.numOfRow>1);
        else if(this.numOfColumn*this.numOfRow==y.numOfColumn*y.numOfRow);
        else{
            System.out.println("要素数が計算できる組み合わせとなっていません.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        /*
         * main メソッド中で今回作成した内積計算メソッドや行列同士、ベクトルと行列、 行列とベクトルの積を計算するメソッドが正常に動いているかを確認せよ。
         */

        // 行列・ベクトル定義、および演算処理の一例 （あくまで一例です） 課題の要求を満たすよう、各自で加筆・修正してください

        Matrix mat0, mat1, mat2;

        double[] v0 = { 2.0, -3.0, 7.0 };
        double[][] m0 = { { 1.0, 2.0, 3.0 }, { 3.0, 2.0, -1.0 }, { 4.0, 2.0, 6.0 } },

                m1 = { { 8.0, 2.0 }, { -3.0, 2.0 }, { 1.0, 6.0 } },

                v1 = { { 3.0 }, { -2.0 } },

                d;

        mat0 = new Matrix(v0);
        mat1 = new Matrix(m1);
        mat2 = new Matrix(v1);
        // 以下は、行列・ベクトル演算の実行＆結果表示の一例．不要であれば削除し，課題の条件を満たす記述を新たに追加すること

        System.out.println("v0 = ");
        mat0.display();
        System.out.println("m1 = ");
        mat1.display();
        System.out.println("v1 = ");
        mat2.display();

        System.out.println("例題0a");
        System.out.println("v0 * m1 = ");
        if (mat0.multipliable(mat1) == true)
            (mat0.multiplyMatrix(mat1)).display();

        System.out.println("例題0b");
        System.out.println("v0 * v1 = ");
        if (mat0.multipliable(mat2) == true)
            (mat0.multiplyMatrix(mat2)).display();
    }

}