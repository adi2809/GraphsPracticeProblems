import java.util.*;

public class WordLadder
{
    public int n;
    public String[] wordList;
    public String startWord;
    public String endWord;

    public Graph<String> g;

    public boolean isConnected(String w1, String w2)
    {
        int s = 0;
        for(int i = 0; i<w1.length(); i++)
        {
            if(w2.contains(w1.substring(i, i+1)))
            {
                s+=1;
            }
        }
        return s==w1.length()-1;
    }

    public WordLadder(int n, String[] wordList, String startWord, String endWord)
    {
        this.n = n;
        this.wordList = wordList;
        this.startWord = startWord;
        this.endWord = endWord;

        ArrayList<String> labels = new ArrayList<>();
        labels.add(startWord);
        Collections.addAll(labels, wordList);

        this.g = new Graph<>(n, labels);

        for(int i = 0; i<labels.size()-1; i++)
        {
            for(int j = i+1; j<labels.size(); j++)
            {
                if(this.isConnected(labels.get(i), labels.get(j)))
                {
                    this.g.addEdge(labels.get(i), labels.get(j), true);
                }
            }
        }
    }

    public int findShortestTransform()
    {

        HashMap<String, Boolean> visited = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        q.offer(this.startWord);
        visited.put(this.startWord, true);
        for(String w: this.wordList)
        {
            visited.put(w, false);
        }

        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(this.startWord, 0);
        for(String w: this.wordList)
        {
            distances.put(w, 0);
        }

        while(!q.isEmpty())
        {
            String curr = q.poll();
            System.out.print(curr+" ");
            for(String i:g.adjacency_list.get(curr))
            {
                if(!visited.get(i))
                {
                    distances.put(i, distances.get(curr)+1);
                    visited.put(i, true);
                    q.offer(i);
                }
            }
        }

        return distances.get(endWord);

    }

    public static void main(String[] args)
    {
        String beginWord = "hit";
        String[] wordList = {"hot", "lot", "dot", "dog", "log", "cog"};
        int n = 7;
        String endWord = "cog";

        WordLadder wl = new WordLadder(n, wordList, beginWord, endWord);
        System.out.println(wl.g.adjacency_list.toString());
        System.out.println(wl.findShortestTransform());

    }
}
