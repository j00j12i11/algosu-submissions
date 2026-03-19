javaimport java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int[] groupSize;   // 각 그룹 id별 크기
    static int[][] map;       // 원본 맵
    static int[][] groupId;   // 각 칸이 속한 그룹 id (-1이면 미방문)
    static Queue<int[]> queue;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        groupSize = new int[N * M];
        map = new int[N][M];
        groupId = new int[N][M];
        
        // groupId 미방문 초기화 (-1 = 아직 안 들린 칸)
        for (int[] row : groupId) Arrays.fill(row, -1);
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        queue = new ArrayDeque<>();
        
        // 1단계: 0인 칸들 BFS로 그룹화, 각 그룹 크기 저장
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && groupId[i][j] == -1) {
                    groupSize[cnt] = bfs(i, j, cnt);
                    cnt++;
                }
            }
        }
        
        // 2단계: 1인 칸들 상하좌우 그룹 크기 합산
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    sb.append(add(i, j) % 10);
                } else {
                    sb.append(0);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    
    // 0인 칸들 BFS로 같은 그룹 id 부여, 크기 반환
    static int bfs(int r, int c, int cnt) {
        int size = 1;
        queue.add(new int[]{r, c});
        groupId[r][c] = cnt;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr >= 0 && nc >= 0 && nr < N && nc < M
                        && map[nr][nc] == 0 && groupId[nr][nc] == -1) {
                    size++;
                    queue.add(new int[]{nr, nc});
                    groupId[nr][nc] = cnt;
                }
            }
        }
        return size;
    }
    
    // 벽 칸 기준 상하좌우 인접 그룹 크기 합산 (중복 그룹 제외)
    static int add(int r, int c) {
        // 상하좌우 최대 4개라 배열로 중복 체크
        int[] checked = new int[4];
        int checkedCnt = 0;
        int sum = 1; // 벽 자기 자신 포함
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {
                int gid = groupId[nr][nc];
                
                // 이미 더한 그룹인지 체크
                boolean isDuplicate = false;
                for (int k = 0; k < checkedCnt; k++) {
                    if (checked[k] == gid) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    checked[checkedCnt++] = gid;
                    sum += groupSize[gid];
                }
            }
        }
        return sum;
    }
}