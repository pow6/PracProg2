import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class SolveTSP{
    public int[][] costs;  //各都市間の移動コスト
    public int size;       //総都市数
    public int now;        //現在の都市番号
    public int result;     //最短距離
    public int resultPos;  //最短時の初期位置

    public SolveTSP(){
        this.readFile("./table.dat");
        this.size = this.getCostsSize();
        this.result = Integer.MAX_VALUE;
    }

    //ファイルを読み込む
    public void readFile(String aFilePath){
        int i,j;
        try{
            List<String> mLines = Files.readAllLines(Paths.get(aFilePath), StandardCharsets.UTF_8);
            //巡回済みを保存するフラグを残すために配列の要素を増やす
            this.costs = new int[mLines.size()][mLines.size()+1];
            for(j=0;j<this.costs.length;j++){
                String[] tmp = mLines.get(j).split(" ");
                for(i=0;i<tmp.length;i++){
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
        return costs.length;
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

    //巡回済みフラグを下ろす（-1にする）
    public void clearFlags(){
        for(int i=0;i<this.size;i++){
            this.costs[i][this.size] = -1;
        }
    }

    //指定都市のフラグを立てる
    public void raiseFlag(int aPlace){
        //System.out.println("aPlace=>"+aPlace);
        this.costs[aPlace][this.size] = 1;
    }

    //指定都市が巡回可能か調べる
    public boolean checkMovePossible(int aNextTown){
        if(this.costs[aNextTown][this.size]==-1 && this.costs[this.now][aNextTown]!=-1){
            return true;
        }else{
            return false;
        }
    }

    //最短距離の経路を求める
    public int getNearestDistance(){
        int mNearest=Integer.MAX_VALUE;
        int i=0,mTown=0;
        for(int[] mNow:this.costs){
            //距離が短く，未巡回の場合
            if(mNearest > mNow[this.now] && this.checkMovePossible(i)){
                mNearest = mNow[this.now];
                mTown = i;                
            }
            i++;
        }
        this.now = mTown;
        this.raiseFlag(this.now);
        //System.out.println("mNearest=>"+mNearest);
        return mNearest;
    }

    //ある地点からの最短距離を求める(貪欲法を用いる)
    public int solveByGreedy(int aFirstPlace){
        int mAllDistance=0;
        this.raiseFlag(aFirstPlace);
        this.now = aFirstPlace;
        for(int i=0;i<this.size-1;i++){
            mAllDistance += this.getNearestDistance();
        }
        return mAllDistance;
    }

    public void exportCSV(int[] aDatas, String aPath){
        try{
            int i=0;
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(aPath)));
            pw.println("初期都市,移動距離");
            
            for(int data:aDatas){
                pw.println(i+","+data);
                i++;
            }
            pw.printf("\n%d,%d",this.result,this.resultPos);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        SolveTSP tsp = new SolveTSP();
        int[] distances = new int[tsp.size];
        for(int i=0;i<tsp.size;i++){
            System.out.printf("[%5d]/%d\r",i+1,tsp.size);      //計算実行画面を表示       
            distances[i] = tsp.solveByGreedy(i);
            tsp.clearFlags();
            if(tsp.result > distances[i]){
                tsp.result = distances[i];
                tsp.resultPos = i;
            }
        }
        System.out.printf("最短距離=>%d (初期位置：%d)",tsp.result,tsp.resultPos);
        tsp.exportCSV(distances, "./result.csv");
    }
}