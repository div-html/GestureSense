import pandas as pd
df=pd.read_csv("beginner_movies.csv")
# Whole Dataframe
# print(df.mean(numeric_only=True))
# print(df.sum(numeric_only=True))
# print(df.min(numeric_only=True))
# print(df.max(numeric_only=True))
# print(df.count())
 #Single Column
# print(df["Duration_Minutes"].mean())
# print(df["Duration_Minutes"].max())

group = df.groupby("Genre")
print(group["Duration_Minutes"].mean())