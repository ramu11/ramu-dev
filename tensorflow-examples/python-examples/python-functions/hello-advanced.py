def student_info(*args,**kargs):
    print(args)
    print(kargs)

courses = ['Math', 'Art']
info = {'age': 25, 'name': 'John', 'location': 'US'}

student_info(*courses,**info)
#student_info(courses,info)


