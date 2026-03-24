
import pandas as pd
import numpy as np
# Creating empty series
ser = pd.Series(dtype='object')
print("Pandas Series: ", ser)
# simple array
data = np.array(['s', 'e', 'r', 'i', 'e', 's'])
ser = pd.Series(data)
print("Pandas Series:\n", ser)
print("Shivesh Prateek Sinha-2330047")


#series using dictionary
import pandas as pd
import numpy as np
# Creating empty series
ser = pd.Series(dtype='object')
print("Pandas Series: ", ser)
# simple dictionary
data={1:'s', 2:'e', 3:'r', 4:'i', 5:'e', 6:'s'}
ser=pd.Series(data)
print("Pandas Series:\n", ser)
print("Shivesh Prateek Sinha-2330047")


#series using scalar values
import pandas as pd
import numpy as np
# Creating empty series
ser = pd.Series(dtype='object')
print("Pandas Series: ", ser)
ser=pd.Series(4, index=[1,2,3,4])
print("Pandas Series:\n", ser)
print("Shivesh Prateek Sinha-2330047")





x=pd.Series([1,2,3],index=['a','b','c'])
print(x.shape)
print(x.size)
print(x.index)
print(x.ndim)
print(x.values)
print(x.dtypes)
print(x.nbytes)
print(x.empty)
print(x.hasnans)
print("Shivesh Prateek Sinha-2330047")


x=pd.Series([1,2,3],index=['a','b','c'])
x
print("Shivesh Prateek Sinha-2330047")


# importing the module
import pandas as pd
# creating a sample dataframe
data = pd.DataFrame({'Brand': ['Maruti', 'Hyundai', 'Tata',
'Mahindra', 'Maruti', 'Hyundai',
'Renault', 'Tata', 'Maruti'],
'Year': [2012, 2014, 2011, 2015, 2012,
2016, 2014, 2018, 2019],
'Kms Driven': [50000, 30000, 60000,
25000, 10000, 46000,
31000, 15000, 12000],
'City': ['Gurgaon', 'Delhi', 'Mumbai',
'Delhi', 'Mumbai', 'Delhi',
'Mumbai', 'Chennai', 'Ghaziabad'],
'Mileage': [28, 27, 25, 26, 28,
29, 24, 21, 24]})
# displaying the DataFrame
display(data)
print("Shivesh Prateek Sinha-2330047")






#Display the first row
data.loc[2]


#Display Brand of first row
data.loc[2,'Year']


#Display 1st, 2nd and 5th rows
data.loc[[0,1,4]]


#Display City of 1st and 2nd row
data.loc[[0,1],'City']


#Display 2nd row to 6th row
data.loc[1:5]


# Select the first row
row = data.iloc[0] 
row


# Select first and third rows by position
rows = data.iloc[[0, 2]]
rows


#Select first and 3rd rows (Second column value only)
d=data.iloc[[0,2],[1]]
d


import pandas as pd
data = {
"calories": [420, 380, 390],
"duration": [50, 40, 45]
}
df = pd.DataFrame(data, index = ["day1", "day2", "day3"])
df


import pandas as pd
df=pd.read_csv("/Iris_data_sample(1).csv - Iris_data_sample(1).csv.csv",index_col=0)
df


#Junk values can be converted to missing values by passing them as a list to the parameter ‘n
import pandas as pd
df=pd.read_csv("Iris_data_sample.csv",index_col=0,na_values=['??','###'])
df


df.info()


df.describe()


df.isnull()


df.notnull()


df1=df.fillna(0)
df1


#Fill NA Forward: Fills missing data with data objects above
df2=df.fillna(method='pad')
df2


#Fill NA Backward: Fills missing data with data objects below
df3=df.fillna(method='backfill')
df3


#Drop missing values
df4=df.dropna()
df4


import matplotlib.pyplot as plt
plt.hist(df4['SepalLengthCm'], color='red',edgecolor='white',bins=5)
plt.title('Histogram of SepalLength')
plt.xlabel('SepalLengthCm')
plt.ylabel('Frequency')


import matplotlib.pyplot as plt
plt.hist(df4['PetalLengthCm'], color='orange',edgecolor='white',bins=5)
plt.title('Histogram of PetalLength')
plt.xlabel('PetalLengthCm')
plt.ylabel('Frequency')


import matplotlib.pyplot as plt
plt.hist(df4['SepalWidthCm'], color='red',edgecolor='white',bins=5)
plt.title('Histogram of SepalWidth')
plt.xlabel('SepalWidthCm')
plt.ylabel('Frequency')


import matplotlib.pyplot as plt
plt.hist(df4['PetalWidthCm'], color='pink',edgecolor='white',bins=5)
plt.title('Histogram of PetalWidth')
plt.xlabel('PetalWidthCm')
plt.ylabel('Frequency')


import matplotlib.pyplot as plt
plt.hist(df4['Species'], color='grey',edgecolor='white',bins=5)
plt.title('Histogram of Species')
plt.xlabel('Species')
plt.ylabel('Frequency')


import matplotlib.pyplot as plt
plt.hist(df4['Species'], color='grey',edgecolor='white',bins=5)
plt.title('Histogram of Species')
plt.xlabel('Species')
plt.ylabel('Frequency')


import seaborn as sns
sns.set()
sns.scatterplot(x='SepalLengthCm',y='Species',data=df4)


sns.scatterplot(x='PetalLengthCm',y='Species',data=df4)


sns.scatterplot(x='SepalLengthCm',y='PetalLengthCm',data=df4)


sns.scatterplot(x='SepalLengthCm',y='PetalLengthCm',data=df4)


sns.scatterplot(x='SepalLengthCm',y='PetalLengthCm',data=df4)


sns.scatterplot(x='SepalLengthCm',y='PetalLengthCm',data=df4)


import seaborn as sns
import matplotlib.pyplot as plt
# Plot heatmap of correlation matrix
sns.heatmap(
    data=df4[['SepalLengthCm', 'SepalWidthCm',
              'PetalLengthCm', 'PetalWidthCm']].corr(),
    annot=True,
    cmap='coolwarm',
    linecolor='white',
    linewidths=0.25,
    cbar=True
)
plt.title("Correlation Heatmap")
plt.show()
print("Shivesh Prateek Sinha")