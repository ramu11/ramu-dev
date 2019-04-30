# File objects

#To print all contents of file
'''
with open('test.txt', 'r') as f:
    for line in f:
    print(line, end='')
'''
#print only first line
'''
with open('test.txt', 'r') as f:
     f_contents = f.readline()
     print(f_contents, end='')
'''
#print only first 100 characters of the file
'''
with open('test.txt', 'r') as f:
     f_contents = f.read(100)
     print(f_contents, end='')

#will read next 100 charcter where it previously left
     f_contents = f.read(100)
     print(f_contents, end='')
'''
#will read whole file in chunks using while loop
'''
with open('test.txt', 'r') as f:
    
      size_to_read = 100
      
      f_contents = f.read(size_to_read)
  
      while len(f_contents) > 0:
          print(f_contents,end='')
          f_contents = f.read(size_to_read)     
'''
#reading from begining using seek method
with open('test.txt', 'r') as f:
     size_to_read = 10

     f_contents = f.read(size_to_read)
     print(f_contents, end='')
     
     f.seek(0)
     f_contents = f.read(size_to_read)
     print(f_contents, end='')
