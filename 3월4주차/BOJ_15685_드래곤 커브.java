import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] dy = {0, -1, 0, 1}; // d: 0, 1, 2, 3
	static int[] dx = {1, 0, -1, 0}; //    우 상 좌 하
	static boolean grid[][] = new boolean[101][101];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			grid[x][y] = true;
			drawCurve(x, y, d, g);
		}
		
		System.out.println(count());
	}
	
	static void drawCurve(int x, int y, int d, int g) {
		int[] dList = new int[1<<g];
		x += dx[d];
		y += dy[d];
		grid[x][y] = true;
		dList[0] = d;
		
		for (int i=1; i<=g; i++) {
			for (int j=(1<<i-1); j<(1<<i); j++) {
				// 이전 세대의 맨 마지막 인덱스에서 하나씩 뺌
				int idx = (1<<i) -1 -j;
				int nd = (dList[idx]+1)%4;
				x += dx[nd];
				y += dy[nd];
				grid[x][y] = true;
				dList[j] = nd;
			}
		}
	}
	
	static int count() {
		int result = 0;
		
		for (int x=0; x<100; x++) {
			for (int y=0; y<100; y++) {
				if (check(x,y)) result++;
			}
		}
		
		return result;
	}
	
	static boolean check(int x, int y) {
		return grid[x][y] && grid[x+1][y+1] && grid[x+1][y] && grid[x][y+1];
	}
}