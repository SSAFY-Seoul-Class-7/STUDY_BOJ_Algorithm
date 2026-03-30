import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

def DFS(num, cnt):
    global ans
    visited[num] = True

    for nxt in graph[num]:
        if visited[nxt] == True:
            continue
        if nxt == b:
            ans = cnt
            return
        DFS(nxt, cnt+1)

n = int(input())
a, b = map(int, input().split())
m = int(input())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

visited = [False] * (n+1)
ans = -1
DFS(a, 1)
print(ans)