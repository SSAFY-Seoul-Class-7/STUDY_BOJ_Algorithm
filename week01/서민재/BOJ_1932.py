# 1932 정수 삼각형
# DP
# dp[i][j]: i번 층에서 [i][j]를 더했을 때의 값

n = int(input())
triangle = []

for i in range(n):
    sub = list(map(int, input().split()))
    triangle.append(sub + [0]*(n-i-1))

dp = [[0] * n for _ in range(n)]
dp[0][0] = triangle[0][0]

for i in range(1, n):
    for j in range(i+1):
        if j == 0:
            dp[i][j] = dp[i-1][j] + triangle[i][j]
        else: 
            dp[i][j] = max(dp[i-1][j] + triangle[i][j], dp[i-1][j-1] + triangle[i][j])

print(max(dp[n-1]))