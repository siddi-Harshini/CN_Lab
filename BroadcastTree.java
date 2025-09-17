import java.util.*;

public class BroadcastTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        System.out.println("Enter adjacency matrix (0/1):");
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = sc.nextInt();
                if (x != 0) adj.get(i).add(j);
            }
        }
        System.out.print("Enter root node: ");
        int root = sc.nextInt();
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        visited[root] = true;
        q.add(root);
        System.out.println("Broadcast tree edges:");
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    System.out.println(u + " - " + v);
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        sc.close();
    }
}