# 2583 영역 구하기
# DFS

M, N, K = map(int, input().split())
graph = [[0]*N for _ in range(M)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for _ in range(K):
    lx, ly, rx, ry = map(int, input().split())
    
    for i in range(ly, ry):
        for j in range(lx, rx):
            graph[i][j] = 1

def dfs(i, j):
    graph[i][j] = 1
    cnt = 1

    for d in range(4):
        nx = i + dx[d]
        ny = j + dy[d]

        if 0<=nx<M and 0<=ny<N:
            if graph[nx][ny] == 0:
                cnt += dfs(nx, ny)

answer = []
for x in range(N):
    for y in range(M):
        if graph[x][y] == 0:
            ans = dfs(x, y)
            answer.append(ans)

answer.sort()
print(len(answer))
print(*answer)