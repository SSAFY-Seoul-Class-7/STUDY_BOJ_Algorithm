# 프린터 큐
from collections import deque

T = int(input())

for _ in range(T):
    N, M = map(int, input().split())
    imp = list(map(int, input().split()))

    q = deque()

    for i in range(N):
        q.append((imp[i], i))

    cnt = 1
    
    while q:
        impo, idx = q.popleft()

        # 뒤에 더 중요한 게 있으면 뒤로
        if any(impo < nxt_imp for nxt_imp, _ in q):
            q.append((impo, idx))
            continue  # cnt 증가 없이 다음 루프

        # 인쇄
        if idx == M:
            print(cnt)
            break
        cnt += 1
