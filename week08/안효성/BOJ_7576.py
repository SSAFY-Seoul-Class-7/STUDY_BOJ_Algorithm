from collections import deque
import sys

input = sys.stdin.readline

m, n = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

q = deque()

# 처음부터 익은 토마토들 전부 큐에 넣기
for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            q.append((i, j))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

while q:
    x, y = q.popleft()

    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]

        if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0:
            graph[nx][ny] = graph[x][y] + 1
            q.append((nx, ny))

answer = 0

for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            print(-1)
            sys.exit(0)
        answer = max(answer, graph[i][j])

# 처음 익은 토마토가 1부터 시작했으니 날짜는 -1
print(answer - 1)
