import matplotlib.pyplot as plt
from scipy import stats
x = [1,2,3,4,5,6,7,8,9,10]
y = [2,4,6,8,10,12,14,16,18,20]
slope, intercept, r, p, std_err = stats.linregress(x, y)
def myfunc(x):
    return slope * x + intercept
mymodel = list(map(myfunc, x))
plt.scatter(x, y)
plt.plot(x, mymodel)
plt.xlabel("x")
plt.ylabel("y")
plt.show()
print("Regression Coefficients: ", slope, " and ", intercept)
print("p-value= ",p)
print("R2 Score= ",r)
print("Standard Error= ",std_err)
print("Equation of line:")
print("y=",round(slope,2),"x","+",round(intercept,2),"+",round(std_err,0))
print("For x=11, y=",myfunc(11))

import pandas as pd
df=pd.read_csv("Housing.csv",index_col=None)
print(df)

df.info()

print(df.describe())

#Plot heatmap of correlation matrix
import seaborn as sns

# Convert categorical 'yes'/'no' columns to numerical (1/0)
df_numeric = df.copy()
binary_cols = ['mainroad', 'guestroom', 'basement', 'hotwaterheating', 'airconditioning', 'prefarea']
for col in binary_cols:
    df_numeric[col] = df_numeric[col].apply(lambda x: 1 if x == 'yes' else 0)

correlation_matrix = df_numeric.select_dtypes(include=['number']).corr()
sns.heatmap(data=correlation_matrix, annot=True, linecolor='white', linewidths=0.25)

y=df[['price']]
X=df[['area']]
y

import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, mean_absolute_error
reg = LinearRegression().fit(X_train, y_train)
print("Regression Coefficient=",reg.coef_)
print("Regression Intercept=",reg.intercept_)
print("Mean Absolute Error=",mean_absolute_error(y_train, reg.predict(X_train)))
print("Mean Squared Error=",mean_squared_error(y_train, reg.predict(X_train)))
print("Root Mean Squared Error=",np.sqrt(mean_squared_error(y_train, reg.predict(X_train))))
print("R2 Score=",reg.score(X_train, y_train))

plt.scatter(X_train, y_train,color='black')
plt.plot(X_train, reg.predict(X_train),color='red')
plt.xlabel("x")
plt.ylabel("y")
plt.legend(["Actual Output", "Predicted Output (Regression Line)"], loc="upper left")
plt.show()

