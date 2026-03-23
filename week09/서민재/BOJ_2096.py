# 내려가기
# DP
# min_dp, max_dp

N = int(input())
first_row = list(map(int, input().split()))

min_prev = first_row[:]
max_prev = first_row[:]

for _ in range(1, N):
    row = list(map(int, input().split()))

    min_curr = [0] * 3
    max_curr = [0] * 3

    min_curr[0] = min(min_prev[0], min_prev[1]) + row[0]
    min_curr[1] = min(min_prev[0], min_prev[1], min_prev[2]) + row[1]
    min_curr[2] = min(min_prev[1], min_prev[2]) + row[2]

    max_curr[0] = max(max_prev[0], max_prev[1]) + row[0]
    max_curr[1] = max(max_prev[0], max_prev[1], max_prev[2]) + row[1]
    max_curr[2] = max(max_prev[1], max_prev[2]) + row[2]

    min_prev = min_curr
    max_prev = max_curr

print(max(max_prev), min(min_prev))