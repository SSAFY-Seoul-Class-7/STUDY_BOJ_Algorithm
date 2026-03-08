import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline
sys.setrecursionlimit(10000)

dx = [0, 1, 0, -1, -1, 1, 1, -1]
dy = [1, 0, -1, 0, 1, 1, -1, -1]
def DFS(x, y):
    for d in range(8):
        xx = x + dx[d]
        yy = y + dy[d]
        if 0 <= xx < h and 0 <= yy < w and board[xx][yy] == 1:
            board[xx][yy] = 0
            DFS(xx, yy)

while True:
    w, h = map(int, input().split())
    if w == 0 and h == 0:
        break
    board = [list(map(int, input().split())) for _ in range(h)]
    ans = 0

    for i in range(h):
        for j in range(w):
            if board[i][j] == 1:
                board[i][j] = 0
                DFS(i, j)
                ans += 1

    print(ans)