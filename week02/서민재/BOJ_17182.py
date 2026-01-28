# 17182 우주 탐사선
# 플로이드-워샬

N, K = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]

for k in range(N):
    for i in range(N):
        for j in range(N):
            graph[i][j] = min(graph[i][j], graph[i][k]+graph[k][j])

visited = [False] * N
visited[K] = False

def backtracking(x, y, cnt):
    global answer

    if cnt == N:
        answer = min(answer, y)
        return 

    visited[x] = True

    for i in range(N):
        if not visited[i]:
            backtracking(i, y + graph[x][i], cnt + 1)
            visited[i] = False

answer = 1_000_000
backtracking(K, 0, 1)
print(answer)