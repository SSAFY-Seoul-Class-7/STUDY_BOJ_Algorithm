# 9465 스티커
# DP <- 알고리즘 접근을 잘못해서 확인 후 다시 접근
# dp[i][j]: i번째 열까지 스티커를 뗀 경우 최댓값
# j는 0(안 뗀 경우), 1(0행 뗀 경우), 2(1행 뗀 경우)

T = int(input())

for _ in range(T):
    n = int(input())
    graph = [list(map(int, input().split())) for _ in range(2)]

    dp = [[0]*3 for _ in range(n)]
    dp[0][1] = graph[0][0]
    dp[0][2] = graph[1][0]

    for x in range(1, n):
        dp[x][0] = max(dp[x-1][0], dp[x-1][1], dp[x-1][2])
        dp[x][1] = max(dp[x-1][0], dp[x-1][2]) + graph[0][x]
        dp[x][2] = max(dp[x-1][0], dp[x-1][1]) + graph[1][x]

    print(max(dp[n-1]))
    