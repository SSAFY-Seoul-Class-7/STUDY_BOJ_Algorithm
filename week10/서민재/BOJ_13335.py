# 트럭
from collections import deque

n, w, L = map(int, input().split())
t = deque(list(map(int, input().split())))

b = deque([0 for _ in range(w)])
time = 0


while b:
    b.popleft()
    time += 1

    if t:
        if sum(b) + t[0] <= L:
            b.append(t.popleft())
        else:
            b.append(0)

print(time)