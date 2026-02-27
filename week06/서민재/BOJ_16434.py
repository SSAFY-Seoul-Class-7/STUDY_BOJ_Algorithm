# 드래곤 앤 던전
# 이분 탐색

N, atk = map(int, input().split())
rooms = []
for _ in range(N):
    t, a, h = map(int, input().split())
    rooms.append((t, a, h))

src, dst = 1, 123456*1_000_000

while src <= dst:
    max_hp = (src+dst)//2

    sub_atk = atk
    cur_hp = max_hp
    flag = True

    for t, a, h in rooms:
        if t == 1:
            cur_hp -= (a*(((h + sub_atk - 1) // sub_atk)-1))

            if cur_hp <= 0: 
                flag = False
                break

        if t == 2:
            cur_hp += h
            sub_atk += a

            if cur_hp > max_hp: cur_hp = max_hp
    
    if flag:
        dst = max_hp -1
    else:
        src = max_hp + 1

print(src)