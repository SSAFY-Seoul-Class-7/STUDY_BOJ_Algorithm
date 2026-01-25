# 설탕 배달
n = int(input())
ans = 0
if n % 5 == 0:
    ans = n // 5
else:
    while n >= 0:
        n -= 3
        ans += 1
        if n % 5 == 0:
            ans += (n // 5)
            break
    else:
        ans = -1
print(ans)