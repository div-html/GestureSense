import pandas as pd
df=pd.read_csv("beginner_movies.csv")
print(df.tail(3)) # to print the last 3 rows
print(df.shape) # to print the number of rows and columns
print(df.columns.to_list()) # to get all the column names
print(df[["Title","Genre"]])
print(df.iloc[0])