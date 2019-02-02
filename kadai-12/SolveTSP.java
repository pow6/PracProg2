import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class SolveTSP{
    public int[][] costs;  //�e�s�s�Ԃ̈ړ��R�X�g
    public int size;


    public SolveTSP(){
        this.readFile("./table.dat");
        this.size = this.getCostsSize();
    }

    //�t�@�C����ǂݍ���
    public void readFile(String aFilePath){
        int i,j;
        try{
            List<String> mLines = Files.readAllLines(Paths.get(aFilePath), StandardCharsets.UTF_8);
            //����ς݂�ۑ�����t���O���c�����߂ɔz��̗v�f�𑝂₷
            this.costs = new int[mLines.size()][mLines.size()+1];
            for(j=0;j<this.costs.length;j++){
                String[] tmp = mLines.get(j).split("",(int)Math.sqrt(mLines.size()));
                for(i=0;i<this.costs[j].length;i++){
                    this.costs[j][i] = Integer.parseInt(tmp[i]);
                }
                this.costs[j][i] = -1;
            }
        }catch(IOException e){
            System.out.println("�G���[");
        }
    }

    //�s�s�̐���Ԃ�
    public int getCostsSize(){
        return costs[0].length;
    }

    //���݂̓s�s�f�[�^�̔z���\������
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