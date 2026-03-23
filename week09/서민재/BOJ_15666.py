# N과 M (12)

N, M = map(int, input().split())
nums = list(map(int, input().split()))

nums.sort()
answer = []

def func(path, start):
    global answer

    if len(path) == M:
        if path[:] not in answer:
            print(*path[:])
            answer.append(path[:])
        return
    
    for i in range(start, N):
        path.append(nums[i])
        func(path, i)
        path.pop()

func([], 0)
