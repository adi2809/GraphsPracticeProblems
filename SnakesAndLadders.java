import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders
{
    public int n;
    public int[][] ladders;
    public int[][] snakes;
    public Graph<Integer>g;

    public SnakesAndLadders(int[][] ladders, int[][] snakes, int n)
    {
        this.ladders= ladders;
        this.snakes= snakes;
        this.n = n;

        ArrayList<Integer> labels = new ArrayList<>();
        for(int i = 1; i<=n; i++)
        {
            labels.add(i);
        }

        this.g = new Graph<>(n, labels);
        labels.clear();

        for(int k = 1; k<=6; k++)
        {
            for(int i = 1; i<=this.n-k; i++)
            {
                this.g.addEdge(i, i+k, false);
            }
        }

        for(int[] ladder : this.ladders)
        {
            this.g.addEdge(ladder[0], ladder[1], false);
        }

        for(int[] snake : this.snakes)
        {
            this.g.addEdge(snake[1], snake[0], false);
        }
    }

    public int findShortestPath()
    {
        boolean[] visited = new boolean[this.n+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1]=true;
        int[] distances = new int[this.n+1];
        distances[1] = 0;
        while(!q.isEmpty())
        {
            int curr = q.poll();
            for(int i:g.adjacency_list.get(curr))
            {
                if(!visited[i])
                {
                    distances[i] = distances[curr]+1;
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }

        return distances[this.n];
    }

    public static void main(String[] args)
    {
        int n = 36;
        int[][] ladders = {{2, 15}, {5, 7}, {9, 27}, {18, 29}, {25, 35}};
        int[][] snakes = {{4, 17}, {6, 20}, {12, 34}, {24, 16}, {32, 30}};

        SnakesAndLadders san = new SnakesAndLadders(ladders, snakes, n);
        System.out.println(san.findShortestPath()-1);
    }
}
