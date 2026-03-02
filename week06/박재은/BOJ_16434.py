# 드래곤 앤 던전
import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline
import math
def check(mid):
    attack = atk
    hp = mid

    for t, a, k in board:
        if t == 1:
            m_cnt = math.ceil(k / attack) # 용을 쓰러트리기 위해 필요한 공격 횟수
            a_cnt = math.ceil(hp / a) # 용사가 버틸 수 있는 공격 횟수
            if m_cnt <= a_cnt:
                hp -= (m_cnt - 1) * a
            else:
                return False
        else:
            attack += a
            hp += k
            if hp > mid:
                hp = mid
    return True

n, atk = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
s, e = 1, 123456*1000000*1000000
res = 0
while s <= e:
    mid = (s + e) // 2
    if check(mid):
        e = mid - 1
        res = mid
    else:
        s = mid + 1
print(res)