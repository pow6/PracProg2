package pr2calc;

public class Matrix {
        // 必要なフィールド（インスタンス）変数を宣言せよ
	
	public Matrix(){
		// 行列の行数,列数を格納するインスタンス変数の値を0に初期化
	}

	public Matrix(double[][] input){
		// 二次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：配列 inputの0行0列目の値を、行列の0行0列目とする)
        }

	public Matrix(double[] input){
		// 一次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：行数は1、列数はinputの要素数とする)
	}

	public int getNumOfRow(){
		return 行数;
        }
        
	public int getNumOfColumn(){
		return 列数;
        }

	public double showsComponentOf(int rowIndex, int columnIndex){
		// 指定した範囲が存在しない場合
                if(条件を指定せよ){
                        System.out.println("指定する要素は存在しません.");
                        System.exit(0);
                }
                // 指定された要素に対応する値を返す
        }
        
	public void display(/* 引数は任意 */){
		// 行列内容の表示処理を実装せよ
	}

	// ベクトルAとBの内積 A・Bの結果を返す
	public double getInnerProduct(/* 引数は任意 */){
		// Aが列ベクトルである場合、エラーメッセージを表示させて System.exit(0)
		// A, B 双方とも行ベクトル、かつ、要素数が等しければ内積を計算
		// Aが行ベクトル、Bが列ベクトルで、要素数が等しければ内積を計算
		// 内積計算が可能な条件を満たさない場合は、エラーメッセージを表示させてSystem.out.exit(0)
		// 計算結果を返す
	}

        // 行列同士、もしくは行列とベクトルとの積を計算する
        public Matrix multiplyMatrix(Matrix target){

		// 掛けられる行列の列数と掛ける行列の行数が等しいなら
		if(/* 条件を指定せよ */){
			// 積の計算処理を実装せよ
                }
                else{
                        System.out.println("要素数が計算できる組み合わせとなっていません");
                        System.exit(0);
                }
                // 積の結果をMatrix型で返す
        }


	public static void main(String[] args) {
/*
 * main メソッド中で今回作成した内積計算メソッドや行列同士、ベクトルと行列、
 * 行列とベクトルの積を計算するメソッドが正常に動いているかを確認せよ。
 */

// 行列・ベクトル定義、および演算処理の一例 （あくまで一例です）　課題の要求を満たすよう、各自で加筆・修正してください

		Matrix mat0,mat1,mat2;

		double[]
			v0 = {2.0, -3.0, 7.0};
		double[][] 
			m0 = {
				{1.0, 2.0,  3.0},
				{3.0, 2.0, -1.0},
				{4.0, 2.0,  6.0}},

			m1 = {
				{ 8.0, 2.0},
				{-3.0, 2.0},
				{ 1.0, 6.0}},

			v1 = {
				{ 3.0},
				{-2.0}},

			d;
                
                
                mat0 = new Matrix(v0);	mat1 = new Matrix(m1);	mat2 = new Matrix(v1);
// 以下は、行列・ベクトル演算の実行＆結果表示の一例．不要であれば削除し，課題の条件を満たす記述を新たに追加すること

		System.out.println("v0 = ");	mat0.display();
		System.out.println("m1 = ");	mat1.display();
		System.out.println("v1 = ");	mat2.display();

		System.out.println("例題0a");
		System.out.println("v0 * m1 = ");
		if(mat0.multipliable(mat1) == true)
			(mat0.multiplyMatrix(mat1)).display();

		System.out.println("例題0b");
		System.out.println("v0 * v1 = ");
		if(mat0.multipliable(mat2) == true)
			(mat0.multiplyMatrix(mat1)).display();
        }

}