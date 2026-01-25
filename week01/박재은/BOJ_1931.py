# 회의실 배정
n = int(input())
meeting = []
for i in range(n):
    s, e = map(int, input().split())
    meeting.append((s, e))
# 회의 종료 시간을 기준으로 오름차순 정렬, 종료 시간이 같다면 시작 시간을 기준으로 오름차순 정렬
meeting.sort(key=lambda x:(x[1], x[0]))
end = meeting[0][1] # 첫번째 회의 끝나는 시간
ans = 1
for i in range(1, n):
    s, e = meeting[i]
    if s >= end:
        ans += 1
        end = e
print(ans)