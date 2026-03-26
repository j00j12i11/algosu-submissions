import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C;
	static int INF = 1_000_000; // 큰 값 
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	
	static Queue<int[]> fire = new ArrayDeque<>(); // 불 여러개 가능
	static Queue<int[]> move = new ArrayDeque<>(); // 지훈이 이동 저징용
	
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
				case '#': // 갈 수 없는 곳은 음수
					map[i][j] = -1;
					break;
				case '.': // 갈 수 있는 곳은 무한대
					map[i][j] = INF;
					break;
				case 'J': // 지훈이 위치 기록
					map[i][j] = INF; // 지훈이 위치도 일단 불이 퍼질 수 있어야 함 
					move.add(new int[] {i, j, 1}); // 위치와 시간을 함께 기록함
					break;
				case 'F': // 불 위치 기록
					map[i][j] = 1; // 불의 시작 시간은 1초
					fire.add(new int[] {i, j, 1}); // 위치와 시간을 함께 기록함22
					break;
				}
			}
		}
		
		// 불 먼저 퍼뜨리기
		burn();
		// 처음 지훈이 위치 먼저 방문처리
		map[move.peek()[0]][move.peek()[1]] = 0;
		play();

	}
	
	static void burn() {
		// 불 시작 위치는 이미 1. 첫 불을 꺼내면 시간 2부터 시작해서 쭉 채움
		while(!fire.isEmpty()) {
			int[] cur = fire.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 다음 위치가 범위 내에 있고 빈 공간(무한대)이면...
				if (nr >= 0 && nc >= 0 && nr < R && nc < C && map[nr][nc] == INF) {
					// 시간 1초 더하기
					map[nr][nc] = time + 1;
					fire.add(new int[] {nr, nc, time + 1});
				}
			}
		}
	}
	
	static void play() {
		
		while(!move.isEmpty()) {
			int[] cur = move.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			
			// 현재 위치가 경계 밖이면? 그때의 시간이 정답!
			if (r == 0 || r == R - 1 || c == 0 || c == C - 1) {
				System.out.println(time);
				return;
			}
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 범위 내에 있고,,
				if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
					// 숫자가 현재시간+1보다 크다 => 1초뒤에도 불이 나지 않는 공간이다.
					// 방문한 위치는 0이므로 재방문X + 벽은 -1이므로 벽에도 가지 않음
					if (map[nr][nc] > time + 1) {
						map[nr][nc] = 0; // 방문 처리
						move.add(new int[] {nr, nc, time + 1}); // 1초 증가
					}
				}
			}
		}
		// 탈출 못했을 경우
		System.out.println("IMPOSSIBLE");
	}
	
}
