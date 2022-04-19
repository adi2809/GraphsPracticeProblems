import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MessageRoute
{
    public int n;
    public int[][] edges;
    public Graph<Integer> g;

    public MessageRoute(int n, int[][] edges)
    {
        this.n = n;
        this.edges = edges;

        ArrayList<Integer> labels = new ArrayList<>();
        for(int i = 1; i<=n; i++){
            labels.add(i);
        }

        this.g = new Graph<>(n, labels);
        for(int[] edge: this.edges)
        {
            g.addEdge(edge[0], edge[1], true);
        }
    }

    public boolean findPath()
    {
        boolean[] visited = new boolean[this.n+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1]=true;

        while(!q.isEmpty())
        {
            int curr = q.poll();
            for(int i:g.adjacency_list.get(curr))
            {
                if(!visited[i])
                {
                    if(i==this.n)
                        return true;
                    visited[i] = true;
                    q.offer(i);
                }
            }

        }
        return false;
    }

    public static void main(String[] args)
    {
        int n = 6;
        int[][] edges1 = {{4, 1}, {1, 2}, {2, 5}, {2, 3}, {1, 3}, {3, 6}};
        int[][] edges2 = {{4, 1}, {1, 2}, {2, 5}, {2, 3}, {1, 3}, {3, 5}};
        MessageRoute mr = new MessageRoute(n, edges1);
        System.out.println(mr.findPath());

        MessageRoute mrNew = new MessageRoute(n, edges2);
        System.out.println(mrNew.findPath());
    }
}
