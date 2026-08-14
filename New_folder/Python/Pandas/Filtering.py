import pandas as pd
df=pd.read_csv("beginner_movies.csv")
check_IMDB=df[df["IMDB_Rating"]>8]["Title"]
# print(check_IMDB)
print(df[(df["Duration_Minutes"]>120) & (df["IMDB_Rating"]>8.4)]["Title"])