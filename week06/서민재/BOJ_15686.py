# 치킨 배달
# BFS

from collections import deque

N, M = map(int, input().split())
graph = []

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

chicken = []

for i in range(N):
    graph.append(list(map(int, input().split())))
    for j in range(N):
        if graph[i][j] == 2:
            chicken.append((i,j))

def bfs(pick_chicken):
    q = deque()
    visited_graph = [[False]*N for _ in range(N)]
    dist = 0

    for i, j in pick_chicken:
        q.append((i, j, i, j))
        visited_graph[i][j] = True
    
    while q:
        cx, cy, x, y = q.popleft()

        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]

            if 0<=nx<N and 0<=ny<N:
                if not visited_graph[nx][ny]:
                    visited_graph[nx][ny] = True
                    
                    if graph[nx][ny] == 1:
                        dist += abs(cx-nx)+abs(cy-ny)
                    
                    q.append((cx, cy, nx, ny))

    return dist

def combination(start, keep_chicken):
    global answer

    if len(keep_chicken) == M:
        answer = min(answer, bfs(keep_chicken))
        return 
    
    for i in range(start, chicken_cnt):
        if not visited[i]:
            visited[i] = True
            keep_chicken.append(chicken[i])
            combination(i+1, keep_chicken)
            keep_chicken.pop()
            visited[i] = False

chicken_cnt = len(chicken)
answer = 1_000_000_000

if chicken_cnt == M:
    print(bfs(chicken))
else:
    visited = [False] * chicken_cnt
    combination(0, [])
    print(answer)