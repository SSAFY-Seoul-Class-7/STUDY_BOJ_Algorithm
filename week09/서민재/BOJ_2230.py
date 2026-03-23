# 수 고르기
# 투 포인터

N, M = map(int, input().split())
nums = [int(input()) for _ in range(N)]

nums.sort()

answer = [float('inf')] * N

for i in range(N):
    left = i
    right = N - 1

    while left < right:
        mid = (left + right) // 2
        if nums[mid] - nums[i] < M:
            left = mid + 1
        else:
            right = mid

    if nums[left] - nums[i] >= M:
        answer[i] = nums[left] - nums[i]

print(min(answer))