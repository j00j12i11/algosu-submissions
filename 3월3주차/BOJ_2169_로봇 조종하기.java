import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int INF = Integer.MAX_VALUE / 2;
	static int[][] map, dp;
	static int[] toRight, toLeft;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		toRight = new int[M+2];
		toLeft = new int[M+2];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 맨 위에 값들을 (1,1)의 바로 위인 (0,1)을 제외하고는 -INF로 설정해야함. (첫줄에서 왼쪽 방향으로 합쳐버리는거 방지)
		Arrays.fill(dp[0], -INF);
		dp[0][1] = 0;
		
		for (int row=1; row <= N; row++) {
			checkR(row);
			checkL(row);
			
			for (int col=1; col <= M; col++) {
				dp[row][col] = Math.max(toRight[col], toLeft[col]);
			}
		}
		System.out.println(dp[N][M]);
		
	}
	
	// 오른쪽 방향으로 가는 경우 비교..  위에서 온것과 왼쪽에서 오른쪽으로 온 것 중 뭐가 더 큰가?
	static void checkR(int r) {
		toRight[0] = -INF; //양쪽에 최소값으로 패딩되어있음.
		for (int i=1; i<=M; i++) {
			toRight[i] = Math.max(toRight[i-1], dp[r-1][i]);
			toRight[i] += map[r][i];
		}
	}
	
	// 왼쪽 방향으로 가는 경우 비교
	static void checkL(int r) {
		toLeft[M+1] = -INF;
		for (int i=M; i>0; i--) {
			toLeft[i] = Math.max(toLeft[i+1], dp[r-1][i]);
			toLeft[i] += map[r][i];
		}
	}
}
