
student = {'name':'Ramu', 'age':25, 'courses':['Math', 'compSci']}

print(student['courses'])
print(student['name'])
#print(student['phone']) # key error 'phone'

print(student.get('name'))
print(student.get('phone')) # none

student['phone'] = '555-5555'
student['name'] = 'kumar'

print(student) #{'name': 'kumar', 'age': 25, 'courses': ['Math', 'compSci'], 'phone': '555-5555'}

student.update({'name':'arun', 'age': 26, 'phone': '555-55555'})
print(student)# {'name': 'arun', 'age': 26, 'courses': ['Math', 'compSci'], 'phone': '555-55555'}

del student['age']
print(student) #{'name': 'arun', 'courses': ['Math', 'compSci'], 'phone': '555-55555'}

print(student.keys()) #dict_keys(['name', 'courses', 'phone'])
print(len(student)) # 3

for key in student:
    print(key)

for key, value in student.items():
    print(key, value)



