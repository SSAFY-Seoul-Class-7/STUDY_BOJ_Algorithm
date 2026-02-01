# 1541 잃어버린 괄호

str = list(input().split('-'))

if '+' in str[0]:
    parts = list(str[0].split('+'))
    res = sum(map(int, parts))
else:
    res = int(str[0])

for s in str[1:]:
    if '+' in s:
        parts = list(s.split('+'))
        res -= sum(map(int, parts))
    else:
        res -= int(s)
    
print(res)