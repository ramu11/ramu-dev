import math
import random
import datetime
import calendar
import os

courses = ['History', 'Math', 'Physics', 'CompSci']
random_course = random.choice(courses)
print(random_course)

rads = math.radians(90)
print(math.sin(rads))
print(math.cos(rads))
print(math.tan(rads))

today = datetime.date.today()
print(today)

print(os.getcwd())
print(os.__file__)
