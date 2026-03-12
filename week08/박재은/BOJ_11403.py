import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

def DFS(num):
    for i in graph[num]:
        if visited[i] == 0:
            visited[i] = 1
            DFS(i)

n = int(input())
graph = [[] for _ in range(n)]
for i in range(n):
    tmp = list(map(int, input().split()))
    for j in range(n):
        if tmp[j] == 1:
            graph[i].append(j)

for i in range(n):
    visited = [0 for _ in range(n)]
    DFS(i)
    print(*visited)