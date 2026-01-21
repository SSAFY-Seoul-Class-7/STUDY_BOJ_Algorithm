# 1149 RGB거리
# 순열?
# DP
# dp[i][j]: i번째 집까지 j를 선택했을 때 최소 비용

INF = int(1e9)

N = int(input())
cost = [list(map(int, input().split())) for _ in range(N)]

dp = [[INF]*3 for _ in range(N)]

for i in range(3):
    dp[0][i] = cost[0][i]

for i in range(1, N):
    for j in range(3):
        for k in range(3):
            if j == k: 
                continue
            dp[i][j] = min(dp[i][j], cost[i][j] + dp[i-1][k])

print(min(dp[N-1]))

