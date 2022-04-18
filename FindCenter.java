public class FindCenter
{
    int n;
    int[][] edges;

    public FindCenter(int n, int[][] edges)
    {
        this.n = n;
        this.edges = edges;
    }

    public int findCenter()
    {
        int[] degreeArray = new int[this.n+1];
        for(int[] edge : this.edges)
        {
            degreeArray[edge[0]]++;
            degreeArray[edge[1]]++;
        }

        for(int i = 1; i<=this.n; i++)
        {
            if(degreeArray[i]==this.n-1)
            {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args)
    {
        int n = 4;
        int[][] edges = {{1, 2}, {2,3}, {4, 2}};

        FindCenter fc = new FindCenter(n, edges);
        System.out.println(fc.findCenter());
    }
}
