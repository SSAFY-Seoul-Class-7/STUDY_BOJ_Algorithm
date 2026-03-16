from collections import deque
import sys

input = sys.stdin.readline

N = int(input())
graph = []
shark_x, shark_y = 0, 0

for i in range(N):
    row = list(map(int, input().split()))
    for j in range(N):
        if row[j] == 9:
            shark_x, shark_y = i, j
            row[j] = 0  # 시작 위치는 빈칸 처리
    graph.append(row)
dx = [-1, 0, 0, 1]
dy = [0, -1, 1, 0]

shark_size = 2
eat_count = 0
time = 0

def bfs(sx, sy, size):
    visited = [[-1] * N for _ in range(N)]
    q = deque()
    q.append((sx, sy))
    visited[sx][sy] = 0

    fishes = []
    min_dist = float('inf')

    while q:
        x, y = q.popleft()
        dist = visited[x][y]

        
        if dist > min_dist:
            continue

        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]

            if 0 <= nx < N and 0 <= ny < N and visited[nx][ny] == -1:
                # 자기보다 큰 물고기 칸은 못 감
                if graph[nx][ny] <= size:
                    visited[nx][ny] = dist + 1
                    q.append((nx, ny))

                    # 먹을 수 있는 물고기
                    if 0 < graph[nx][ny] < size:
                        min_dist = dist + 1
                        fishes.append((dist + 1, nx, ny))

    if not fishes:
        return None
    fishes.sort()
    return fishes[0]


while True:
    result = bfs(shark_x, shark_y, shark_size)

    if result is None:
        break

    dist, fx, fy = result
    time += dist

    graph[fx][fy] = 0
    shark_x, shark_y = fx, fy
    eat_count += 1

    if eat_count == shark_size:
        shark_size += 1
        eat_count = 0

print(time)
