import sys
input=sys.stdin.readline

N,K=map(int,input().split())
arr=list(map(int,input().split()))
length=len(arr)
s,e,count,ans, delete=0,0,0,0,0
while e<length:
    if arr[e]%2: #홀수면
        if delete>=K: #삭제횟수 다 써버렸으면
            if arr[s]%2:#s가 원래 홀수 였으면
                s+=1
                delete-=1
            else: #s가 짝수였으면
                while s<e:
                    if arr[s]%2==1:
                        s+=1
                        delete-=1
                        break
                    count-=1
                    s+=1
        else:
            delete+=1
            e+=1
            
    else: #짝수면
        count+=1
        ans=max(ans,count)
        e+=1
        
print(ans)


#제미나이 코드
# import sys
# input = sys.stdin.readline

# N, K = map(int, input().split())
# arr = list(map(int, input().split()))

# count = 0     # 짝수 개수 (현재 길이)
# delete = 0    # 홀수 개수 (삭제한 횟수)
# ans = 0
# s = 0

# # e는 for문으로 무조건 1칸씩 전진 (복잡한 if/else 제거)
# for e in range(N):
#     if arr[e] % 2 == 0: # 짝수 만남
#         count += 1
#     else:               # 홀수 만남
#         delete += 1
    
#     # [중요] 삭제 횟수가 K를 초과하면, 정상 범위가 될 때까지 s를 당김
#     while delete > K:
#         if arr[s] % 2 == 0: # 나가는 놈이 짝수면
#             count -= 1
#         else:               # 나가는 놈이 홀수면
#             delete -= 1
#         s += 1

#     # 여기까지 왔으면 조건(delete <= K)을 만족하는 상태임
#     ans = max(ans, count)

# print(ans)


#시간초과 코드
# ans=-1

# for i in range(len(arr)):
#     if arr[i]%2==1:
#         continue
#     j=i
#     count=K
#     length=0
#     while j<len(arr):
#         if arr[j]%2==0: #짝수면
#             length+=1
#             ans=max(ans,length)
#         else: #홀수면
#             if count==0: #삭제 기회 다써버렸으면
#                 break
#             count-=1            
#         j+=1
# print(ans)            
             
