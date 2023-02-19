package code.daily;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Floweryu
 * @date 2022/12/19 19:20
 */
public class LeetCode1971 {
    /**
     * 广度优先
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] adj = new List[n];
        
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            adj[x].add(y);
            adj[y].add(x);
        }
        
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;
        
        while (! queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == destination) {
                break;
            }
            
            for (int next : adj[cur]) {
                if (! visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        return visited[destination];
    }
    
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        List<Integer>[] adj = new List[n];
    
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            adj[x].add(y);
            adj[y].add(x);
        }
    
        boolean[] visited = new boolean[n];
        return dfs(source, destination, adj, visited);
    }
    
    public boolean dfs(int source, int destination, List<Integer>[] adj, boolean[] visited) {
        if (source == destination) {
            return true;
        }
    
        visited[source] = true;
        for (int next : adj[source]) {
            if (! visited[next] && dfs(next, destination, adj, visited)) {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean validPath3(int n, int[][] edges, int source, int destination) {

        UnionFind unionFind = new UnionFind(n);
        
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }

        return unionFind.connect(source, destination);
    }
    
    
    public static void main(String[] args) {
        
    }
}


class UnionFind {
    private int[] parents;
    private int[] rank;
    
    public UnionFind(int size) {
        parents = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parents[i] = i;
        }
    }
    
    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parents[rooty] = rootx;
            } else if (rank[rooty] > rank[rootx]) {
                parents[rootx] = rooty;
            } else {
                parents[rooty] = rootx;
                rank[rootx]++;
            }
        }
    }
    
    public int find(int val) {
        if (parents[val] != val) {
            parents[val] = find(parents[val]);
        }
        return parents[val];
    }
    
    public boolean connect(int x, int y) {
        return find(x) == find(y);
    }
}