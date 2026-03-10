import java.util.*;
import java.io.*;

public class Main {

	static int V, E;
	static long ANS;
	static int[] p;
	static Edge[] list;

	static class Edge implements Comparable<Edge> {
		int a, b, w;

		Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		p = new int[V + 1];
		Arrays.fill(p, -1);
		
		list = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[i] = new Edge(a, b, w);
		}
		Arrays.sort(list);

		int cnt = 0;
		ANS = 0;
		for (Edge e : list) {
			if (union(e.a, e.b)) {
				cnt++;
				ANS += e.w;
				if (cnt == V - 1)
					break;
			}
		}

		System.out.println(ANS);
	}

	static int find(int a) {
		if (p[a] < 0)
			return a;
		return p[a] = find(p[a]);
	}

	static boolean union(int a, int b) {
		int ar = find(a);
		int br = find(b);

		if (ar == br)
			return false;

		if (p[ar] < p[br]) {
			p[br] = ar;
		} else {
			if (p[ar] == p[br])
				p[br]--;
			p[ar] = br;
		}
		return true;
	}
}