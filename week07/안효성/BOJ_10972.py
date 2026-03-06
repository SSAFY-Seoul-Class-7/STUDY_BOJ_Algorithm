import sys
input=sys.stdin.readline

N=int(input())
arr=list(map(int,input().split()))

for i in range(N-1,0,-1):
    if arr[i]>arr[i-1]:
        s=sys.maxsize
        idx=N
        for j in range(i,N):
            if arr[i-1]<arr[j]:
                if arr[j]<s:
                    s=arr[j]
                    idx=j
        arr[i-1],arr[idx]=arr[idx],arr[i-1]
        print(*(arr[:i]+arr[:i-1:-1]))
        break
        
        
        
else:
    print(-1)
        



# 2 3 4 1
# 2 4 1 3
# 2 4 3 1
# 3 1 2 4







# #역정렬 되는 순간 찾아가면서, 역정렬 되면 그떄 시작점 바로 전거를
# #그 뒤에 수랑 바꾸고, 나머지 수 정렬해서 출력
# for i in range(N):
#     if arr[i:]!=sorted(arr[i:],reverse=True):
#         continue
    
#     if i==0:
#         print(-1)
#         break
    
#     elif i==N-1:
#         arr[-1],arr[-2]=arr[-2],arr[-1]
#         print(*arr)
#         break
        
#     else:
#         s=arr[i-1]+1
#         for j in range(i,N):
#             if arr[j]==s:
#                 arr[i-1],arr[j]=arr[j],arr[i-1]
#                 break
#         print(*(arr[:i]+sorted(arr[i:])))
#         break
