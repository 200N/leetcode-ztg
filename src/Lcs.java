import java.util.LongSummaryStatistics;

public class Lcs {
        public void LcsLength(char[] x,char[] y,int[][] Rec){
            //初始化

            int [][]c=new int[x.length+1][y.length+1];
            for(int i=0;i<=x.length;i++){
                c[i][0]=0;
                Rec[i][0]=0;
            }
            for(int j=0;j<=y.length;j++){
                c[0][j]=0;
                Rec[0][j]=0;
            }
            //动态规划
            for(int i=1;i<=x.length;i++){
                for(int j=1;j<=y.length;j++){
//                    核心代码
//                    if(x[i-1]==y[j-1]){
//                        c[i][j] = c[i-1][j-1]+1;
//                    }else if(c[i-1][j] >= c[i][j-1]) {
//                        c[i][j] = c[i-1][j];
//                    }else {
//                        c[i][j] = c[i][j-1];
//                    }

                    if(x[i-1]==y[j-1]){
                        c[i][j]=c[i-1][j-1]+1;
                        //当Rec为1时，表示Xi和Yi的最长公共子序列是由Xi-1和Yi-1的最长公共子序列在尾部加上Xi所得的子序列。
                        Rec[i][j]=1;
                    }else if(c[i-1][j]>=c[i][j-1]){
                        c[i][j]=c[i-1][j];
                        //当Rec为2时，表示Xi和Yi的最长公共子序列与Xi-1和Yi的最长公共子序列相同。
                        Rec[i][j]=2;
                    }else{
                        c[i][j]=c[i][j-1];
                        //当Rec为3时，表示Xi和Yi的最长公共子序列与Xi和Yi-1的最长公共子序列相同。
                        Rec[i][j]=3;
                    }
                }
            }

            System.out.println("动态规划表格为：");
            for(int i=0;i<=x.length;i++){
                for(int j=0;j<=y.length;j++){
                    System.out.print(c[i][j]+" ");
                }
                System.out.print("\n");
            }
            System.out.println("bdbd");
            System.out.println("记录子问题解的来源的数组为：");
            for(int i=0;i<=x.length;i++){
                for(int j=0;j<=y.length;j++){
                    System.out.print(Rec[i][j]+" ");
                }
                System.out.print("\n");
            }
            System.out.println("最长公共子序列为：");
            lcs(x.length,y.length,x,Rec);
        }

        //递归回溯
        public void lcs(int i,int j,char[] x,int[][] Rec){
            if(i==0||j==0) return ;
            if(Rec[i][j]==1){
                lcs(i-1,j-1,x,Rec);
                //注意此处输出x[i-1] 末尾下标为 i-1,参考链接>https://blog.csdn.net/qq_58668057/article/details/123774788
                System.out.print(x[i-1]);

            }else if(Rec[i][j]==2) lcs(i-1,j,x,Rec);
            else lcs(i,j-1,x,Rec);
        }

        public static void main(String[] args){
            Lcs object=new Lcs();
            char[] x={'A','A','G','A'};
            char[] y={'A','T','A','G','C','G','T','C'};
            //定义一个数组用来记录c[i][j]的值是由哪一个子问题的解得到的
            int [][] Rec=new int[x.length+1][y.length+1];
            object.LcsLength(x,y,Rec);
        }

    }
