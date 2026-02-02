import sys
input = sys.stdin.readline

n = int(input())
arr = []

for _ in range(n):
    arr.append(input().rstrip())

INF = 100000
d = [[INF] * n for _ in range(n)]

# 초기 거리 세팅
for i in range(n):
    for j in range(n):
        if i == j:
            d[i][j] = 0
        else:
            if arr[i][j] == 'Y':
                d[i][j] = 1

for k in range(n):
    for i in range(n):
        if i == k:
            continue
        for j in range(n):
            if d[i][k] + d[k][j] < d[i][j]:
                d[i][j] = d[i][k] + d[k][j]

ans = 0
for i in range(n):
    tmp = 0
    for j in range(n):
        if i == j:
            continue
        
        if d[i][j] <= 2:
            tmp += 1
    if tmp > ans:
        ans = tmp

print(ans)
