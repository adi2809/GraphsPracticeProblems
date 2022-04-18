import java.util.*;
public class MaximalNetworkRank
{
    int n;
    int[][] edges;

    public MaximalNetworkRank(int n, int[][] edges)
    {
        this.n = n;
        this.edges = edges;
    }

    public int delta(int i, int j)
    {
        for(int[] edge: this.edges)
        {
            if((edge[0]==i && edge[1]==j)||(edge[1]==i && edge[0]==j))
            {
                return -1;
            }
        }
        return 0;
    }

    public int findMaxNetworkRank()
    {
        int[] degreeArray = new int[this.n+1];
        for(int[] edge : this.edges)
        {
            degreeArray[edge[0]]++;
            degreeArray[edge[1]]++;
        }

        int maxRank = 0;
        for(int i = 1; i<=this.n; i++)
        {
            for(int j = i+1; j<=n; j++)
            {
                if(degreeArray[i]+degreeArray[j]+this.delta(i, j)>maxRank)
                {
                    maxRank = degreeArray[i]+degreeArray[j]+this.delta(i, j);
                }
            }
        }
        return maxRank;
    }

    public static void main(String[] args)
    {
        int n = 4;
        int[][] edges = {{1, 2}, {1, 4}, {2, 3}, {2, 4}};

        MaximalNetworkRank mnr = new MaximalNetworkRank(n, edges);
        System.out.println(mnr.findMaxNetworkRank());
    }
}
