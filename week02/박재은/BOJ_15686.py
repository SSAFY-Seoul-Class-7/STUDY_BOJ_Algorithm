# 치킨 배달
import math
def distance(selected):
    # 고른 m개의 치킨집으로 도시 거리 구하기
    city_dist = 0
    for x1, y1 in home:
        min_dist = math.inf
        for x2, y2 in selected:
            min_dist = min(min_dist, abs(x1-x2) + abs(y1-y2))
        city_dist += min_dist
    return city_dist

def DFS(d, cur):
    global ans
    if d == m:
        # 폐업하지 않는 치킨집
        selected = []
        for i in range(len(chicken)):
            if visited[i]:
                selected.append(chicken[i])
        ans = min(ans, distance(selected))
    for i in range(cur, len(chicken)):
        if not visited[i]:
            visited[i] = True
            DFS(d+1, i+1)
            visited[i] = False


n, m = map(int, input().split())
li = [list(map(int, input().split())) for _ in range(n)]
home = []
chicken = []
for i in range(n):
    for j in range(n):
        if li[i][j] == 1:
            home.append([i, j])
        if li[i][j] == 2:
            chicken.append([i, j])
ans = math.inf
visited = [False] * len(chicken)
DFS(0, 0)
print(ans)