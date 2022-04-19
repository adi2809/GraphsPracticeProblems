import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class IntegerComparator implements Comparator<Integer>
{
    public ArrayList<Integer> order;
    public IntegerComparator(ArrayList<Integer> order)
    {
        this.order = order;
    }
    public int compare(Integer i, Integer j)
    {
        if (this.order.indexOf(i)<this.order.indexOf(j))
            return i;
        else if (this.order.indexOf(i)>this.order.indexOf(j))
            return j;
        return i;
    }
}
public class ValidBFS
{
    public int n;
    public int[][] edges;
    public Graph<Integer> g;
    public ArrayList<Integer> bfsOrder;


    public ValidBFS(int n, int[][] edges, ArrayList<Integer> bfsOrder)
    {
        this.n = n;
        this.edges = edges;

        ArrayList<Integer> labels = new ArrayList<>();
        for (int i = 1; i<=n; i++)
        {
            labels.add(i);
        }

        this.g =new Graph<>(n, labels);
        for(int[] edge: edges)
        {
            this.g.addEdge(edge[0], edge[1], true);
        }
        this.bfsOrder = bfsOrder;
    }

    public boolean checkIfValid()
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>(this.n, new IntegerComparator(this.bfsOrder));
        boolean[] visited = new boolean[this.n+1];
        ArrayList<Integer> newOrder = new ArrayList<>();
        pq.offer(this.bfsOrder.get(0));
        visited[this.bfsOrder.get(0)] = true;

        while(!pq.isEmpty())
        {
            int curr = pq.poll();
            newOrder.add(curr);
            for(int i : g.adjacency_list.get(curr))
            {
                if(!visited[i])
                {
                    pq.offer(i);
                    visited[i] = true;
                }
            }
        }

        for(int i = 0; i< newOrder.size(); i++)
        {
            if(!this.bfsOrder.get(i).equals(newOrder.get(i)))
                return false;
        }
        return true;

    }

    public static void main(String[] args)
    {
        int n = 4;
        int[][] edges = {{1,2}, {1,3}, {2,4}};
        ArrayList<Integer> order = new ArrayList<>();
        order.add(1);
        order.add(2);
        order.add(4);
        order.add(3);
        ValidBFS vbfs = new ValidBFS(n, edges, order);
        System.out.println(vbfs.checkIfValid());
    }
}
