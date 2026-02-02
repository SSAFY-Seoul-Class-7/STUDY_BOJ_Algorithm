import sys
input = sys.stdin.readline

N, K = map(int, input().split())
dist = [list(map(int, input().split())) for _ in range(N)]

for k in range(N):
    for i in range(N):
        for j in range(N):
            if dist[i][j] > dist[i][k] + dist[k][j]:
                dist[i][j] = dist[i][k] + dist[k][j]

visited = [False] * N
visited[K] = True
answer = float('inf')

def dfs(cur, count, cost):
    global answer

    if cost >= answer:
        return

    if count == N:
        answer = min(answer, cost)
        return

    for nxt in range(N):
        if not visited[nxt]:
            visited[nxt] = True
            dfs(nxt, count + 1, cost + dist[cur][nxt])
            visited[nxt] = False

dfs(K, 1, 0)

print(answer)
