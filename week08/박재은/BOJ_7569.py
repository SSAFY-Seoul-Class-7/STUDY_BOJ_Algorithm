import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline
from collections import deque

dx = [0, 0, 0, 1, 0, -1]
dy = [0, 0, 1, 0, -1, 0]
dz = [-1, 1, 0, 0, 0, 0]
m, n, h = map(int, input().split())
tomato = []
visited = [[[False] * m for _ in range(n)] for _ in range(h)]
ans = 0
for _ in range(h):
    tomato.append([list(map(int, input().split())) for _ in range(n)])
Q = deque()
for i in range(h):
    for j in range(n):
        for k in range(m):
            if tomato[i][j][k] == 1:
                Q.append([i, j, k, 0])

while Q:
    tz, tx, ty, day = Q.popleft()
    ans = day
    for d in range(6):
        zz = tz + dz[d]
        xx = tx + dx[d]
        yy = ty + dy[d]
        if 0 <= zz < h and 0 <= xx < n and 0 <= yy < m and not visited[zz][xx][yy] and tomato[zz][xx][yy] == 0:
            tomato[zz][xx][yy] = 1
            visited[zz][xx][yy] = True
            Q.append([zz, xx, yy, day+1])

for i in range(h):
    for j in range(n):
        for k in range(m):
            if tomato[i][j][k] == 0:
                print(-1)
                exit(0)
print(day)