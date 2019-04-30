
#Immutable
'''
tuple_1 = ('History', 'Math', 'Physics', 'compSci')
tuple_2 = tuple_1

print(tuple_1)
print(tuple_2)

tuple_1[0] = 'Art'
 
print(tuple_1)
print(tuple_2)
'''

#sets are un-ordered and no duplicates 
'''
cs_courses = {'History', 'Math', 'Physics', 'compSci', 'Math'}
art_courses = {'History', 'Math', 'Art', 'Design', 'Math'}
print(cs_courses)
print(cs_courses.intersection(art_courses)) #{'History', 'Math'}

print(cs_courses.difference(art_courses)) #{'compSci', 'Physics'}

print(cs_courses.union(art_courses)) #{'Art', 'Design', 'Math', 'History', 'compSci', 'Physics'}
'''

# Empty Lists
empty_list = []
empty_list = list()

# Empty Tuples
empty_tuple = ()
empty_tuple = tuple()

# Empty Sets
empty_set = {} # This is not right! It's a dict
empty_set = set()
