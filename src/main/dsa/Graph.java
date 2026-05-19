package dsa;

import java.util.*;
import java.util.LinkedList;

public class Graph {

    // a static class to solve the No.of Islands problem
    // since the nodes are stored in a matrix,  it will return a pair containing row and column.
    static class Pair {
        int first;
        int second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    // the adjacency list can be a class variable since it is common for every graph created.
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    // for undirected graph
    public void createAdjListUD(int M, int N) {
        Scanner sc = new Scanner(System.in);
        // N+1 - creating an arraylist for every node and an extra zero index
        for(int i = 0; i < N+1; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // storing the neighbors of every node
        for(int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
    }
    // for directed graph
    public void createAdjListD(int M, int N) {
        Scanner sc = new Scanner(System.in);
        // N+1 - creating an arraylist for every node and an extra zero index
        for(int i = 0; i < N+1; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // storing the neighbors of every node
        for(int i = 0; i < 2*M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }
    }

    // Traversal functions
    // depth first search - DFS
    public ArrayList<Integer> depthFirstSearch(int node, boolean[] vis, ArrayList<Integer> ls) {
        vis[node] = true; // mark the node as visited
        ls.add(node); // add the node to the list. this list is used to store the traversal path
        ArrayList<Integer> nodes = adj.get(node); // extract the neighbors of current node
        for(int val : nodes) { // if the neighbor is not visited, go visit it.
            if(!vis[val]) depthFirstSearch(val, vis, ls);
        }
        return ls;
    }

    // breadth first search - BFS
    public ArrayList<Integer> breadthFirstSearch(boolean[] vis, ArrayList<Integer> ls, int N, int startNode) {
        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);
        vis[startNode] = true;

        while(!q.isEmpty()) {
            int node = q.poll();
            ls.add(node);

            // get all the neighbors of the recently popped node
            // and traverse the neighbors if they are not visited
            // enqueue them, mark as visited
            // repeat until all the nodes are marked as visited
            ArrayList<Integer> neighbors = adj.get(node);
            for(int neighbor : neighbors) {
                if(!vis[neighbor]) {
                    q.add(neighbor);
                    vis[neighbor] = true;
                }
            }
        }
        return ls;
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!vis[i][j] && grid[i][j] == '1') { // traverse only if unvisited and is land
                    count++;
                    bfs(i, j, vis, grid);
                }
            }
        }
        return count;
    }

    public void bfs(int row, int col, boolean[][] vis, char[][] grid) {
        vis[row][col] = true;
        int n = grid.length;
        int m = grid[0].length;

        // A queue to keep track of neighbors
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(row, col));

        while(!q.isEmpty()) {
            int r = q.peek().first;
            int c = q.peek().second;
            q.remove();

            // check if the neighbors are land, if yes enqueue them and mark as visited.
            int[] deltaRow = {-1, 0, 1, 0};
            int[] deltaCol = {0, 1, 0, -1};

            for(int i = 0; i < 4; i++) {
                int nrow = r + deltaRow[i];
                int ncol = c + deltaCol[i];
                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                        grid[nrow][ncol] == '1' && !vis[nrow][ncol]) {
                    vis[nrow][ncol] = true;
                    q.add(new Pair(nrow, ncol));
                }
            }
        }
    }
    // utility functions
    public void display(int N) {
        for(int i = 1; i <= N; i++) {
            System.out.println(i + "->" + adj.get(i));
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        System.out.println("1. Undirected Graph \n2. Directed Graph");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                g.createAdjListUD(M, N);
                break;
            case 2:
                g.createAdjListD(M, N);
                break;
            default:
                System.out.println(0);
                break;
        }
        boolean[] visited = new boolean[N+1];
        ArrayList<Integer> ls = new ArrayList<>();
        System.out.println("Select the operation you want to perform: \n" +
                "1. Display Graph\n" +
                "2. Breadth First Search\n" +
                "3. Depth First Search");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                g.display(N);
                break;
            case 2:
                System.out.println("Enter the Node that you want start from: ");
                int startNode = sc.nextInt();
                System.out.println(g.breadthFirstSearch(visited, ls, N, startNode));
                break;
            case 3:
                System.out.println("Enter the Node that you want start from: ");
                int startNode1 = sc.nextInt();
                System.out.println(g.depthFirstSearch(startNode1, visited, ls));
                break;
            default:
                System.out.println(0);
                break;
        }

        // LeetCode #200 - Number of Islands
        System.out.println(g.numIslands(new char[][] {{'1','1','0','0', '0'},{'1','1','0','0','0'},{'0','0','1','0','0',},{'0','0','0','1','1'}}));
    }
}
