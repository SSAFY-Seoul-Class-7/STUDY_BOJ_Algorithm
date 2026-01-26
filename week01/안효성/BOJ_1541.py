import sys
input=sys.stdin.readline

arr=input().rstrip().split("-")
val=0
if arr[0]:
    val=sum(map(int,arr[0].split("+")))

for elem in arr[1:]:
    val-=sum(map(int,elem.split("+")))
    

print(val)
