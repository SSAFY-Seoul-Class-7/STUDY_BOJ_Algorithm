import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline
sys.setrecursionlimit(10000)

def DFS(s, total):
    for nxt, d in adj[s]:
        if visited[nxt] == -1:
            visited[nxt] = total + d
            DFS(nxt, total + d)

n = int(input())
adj = [[] for _ in range(n+1)]
for _ in range(n-1):
    a, b, d = map(int, input().split())
    adj[a].append((b, d))
    adj[b].append((a, d))

visited = [-1] * (n+1)
visited[1] = 0
DFS(1, 0)

last = visited.index(max(visited))
visited = [-1] * (n+1)
visited[last] = 0
DFS(last, 0)
print(max(visited))