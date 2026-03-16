# 두 수의 합
# n: 수열의 크기
# arr: 수열
# x: 두 수의 합
# output: x가 되는 쌍의 수

n = int(input())
arr = list(map(int, input().split()))
arr.sort()
x = int(input())

left = 0
right = n-1
answer = 0

while left < right:
    if arr[left] + arr[right] == x:
        answer += 1
        left += 1
        right -= 1
    elif arr[left] + arr[right] < x:
        left += 1
    else:
        right -= 1

print(answer)