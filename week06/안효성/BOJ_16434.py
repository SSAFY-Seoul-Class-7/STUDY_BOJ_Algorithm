import sys,math
input=sys.stdin.readline

N,H=map(int,input().split()) #방 갯수, 용사 공격력

arr=[list(map(int,input().split())) for _ in range(N)]
#용사가 미션완료하기 위해 필요한 최대 생명력의 최소를 구하기
#파라매트릭 서치로 ㄱㄱ
left=1
right=10**18
ans=sys.maxsize

while left<=right:
    mid=(left+right)//2
    hp=mid
    h=H
    for room in arr:
        if room[0]==1:
            #for문으로 싸우는거 하면 시간 초과임. 수식으로 용사 체력 얼마 되는지 풀기.
            hits=math.ceil(room[2]/h)
            damage = (hits - 1) * room[1]
            hp-=damage
            if hp<=0:
                left=mid+1
                break
        else: #room[0]==2 인 경우
            h+=room[1]
            hp=min(mid,hp+room[2])
    else:
        right=mid-1
        ans=min(ans,mid)
print(ans)
