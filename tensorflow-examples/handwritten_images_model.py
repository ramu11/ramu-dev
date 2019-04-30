#https://pythonprogramming.net/introduction-deep-learning-python-tensorflow-keras/
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt

'''
The x_train data is the "features." In this case, the features are pixel values of the 28x28 images of these digits 0-9. 
The y_train is the label (is it a 0,1,2,3,4,5,6,7,8 or a 9?)
The testing variants of these variables is the "out of sample" examples that we will use. These are examples from our data that we're going to set aside, reserving them for testing the model.
'''
mnist =  tf.keras.datasets.mnist #28x28 images of handwritten digits from o to 9
(x_train, y_train), (x_test, y_test) = mnist.load_data()


#Normalization is a rescaling of the data from the original range so that all values are within the range of 0 and 1
x_train = tf.keras.utils.normalize(x_train, axis=1)
x_test = tf.keras.utils.normalize(x_test, axis=1) 

#Sequential model just means things are going to go in direct order. A feed forward model.
#The neural network(input layer) was flat. So, we need to take this 28x28 image, and make it a flat 1x784
#The activation function is meant to simulate a neuron firing or not
#A densely-connected layer, meaning it's "fully connected," where each node connects to each prior and   subsequent node
model = tf.keras.models.Sequential()
model.add(tf.keras.layers.Flatten()) # input layer
model.add(tf.keras.layers.Dense(128, activation=tf.nn.relu))#hidden layer has 128 units
model.add(tf.keras.layers.Dense(128, activation=tf.nn.relu))#hidden layer has 128 units
model.add(tf.keras.layers.Dense(10, activation=tf.nn.softmax))#output layer has 10 nodes. 1 node per possible number prediction
          
# loss metric. Loss is a calculation of error.
# A neural network doesn't actually attempt to maximize accuracy. It attempts to minimize loss
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])
          
model.fit(x_train, y_train, epochs=3) 

val_loss, val_acc = model.evaluate(x_test, y_test)
print(val_loss, val_acc)

plt.imshow(x_test[6], cmap = plt.cm.binary)
plt.show()

predictions = model.predict([x_test])
print(np.argmax(predictions[6]))



