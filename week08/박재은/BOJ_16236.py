import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

from collections import deque
n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def BFS(x, y, size):
    visited = [[0] * n for _ in range(n)]
    Q = deque([(x, y)])
    visited[x][y] = 1
    can_eat = []

    while Q:
        i, j = Q.popleft()
        for d in range(4):
            xx = i + dx[d]
            yy = j + dy[d]
            if 0 <= xx < n and 0 <= yy < n and visited[xx][yy] == 0:
                if board[xx][yy] <= size:
                    visited[xx][yy] = visited[i][j] + 1
                    Q.append((xx, yy))
                    if 0 < board[xx][yy] < size:
                        can_eat.append((visited[xx][yy], xx, yy))
    return sorted(can_eat)


pos = []
for i in range(n):
    for j in range(n):
        if board[i][j] == 9:
            x, y = i, j
            break

ans = 0
size = [2, 0]
while True:
    board[x][y] = 0
    can_eat = BFS(x, y, size[0])
    if not can_eat:
        break
    dist, xx, yy = can_eat[0]
    ans += (dist - 1)

    x, y = xx, yy
    size[1] += 1
    if size[0] == size[1]:
        size[0] += 1
        size[1] = 0
print(ans)