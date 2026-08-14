import pandas as pd
df = pd.read_csv("Pokemon.csv")
# 1. Drop irrelevant columns
df = df.drop(columns=["Legendary"]) # returns a new dataFrame
# 2 Handle Missing Data
# df = df.dropna(subset=["Type 2"])
df = df.fillna({"Type 2": "None"})
# 3 Fixing inconsistent values
df["Type 1"] = df["Type 1"].replace({"Grass" : "GRASS"})
# 4 Stardadize Text
df["Name"] = df["Name"].str.lower()
# 5 Fix Data Types
df["HP"] = df["HP"].astype(float)
df = df.drop_duplicates()
print(df.to_string())