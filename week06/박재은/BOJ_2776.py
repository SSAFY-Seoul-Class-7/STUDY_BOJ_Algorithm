# 암기왕
import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

def binary_search(target, s, e):
    if s > e:
        return 0

    mid = (s + e) // 2

    if one[mid] == target:
        return 1
    elif one[mid] < target:
        return binary_search(target, mid + 1, e)
    elif one[mid] > target:
        return binary_search(target, s, mid - 1)


T = int(input())
for _ in range(T):
    n = int(input())
    one = list(map(int, input().split()))
    m = int(input())
    two = list(map(int, input().split()))

    one.sort()
    for t in two:
        print(binary_search(t, 0, n-1))