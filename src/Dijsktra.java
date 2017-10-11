/**
 * Created by shuoshuo on 2017/10/11.
 */
public class Dijsktra {
    public static void main (String[] args)
    {
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int[] distances = dijkstra(graph, 0);
        int index = 0;

        for (int distance : distances) {
            System.out.println(index++ + ":" + distance);
        }
    }

    private static int[] dijkstra(int[][] graph, int src) {
        int v = graph.length;
        int[] distances = new int[v];  //存放各点到src的最短路径
        boolean[] visited = new boolean[v];  //在剩余的点中，距离src最近的点被选中，设为true

        //init distances,开始时距离都设置为无穷大
        for (int i = 0; i < v; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distances[src] = 0;

        for (int i = 0; i < v; i++) {
            //在所有未遍历的点中找出距离src最近的点
            int u = findMinDistance(distances, v, visited);
            visited[u] = true;

            //途经u，更新所有点的最短路径
            for (int j = 0; j < v; j++) {
                if (graph[u][j] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + graph[u][j] < distances[j]) {
                   distances[j] = distances[u] + graph[u][j];
                }
            }
        }

        return distances;
    }

    private static int findMinDistance(int[] distances, int v, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < v; i++) {
            if (distances[i] < minDistance && !visited[i]) {
                minDistance = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}
