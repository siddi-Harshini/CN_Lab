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
