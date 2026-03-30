# 벽 부수고 이동하기
# BFS
# Claude 참고

from collections import deque

N, M = map(int, input().split())
graph = [list(map(int, input())) for _ in range(N)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs():
    q = deque()
    q.append((0, 0, 1, False))  # x, y, 거리, 벽 사용 여부
    visited = [[[False]*2 for _ in range(M)] for _ in range(N)]
    visited[0][0][0] = True

    while q:
        x, y, dist, wall = q.popleft()

        if x == N-1 and y == M-1:
            print(dist)
            return

        for d in range(4):
            nx, ny = x+dx[d], y+dy[d]

            if 0 <= nx < N and 0 <= ny < M:
                if graph[nx][ny] == 1:  # 벽
                    if not wall and not visited[nx][ny][1]:
                        visited[nx][ny][1] = True
                        q.append((nx, ny, dist+1, True))
                else:  # 빈 칸
                    w = int(wall)
                    if not visited[nx][ny][w]:
                        visited[nx][ny][w] = True
                        q.append((nx, ny, dist+1, wall))

bfs()