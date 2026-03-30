import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

from collections import deque
n = int(input())
map = [list(map(int, input().split())) for _ in range(n)]
dx = [0, 1]
dy = [1, 0]
visited = [[False] * n for _ in range(n)]

def BFS(x, y):
    Q = deque()
    Q.append((x, y))
    visited[x][y] = True

    while Q:
        x, y = Q.popleft()
        if map[x][y] == -1:
            return "HaruHaru"
        cnt = map[x][y]

        for d in range(2):
            xx = x + dx[d] * cnt
            yy = y + dy[d] * cnt
            if 0 <= xx < n and 0 <= yy < n and visited[xx][yy] == 0:
                visited[xx][yy] = True
                Q.append((xx, yy))
    return "Hing"

print(BFS(0, 0))