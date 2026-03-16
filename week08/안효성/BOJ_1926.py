from collections import deque
import sys

input = sys.stdin.readline

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

count = 0
max_area = 0

def bfs(x, y):
    q = deque()
    q.append((x, y))
    graph[x][y] = 0
    area = 1

    while q:
        cx, cy = q.popleft()

        for d in range(4):
            nx = cx + dx[d]
            ny = cy + dy[d]

            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 1:
                graph[nx][ny] = 0
                q.append((nx, ny))
                area += 1

    return area

for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            count += 1
            max_area = max(max_area, bfs(i, j))

print(count)
print(max_area)
