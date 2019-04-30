
# comparisions:
# Equal:           ==
# Not Equal:       !=
# Greater Than:    >
# Less Than:       <
# Greater or Equal:>=
# Less or Equal:   <= 
# Object Identity: is

'''
language = 'Java'

if language == 'Python':
    print('Language is python')
elif language == 'Java':
     print('Language is Java')
else:
    print('No match') 

'''

# and
# or
# not

'''
user = 'Admin'
logged_in = Truempty mapping
logged_in_2 = False

if user == 'Admin' and logged_in:
   print('Admin Page')
else:
    print('Bad Cred')

if not logged_in_2:
   print('Please Log In') # Please Log In
else:
    print('Welcome')
'''

'''
a = [1, 2, 3]
b = [1, 2, 3]

print(a == b) # True
print(a is b) # False

b = a
print(id(a))
print(id(b))
print(a is b)
'''
# False Values:
   # False
   # None
   # Zero of any numeric type
   # Any empty sequence. For example,'', (), []
   # Any empty mapping. For example, {}.

#condition = False
#condition = None
#condition = 0
#condition = 10
#condition = []
#condition = ''
condition = {}

if condition:
    print('Evaluated to True') # True(10)
else:
    print('Evaluated to False') # False, False(None), False(0), False([]), False(''), False({}) 



