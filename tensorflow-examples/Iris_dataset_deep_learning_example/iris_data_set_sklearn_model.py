#https://www.youtube.com/watch?v=V23DmbdzMvg
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical

from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn import preprocessing

iris = load_iris()
X = preprocessing.scale(iris['data'])
Y = to_categorical(iris['target'])

#print(X)
#print(Y)

#training data and test data
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.2)

#model
model = Sequential()
model.add(Dense(10, input_dim=4, activation='relu'))
model.add(Dense(10,  activation='relu'))
model.add(Dense(3, activation='softmax'))
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

#fitting the model
model.fit(X_train, Y_train, validation_data=(X_test, Y_test), epochs=200, batch_size=10)
