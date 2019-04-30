def hello_func():
    return 'hello function.'

def hello_func(greeting,name):
    return '{},{}, Function called.'.format(greeting,name)

def student_info(*args,**kargs):
    print(args)
    print(kargs)

#student_info('Math','Art',name='John', age=25, location='US')
#print(hello_func('Hi',name= 'ammu'))
#print(hello_func('Hi','ramu'))
#print(hello_func().upper())
#print(len('test'))
