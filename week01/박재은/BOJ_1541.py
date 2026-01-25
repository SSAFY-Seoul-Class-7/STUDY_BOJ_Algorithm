# 잃어버린 괄호
calc = input().rstrip()
sub = calc.split('-')
ans = 0
for i in range(len(sub)):
    tmp = sub[i].split('+')
    tmp_total = 0
    for j in range(len(tmp)):
        tmp_total += int(tmp[j])
    if i == 0:
        ans += tmp_total
        continue
    ans -= tmp_total
print(ans)