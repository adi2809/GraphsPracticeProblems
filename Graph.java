import java.util.*;
public class Graph<E>
{
    /**
     * this class will implement graph based data structures using adjacency list representation.
     * */
    public int num_vertices;
    public ArrayList<E> vertex_labels;
    public TreeMap<E, ArrayList<E>> adjacency_list;

    public Graph(int num_vertices, ArrayList<E> labels)
    {
        this.num_vertices = num_vertices;
        this.vertex_labels = labels;
        this.adjacency_list = new TreeMap<>();
        for(E label: this.vertex_labels)
            this.adjacency_list.put(label, new ArrayList<>());
    }

    public void addEdge(E i, E j, boolean undirected)
    {
       if(undirected)
       {
           this.adjacency_list.get(i).add(j);
           this.adjacency_list.get(j).add(i);
       }
       else
           this.adjacency_list.get(i).add(j);
    }

    public void deleteVertex(E i)
    {
        this.adjacency_list.remove(i);
        for(Map.Entry<E, ArrayList<E>> e: adjacency_list.entrySet())
            e.getValue().remove(i);
    }

    public void deleteEdge(E i, E j, boolean undirected)
    {
        if(undirected)
        {
            this.adjacency_list.get(i).remove(j);
            this.adjacency_list.get(j).remove(i);
        }
        else
        {
            this.adjacency_list.get(i).remove(j);
        }
    }

}
