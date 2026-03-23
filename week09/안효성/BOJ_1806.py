N, S = map(int, input().split())
arr = list(map(int, input().split()))

ans = int(1e9)
sum = 0
s = 0

for e in range(N):
    sum += arr[e]
    while sum >= S:
        ans = min(ans, e - s) 
        sum -= arr[s]
        s += 1

if ans == int(1e9):
    print(0)  
else:
    print(ans+1)
