import java.util.Scanner;

public class DistanceVectorRouting {
    static final int MAX = 10;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] cost = new int[MAX][MAX];
        int[][] dist = new int[MAX][MAX];

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.println("Enter cost matrix (-1 for no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = sc.nextInt();
                cost[i][j] = (x == -1) ? INF : x;
            }
        }

        // Initialize distance matrix
        for (int i = 0; i < n; i++)
            System.arraycopy(cost[i], 0, dist[i], 0, n);

        // Distance Vector Algorithm
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        // Print routing tables
        System.out.println("Routing tables:");
        for (int i = 0; i < n; i++) {
            System.out.print("Node " + i + ": ");
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}



// OUTPUT
// Enter number of nodes: 4
// Enter cost matrix (-1 for no edge):
// 0 1 3 -1
// 1 0 1 4
// 3 1 0 2
// -1 4 2 0
// Routing tables:
// Node 0: 0 1 2 4 
// Node 1: 1 0 1 3 
// Node 2: 2 1 0 2 
// Node 3: 4 3 2 0 