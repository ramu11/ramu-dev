
nums = [1, 2, 3, 4, 5]

# Break
'''
for num in nums:
    if num ==3:
       print('Found!')
       break
    print(num)

output:1
       2
       Found!
'''

# continue
'''
for num in nums:
    if num ==3:
       print('Found!')
       continue
    print(num)

output: 1
        2
        Found!
        4
        5
'''

'''
for num in nums:
    for letter in 'abc':
        print(num, letter)
'''

# using range
'''
for i in range(10):
    print(i) # 0 ....9

for i in range(1, 11):
    print(i) # 1 .... 10
'''

x = 0
while x < 10:
    if x == 5:
        break  
    print(x)
    x+=1
     
