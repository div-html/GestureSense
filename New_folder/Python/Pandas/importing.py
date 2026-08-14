import pandas as pd
#How to import csv and also json

df=pd.read_csv("beginner_movies.csv", index_col="Title")
print(df["Title"])
print(df.loc["Titanic"])
df1=pd.read_json("beginner_movies.json")
# print(df1.loc["Titanic"])