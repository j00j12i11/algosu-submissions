import java.io.*;
import java.util.*;

public class Main {
	
	static int[] p;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		p = new int[V+1];
		Arrays.fill(p, -1);
		
		long[][] l = new long[E][3];
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<3;j++)
				l[i][j] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(l, ((l1, l2) -> Long.compare(l1[2], l2[2])));
		
		int cnt=0;
		long ANS = 0;
		for (long[] line : l) {
			int a = (int)line[0];
			int b = (int)line[1];
			int aRoot = find(a);
			int bRoot = find(b);
			
			if (aRoot == bRoot) continue;
			
			if (p[aRoot] < p[bRoot]) {
				p[aRoot] = bRoot;
			} else {
				if (p[aRoot] == p[bRoot])  p[aRoot]--;
				p[bRoot] = aRoot;
			}
			cnt++;
			ANS += line[2];
			if (cnt == V-1) break;
		}
		
		System.out.println(ANS);

	}
	static int find(int a) {
		if (p[a] < 0) return a;
		return p[a] = find(p[a]);
	}
}
