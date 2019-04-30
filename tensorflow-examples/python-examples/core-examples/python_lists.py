#Lists Mutable 
'''
courses = ['History', 'Math', 'Physics', 'compSci']

#print(courses)
print(courses[0])
print(courses[-1]) #last element
print(courses[0:2]) #['History', 'Math']
print(courses[2:]) #['Physics', 'compSci']
'''

'''
courses = ['History', 'Math', 'Physics', 'compSci']

courses.append('Art')
print(courses) #['History', 'Math', 'Physics', 'compSci', 'Art']
'''

'''
courses = ['History', 'Math', 'Physics', 'compSci']

courses.insert(0,'Biology')
print(courses) #['Biology', 'History', 'Math', 'Physics', 'compSci']
'''

'''
courses = ['History', 'Math', 'Physics', 'compSci']
courses_2 = ['Art', 'Education']

#courses.insert(0,courses_2) 
#print(courses) #[['Art', 'Education'], 'History', 'Math', 'Physics', 'compSci']

#courses.extend(courses_2)
#print(courses) #['History', 'Math', 'Physics', 'compSci', 'Art', 'Education']

#courses.remove('Math')
#print(courses) #['History', 'Physics', 'compSci']

#courses.pop()
#print(courses) #['History', 'Math', 'Physics']

#courses.reverse()
#print(courses) #['compSci', 'Physics', 'Math', 'History']

#courses.sort()
#print(courses) #['History', 'Math', 'Physics', 'compSci']
'''

'''
nums = [1, 5, 2, 4, 3]
#nums.sort()
#print(nums) #[1, 2, 3, 4, 5]

#nums.sort(reverse=True)
#print(nums) #[5, 4, 3, 2, 1]

#sorted_nums = sorted(nums)
#print(sorted_nums)

print(min(nums)) #1
print(max(nums)) #5
print(sum(nums)) #15
'''

'''
courses = ['History', 'Math', 'Physics', 'compSci']
#print('Math' in courses) #True

for item in courses:
    print(item)  #prints all items in list line by line 

for index, course in enumerate(courses, start=1):
     print(index, course)

course_str = ' - '.join(courses)
new_list = course_str.split(' - ')
print(course_str) #History - Math - Physics - compSci
print(new_list) #['History', 'Math', 'Physics', 'compSci']
'''

                    
