import java.util.ArrayList;

public class MinimumConnectedTrio
{
    int n;
    int[][] edges;
    ArrayList<Integer>labels = new ArrayList<>();
    Graph<Integer> g;

    public MinimumConnectedTrio(int n, int[][] edges)
    {
        this.n = n;
        this.edges = edges;

        for(int i=1; i<=n; i++)
            this.labels.add(i);

        this.g = new Graph<>(n,labels);

        for(int[] edge: edges)
            this.g.addEdge(edge[0], edge[1], true);
    }

    public int findMinimumConnectedTrio()
    {
        int minRank = 1000000007;
        for(Integer v1: g.adjacency_list.keySet())
        {
            for(Integer v2: g.adjacency_list.get(v1))
            {
                for(Integer v3: g.adjacency_list.get(v1))
                {
                    if(g.adjacency_list.get(v2).contains(v3))
                    {
                        int candidate = g.adjacency_list.get(v1).size()+g.adjacency_list.get(v2).size()+g.adjacency_list.get(v3).size()-6;
                        if(minRank>candidate)
                        {
                            minRank=candidate;
                        }
                    }
                }
            }
        }
        return minRank;
    }

    public static void main(String[] args)
    {
        int n = 6;
        int[][] edges = {{4, 1}, {1, 2}, {2, 5}, {2, 3}, {1, 3}, {3, 6}};
        MinimumConnectedTrio mct = new MinimumConnectedTrio(n, edges);
        System.out.println(mct.findMinimumConnectedTrio());
    }
}
