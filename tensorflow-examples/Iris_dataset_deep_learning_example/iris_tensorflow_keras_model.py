
"""
Created on Mon April 22,2019

@author: Ramu 

"""

# Import `tensorflow` and `pandas`
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
import numpy
import pandas as pd

#to_categorical is the fundamental package for scientific computing and Pandas provides easy to use data structures and data analysis tools.#
COLUMN_NAMES = [
        'SepalLength', 
        'SepalWidth',
        'PetalLength', 
        'PetalWidth', 
        'Species'
        ]
# Import training dataset
training_dataset = pd.read_csv('iris_training.csv', names=COLUMN_NAMES, header=0)
train_x = training_dataset.iloc[:, 0:4].values
train_y = training_dataset.iloc[:, 4].values

# Import testing dataset
test_dataset = pd.read_csv('iris_test.csv', names=COLUMN_NAMES, header=0)
test_x = test_dataset.iloc[:, 0:4].values
test_y = test_dataset.iloc[:, 4].values

# Encoding training dataset
encoding_train_y = to_categorical(train_y)

# Encoding testing dataset
encoding_test_y = to_categorical(test_y)

#print(encoding_train_y)

# Creating a model
model = Sequential()
model.add(Dense(10, input_dim=4, activation='relu'))
model.add(Dense(10, activation='relu'))
model.add(Dense(10,  activation='relu'))
model.add(Dense(3, activation='softmax'))

# Compiling model
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

# Training a model
model.fit(train_x, encoding_train_y, epochs=200, batch_size=10)

# Evaluate the model
scores = model.evaluate(test_x, encoding_test_y)
print("\nAccuracy: %.2f%%" % (scores[1]*100))

# serialize model to JSONx
model_json = model.to_json()
with open("model_iris.json", "w") as json_file:
         json_file.write(model_json)
# serialize weights to HDF5
model.save_weights("model_iris.h5")


