import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
		    Arrays.fill(map[i], -1);
		    map[i][i] = 0; 
		}
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (map[a][b] == -1 || map[a][b] > c) {
		        map[a][b] = c;
		    }
		}
		
		for (int k=1; k<=n; k++) {
			for (int f=1; f<=n; f++) {
				for (int t=1; t<=n; t++) {
					if (map[f][k] != -1 && map[k][t] != -1) {
						int newcost = map[f][k] + map[k][t];
						if (map[f][t] == -1) map[f][t] = newcost;
						else map[f][t] = map[f][t] > newcost ? newcost : map[f][t];
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (map[i][j] == -1) {
					sb.append(0);
				} else {
					sb.append(map[i][j]);
				}
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length()-1);
		
		System.out.println(sb);
		
	}

}