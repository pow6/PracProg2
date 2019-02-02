import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class SolveTSP{
    public int[][] costs;  //各都市間の移動コスト
    public int size;


    public SolveTSP(){
        this.readFile("./table.dat");
        this.size = this.getCostsSize();
    }

    //ファイルを読み込む
    public void readFile(String aFilePath){
        int i,j;
        try{
            List<String> mLines = Files.readAllLines(Paths.get(aFilePath), StandardCharsets.UTF_8);
            //巡回済みを保存するフラグを残すために配列の要素を増やす
            this.costs = new int[mLines.size()][mLines.size()+1];
            for(j=0;j<this.costs.length;j++){
                String[] tmp = mLines.get(j).split("",(int)Math.sqrt(mLines.size()));
                for(i=0;i<this.costs[j].length;i++){
                    this.costs[j][i] = Integer.parseInt(tmp[i]);
                }
                this.costs[j][i] = -1;
            }
        }catch(IOException e){
            System.out.println("エラー");
        }
    }

    //都市の数を返す
    public int getCostsSize(){
        return costs[0].length;
    }

    //現在の都市データの配列を表示する
    public void printDatas(){
        for(int[] mArray : this.costs){
            for(int mData : mArray){
                System.out.printf("%d ",mData);
            }
            System.out.println();
        }
    }

    public static void main(){
        SolveTSP tsp = new SolveTSP();
        tsp.readFile("./table.dat");
        tsp.printDatas();
    }
}