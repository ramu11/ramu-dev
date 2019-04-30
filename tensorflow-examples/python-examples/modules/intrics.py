#import my_module
import sys
sys.path.append('\home\kkakarla\Development\tensorflow-examples\python-examples')
from my_module import find_index, test

courses = ['History', 'Math', 'Physics', 'CompSci']

#index = my_module.find_index(courses, 'Math')
index = find_index(courses, 'Math')
print(index)
print(test)

print(sys.path)

