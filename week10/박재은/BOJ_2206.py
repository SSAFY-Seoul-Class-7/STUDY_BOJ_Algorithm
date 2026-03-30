import sys
sys.stdin = open('input.txt', 'r')
# input = sys.stdin.readline

from collections import deque

n, m = map(int, input().split())
graph = [list(map(int, input())) for _ in range(n)]
visited = [[[0] * 2 for _ in range(m)] for _ in range(n)]
visited[0][0][0] = 1
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def BFS(x, y, z):
    Q = deque()
    Q.append((x, y, z))

    while Q:
        a, b, c = Q.popleft()
        if a == n-1 and b == m-1:
            return visited[a][b][c]
        for d in range(4):
            xx = a + dx[d]
            yy = b + dy[d]
            if 0 <= xx < n and 0 <= yy < m and not visited[xx][yy][c]:
                if not graph[xx][yy]:
                    visited[xx][yy][c] = visited[a][b][c] + 1
                    Q.append((xx, yy, c))
                if not c and graph[xx][yy]:
                    visited[xx][yy][1] = visited[a][b][c] + 1
                    Q.append((xx, yy, 1))
    return -1

print(BFS(0, 0, 0))