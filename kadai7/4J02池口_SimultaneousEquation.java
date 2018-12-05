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
        for(i=0;i<=numOfRow;i++){
            this.m[row][i]=this.m[row][i]/org;
        }
    }
    //対角成分が１となった行全体に適切な値を乗じた値を用いて、その他の行の対応する列の要素が０となるように差し引くメソッド
    //subtractRowFrom(差し引く行,差し引かれる行)
    public void subtractRowFrom(int rowFrom,int rowTo){
           int i;
           double scale;
           scale=this.m[rowTo][rowFrom]/this.m[rowFrom][rowFrom];
           for(i=0;i<=numOfRow;i++){
                this.m[rowTo][i]-=this.m[rowFrom][i]*scale;
           }
    }
    //掃き出し法を実行するメソッド
    public void solveByGaussJordan(){
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
            System.out.printf("x%d = %.2f, ",i+1,this.answers[i]);
        }
        System.out.printf("x%d = %.2f. \n",i,this.answers[i]);
    }

    //ガウスの消去法を実現するメソッド
    public void solveByGauss(){
        int i,j,counter;
       //前進消去
        for(i=0;i<this.numOfColumn-2;i++){
            for(j=i+1;j<this.numOfRow;j++){   //すでに計算した行列で引かないようにj=iから始める
                if(j==this.numOfRow)break;
                this.subtractRowFrom(i, j);
            }
            this.display();
            System.out.println();
        }
        //後退代入
        counter=0;
        for(i=this.numOfRow-1;i>=0;i--){
            answers[i]=this.m[i][this.numOfColumn-1];
            for(j=0;j<counter;j++){
                if(counter==0)break;
                answers[i]-=this.m[i][this.numOfColumn-j-2]*answers[this.numOfColumn-j-2];
            }
            answers[i]=answers[i]/this.m[i][i];
            counter++;
        }

    }
    //部分選択法
    public void solveByGaussWithPartialSelection(){
        int i,j,counter;
       //前進消去
        for(i=0;i<this.numOfColumn-2;i++){
            this.exchangeRows(i, this.selectPivotFromRow(i));
            for(j=i+1;j<this.numOfRow;j++){   //すでに計算した行列で引かないようにj=iから始める
                if(j==this.numOfRow)break;
                this.subtractRowFrom(i, j);
            }
            this.display();
            System.out.println();
        }
        //後退代入
        counter=0;
        for(i=this.numOfRow-1;i>=0;i--){
            answers[i]=this.m[i][this.numOfColumn-1];
            for(j=0;j<counter;j++){
                if(counter==0)break;
                answers[i]-=this.m[i][this.numOfColumn-j-2]*answers[this.numOfColumn-j-2];
            }
            answers[i]=answers[i]/this.m[i][i];
            counter++;
        }
    }

    protected void exchangeRows(int row1, int row2) {
        double tmp;
        for(int i=0;i<this.numOfColumn;i++){
            tmp=this.m[row1][i];
            this.m[row1][i]=this.m[row2][i];
            this.m[row2][i]=tmp;
        }
    }

    protected int selectPivotFromRow(int targetColmn) {
        int top=targetColmn;
        for(int i=targetColmn+1;i<this.numOfRow;i++){
            if(Math.abs(this.m[top][targetColmn])<Math.abs(this.m[i][targetColmn])){
                top=i;
            }
        }
        return top;
    } 
    protected int selectPivotFromRow(int targetColmn,int start) {
        int top=start;
        for(int i=start+1;i<this.numOfRow;i++){
            if(Math.abs(this.m[top][targetColmn])<Math.abs(this.m[i][targetColmn])){
                top=i;
            }
        }
        return top;
    }
    //完全選択法
    public void solveByGaussWithCompleteSelection(){
        int i,j,counter;
        int z,y,topCol,topRow;
        int[] answersOrder = new int[this.numOfRow];
        int tmp;
        double[] tmpArray = new double[this.numOfRow];
        for(i=0;i<this.numOfRow;i++){
            answersOrder[i]=i;
        }
       //前進消去
        for(i=0;i<this.numOfColumn-2;i++){  //行列の最後は方程式の和、また、その一つ前は計算する必要がないため-2
            topCol=i;
            topRow=i;
            for(y=i+1;y<this.numOfColumn-1;y++){
                if(Math.abs(this.m[topRow][topCol])<Math.abs(this.m[selectPivotFromRow(y,i)][y])){
                    topCol=y;
                    topRow=selectPivotFromRow(y,i);
                }
            }
            //ここから【列入れ替えによる、解の順番の変更の保存】
            exchangeRowsAndColumns(i, i, topRow, topCol);
            tmp=answersOrder[i];
            answersOrder[i]=answersOrder[topCol];
            answersOrder[topCol]=tmp;
            //ここまで
           for(j=i+1;j<this.numOfRow;j++){   //すでに計算した行列で引かないようにj=iから始める
                if(j==this.numOfRow)break;
                this.subtractRowFrom(i, j);
            }
            this.display();
            System.out.println();
        }
        //後退代入
        counter=0;
        for(i=this.numOfRow-1;i>=0;i--){
            answers[i]=this.m[i][this.numOfColumn-1];
            for(j=0;j<counter;j++){
                if(counter==0)break;
                answers[i]-=this.m[i][this.numOfColumn-j-2]*answers[this.numOfColumn-j-2];
            }
            answers[i]=answers[i]/this.m[i][i];
            counter++;
        }
        //ここから【解の順番を正す】
        for(i=0;i<this.numOfRow;i++){
            tmpArray[answersOrder[i]]=answers[i];
        }
        for(i=0;i<this.numOfRow;i++){
            answers[i]=tmpArray[i];
        }
        //ここまで

    }

    protected void exchangeRowsAndColumns(int row1, int column1, int row2, int column2){
        double tmp;
        this.exchangeRows(row1, row2);
        for(int i=0;i<this.numOfRow;i++){
            tmp=this.m[i][column1];
            this.m[i][column1]=this.m[i][column2];
            this.m[i][column2]=tmp;
        }
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
        mat.display(); System.out.println();
        mat.solveByGaussWithCompleteSelection();
        mat.displayAns();
    }
}


/***** 実行結果 ******/
/** 完全選択法 ***
[2.00 1.00 3.00 4.00 2.00]
[3.00 2.00 5.00 2.00 12.00]
[3.00 4.00 1.00 -1.00 3.00]
[-1.00 -3.00 1.00 3.00 -1.00]

[5.00 2.00 3.00 2.00 12.00]
[0.00 -0.20 0.20 2.80 -5.20]
[0.00 3.60 2.40 -1.40 0.60]
[0.00 -3.40 -1.60 2.60 -3.40]

[5.00 2.00 3.00 2.00 12.00]
[0.00 2.80 0.20 -0.20 -5.20]
[0.00 0.00 2.50 3.50 -2.00]
[0.00 0.00 -1.79 -3.21 1.43]

[5.00 2.00 2.00 3.00 12.00]
[0.00 2.80 -0.20 0.20 -5.20]
[0.00 0.00 3.50 2.50 -2.00]
[0.00 0.00 0.00 0.51 -0.41]

Answer:
x1 = -0.80, x2 = 0.00, x3 = 3.60, x3 = -1.80.
*/
