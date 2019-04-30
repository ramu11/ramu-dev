#read write files
'''
with open('test.txt', 'r') as rf:
    with open('test_copy.txt', 'w') as wf:
         for line in rf:
             wf.write(line)
'''
#read write images
'''
with open('tf.png', 'rb') as rf:
     with open('tf_copy.png', 'wb') as wf:
          for line in rf:
              wf.write(line)
'''
#read write images in chunks
with open('tf.png', 'rb') as rf:
     with open ('tf_copy.png','wb') as wf:
           chunk_size = 4096
           rf_chunk = rf.read(chunk_size)
           while len(rf_chunk) > 0:
                  wf.write(rf_chunk)
                  rf_chunk =  rf.read(chunk_size)

 
