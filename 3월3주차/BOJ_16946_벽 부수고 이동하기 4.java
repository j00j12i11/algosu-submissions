import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] size;
	static int[][] map;
	static int[][] result;
	static int[][] id;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		size = new int [N*M];
		map = new int[N][M];
		result = new int[N][M];
		id = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			String line = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		queue = new ArrayDeque<>();
		int cnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					int s = sizeCheck(i, j, cnt);
					size[cnt++] = s;
				}
			}
		}
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 1) {
					add(i,j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int[]r : result) {
			for (int i : r) {
				sb.append(i);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static int sizeCheck(int r, int c, int cnt) {
		int result = 1;
		queue.add(new int[] {r,c});
		visited[r][c] = true;
		id[r][c] = cnt;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i=0; i<4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0 && !visited[nr][nc]) {
					result++;
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
					id[nr][nc] = cnt;
				}
			}
		}
		
		return result;
	}
	
	static void add(int r, int c) {
		Set<Integer> set = new HashSet<>();
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {
				set.add(id[nr][nc]);
			}
		}
		int sum = 1;
		for (int num : set) {
		    sum += size[num];
		}
		
		result[r][c] = sum % 10;
	}
}