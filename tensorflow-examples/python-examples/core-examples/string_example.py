'''
message = """bobby's world was a good
cartoon in the 1990s"""

print(message)
'''
#length of the string
'''
message = 'Hello World'
print(len(message))
'''
#string indexing
'''
message = 'Hello World'
print(message[0])
print(message[0:5])
print(message[:5])
print(message[6:])
'''
#string utility methods
'''
message = 'Hello World'
print(message.lower())
print(message.count('hello'))
print(message.count('l'))
print(message.find('universe'))
'''
#String replace
'''
message = 'Hello World'
new_message = message.replace('World', 'universe')
print(message)
print(new_message)
'''
#string concatenate
greeting = 'Hello'
name = 'Ramu'
#message = '{}, {}, Welcome!'.format(greeting, name)
message = f'{greeting},{name.upper()},Welcome!'
print(message)
print(help(str))
