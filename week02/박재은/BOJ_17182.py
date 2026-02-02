#우주 탐사선
def DFS(cnt, start, total):
    global ans
    if cnt == n:
        ans = min(ans, total)
        return
    for i in range(n):
        if not visited[i]:
            visited[i] = True
            DFS(cnt+1, i, total + time[start][i])
            visited[i] = False

n, m = map(int, input().split())
time = [list(map(int, input().split())) for _ in range(n)]
for k in range(n):
    for i in range(n):
        for j in range(n):
            time[i][j] = min(time[i][j], time[i][k] + time[k][j])

visited = [False] * n
visited[m] = True
ans = 2**31 - 1
DFS(1, m, 0)
print(ans)