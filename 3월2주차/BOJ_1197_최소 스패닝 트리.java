import java.util.*;
import java.io.*;

public class Solution {
	
	static class Node implements Comparable<Node> {
		int v, w;
		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<Node>[] adjList = new ArrayList[V+1];
			for (int i=1;i<=V;i++) {
				adjList[i] = new ArrayList<Node>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				adjList[a].add(new Node(b, w));
				adjList[b].add(new Node(a, w));
			}
			
			long ANS = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			boolean[] visited = new boolean[V+1];
			
			pq.add(new Node(1,0));
			int cnt = V;
			while(cnt > 0 && !pq.isEmpty()) {
				Node cur = pq.poll();
				
				if (visited[cur.v]) continue;
				cnt--;
				ANS += cur.w;
				visited[cur.v] = true;
				
				for (Node n : adjList[cur.v]) {
					if (!visited[n.v]) {
						pq.add(n);
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, ANS);
		}

	}
}
