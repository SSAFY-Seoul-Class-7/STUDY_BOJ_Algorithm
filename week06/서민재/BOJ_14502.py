# 연구소
# BFS

from collections import deque

N, M = map(int, input().split())
graph = []

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

empty = []
virus = []
wall_cnt = 0

for i in range(N):
    row = list(map(int, input().split()))
    graph.append(row)
    for j in range(M):
        if graph[i][j] == 0:
            empty.append([i, j])
        if graph[i][j] == 1:
            wall_cnt += 1
        if graph[i][j] == 2:
            virus.append((i, j))


def bfs(lists):
    q = deque()
    visited = [[False]*M for _ in range(N)]
    cnt = 0

    for i, j in lists:
        q.append((i, j))
        visited[i][j] = True
    
    while q:
        x, y = q.popleft()
        cnt += 1

        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]

            if 0<=nx<N and 0<=ny<M:
                if not visited[nx][ny]:
                    visited[nx][ny] = True
                    
                    if graph[nx][ny] == 1:
                        continue
                    
                    q.append((nx, ny))

    return cnt

def combination(start, wall):
    global answer

    if wall == 3:
        answer = max(answer, ((N*M) - (wall_cnt + 3) - bfs(virus)))
        return 
    
    for i in range(start, len(empty)):
        x, y = empty[i]
        graph[x][y] = 1
        combination(i+1, wall+1)
        graph[x][y] = 0

answer = 0
combination(0, 0)
print(answer)