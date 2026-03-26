import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C;
	static int INF = 1_000_000;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	
	static Queue<int[]> fire = new ArrayDeque<>();
	static Queue<int[]> move = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i=0; i<R; i++) {
			String line = br.readLine();
			for (int j=0; j<C; j++) {
				char c = line.charAt(j);
				switch(c) {
				case '#':
					map[i][j] = -1;
					break;
				case '.':
					map[i][j] = INF;
					break;
				case 'J':
					move.add(new int[] {i, j, 1});
					break;
				case 'F':
					fire.add(new int[] {i, j, 1});
					break;
				}
			}
		}
		
		burn();
		map[move.peek()[0]][move.peek()[1]] = 0;
		int ANS = play();
		
		if (ANS == -1) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(ANS);
		}
		
	}
	
	static void burn() {
		
		while(!fire.isEmpty()) {
			int[] cur = fire.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nc >= 0 && nr < R && nc < C && map[nr][nc] == INF) {
					map[nr][nc] = time + 1;
					fire.add(new int[] {nr, nc, time + 1});
				}
			}
		}
	}
	
	static int play() {
		
		while(!move.isEmpty()) {
			int[] cur = move.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (r == 0 || r == R - 1 || c == 0 || c == C - 1) {
					return time;
				} else if (map[nr][nc] > time + 1) {
					map[nr][nc] = 0;
					move.add(new int[] {nr, nc, time + 1});
				}
			}
		}
		return -1;
		
	}
	
}
