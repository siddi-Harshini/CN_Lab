package CNLab;

import java.util.*;

public class Dijkstrasalgoo{

    static final int MAX_NODES = 10;
    static final int INF = Integer.MAX_VALUE;

    // Function to find vertex with minimum distance not yet processed
    static int minDistance(int dist[], boolean sptSet[], int nodes) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < nodes; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Function to print the shortest path from source to a vertex
    static void printPath(int parent[], int j) {
        if (parent[j] == -1) {
            System.out.print((char)(j + 'a') + " ");
            return;
        }
        printPath(parent, parent[j]);
        System.out.print((char)(j + 'a') + " ");
    }

    // Dijkstra’s algorithm implementation
    static void dijkstra(int graph[][], int src, int dest, int nodes) {
        int dist[] = new int[nodes];
        boolean sptSet[] = new boolean[nodes];
        int parent[] = new int[nodes];

        // Initialize distances and parent array
        for (int i = 0; i < nodes; i++) {
            dist[i] = INF;
            sptSet[i] = false;
            parent[i] = -1;
        }

        dist[src] = 0;

        for (int count = 0; count < nodes - 1; count++) {
            int u = minDistance(dist, sptSet, nodes);
            if (u == -1) break;  // no reachable vertex left

            sptSet[u] = true;

            for (int v = 0; v < nodes; v++) {
                if (!sptSet[v] && graph[u][v] != -1 && dist[u] != INF
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        // Print the shortest path and its length
        System.out.print("Shortest path from " + (char)(src + 'a') + " to " + (char)(dest + 'a') + " is: ");
        if (dist[dest] == INF) {
            System.out.println("No path exists.");
        } else {
            printPath(parent, dest);
            System.out.println("\nTotal path length = " + dist[dest]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of nodes (max " + MAX_NODES + "): ");
        int nodes = sc.nextInt();
        if (nodes > MAX_NODES || nodes <= 0) {
            System.out.println("Invalid number of nodes.");
            sc.close();
            return;
        }

        int graph[][] = new int[nodes][nodes];

        System.out.println("Enter the adjacency matrix (-1 for no edge):");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter the source node (a-" + (char)('a' + nodes - 1) + "): ");
        char srcChar = sc.next().charAt(0);
        System.out.print("Enter the destination node (a-" + (char)('a' + nodes - 1) + "): ");
        char destChar = sc.next().charAt(0);

        int src = srcChar - 'a';
        int dest = destChar - 'a';

        if (src < 0 || src >= nodes || dest < 0 || dest >= nodes) {
            System.out.println("Invalid source or destination node.");
            sc.close();
            return;
        }

        dijkstra(graph, src, dest, nodes);

        sc.close();
    }
}


// Output
// Enter the number of nodes:6
// Enter the adjacency matrix for the graph(-1 for no edge):
// 0 1 -1 2 -1 -1
// 1 0 1 -1 -1 -1
// -1 1 0 1 1 -1
// 2 -1 1 0 2 -1
// -1 -1 1 2 0 3
// -1 -1 -1 -1 3 0
// Enter the source node:a
// Enter the destination node:f
// Shortest path:
// From a to f is:a b c e f length=6















import java.util.*;
public class Main {
    static final int INF = 9999;
    static final int MAX = 10;
    static void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(j);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print(" -> " + j);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] graph = new int[MAX][MAX];
        int[] dist = new int[MAX];
        boolean[] visited = new boolean[MAX];
        int[] parent = new int[MAX];

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = sc.nextInt();

        System.out.printf("Enter starting node (0 to %d): ", n - 1);
        int start = sc.nextInt();

        // Initialize distances and tracking arrays
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);
        dist[start] = 0;

        // Dijkstra's algorithm
        for (int count = 0; count < n - 1; count++) {
            int min = INF, u = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }

            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v] && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }
        System.out.println("\nPaths from node " + start + ":");
        for (int i = 0; i < n; i++) {
            if (dist[i] != INF) {
                printPath(parent, i);
                System.out.println();
            }
        }
    }
}

INPUT:
Enter number of nodes: 7
Enter adjacency matrix:
0 2 6 0 0 0 0
2 0 0 5 0 0 0
6 0 0 8 0 0 0
5 8 0 0 10 15 0
0 0 0 10 0 0 2
0 0 15 0 0 0 6
0 0 0 0 2 6 0
Enter starting node (0 to 6): 0

OUTPUT:
Shortest distances and paths from node 0:
To node 0: 0, Path: 0
To node 1: 2, Path: 0 -> 1
To node 2: 6, Path: 0 -> 2
To node 3: 7, Path: 0 -> 1 -> 3
To node 4: 17, Path: 0 -> 1 -> 3 -> 4
To node 5: 22, Path: 0 -> 1 -> 3 -> 5
To node 6: 19, Path: 0 -> 1 -> 3 -> 4 -> 6


