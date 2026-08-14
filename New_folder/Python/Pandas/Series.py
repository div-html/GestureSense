import pandas as pd 
# print(pd.__version__)
# data=[101,102,103,105,109]
# series=pd.Series(data, index = ["A", "B", "C", "D", "E"])
# print(series)
# series.loc["A"] = 104
# print(series.loc["A"])
# print(series.iloc[0])
# ## Filter by Value
# print(series[series >=103])
calories = {"Day 1": 1750, "Day 2": 1200, "Day 3": 2000}
series=pd.Series(calories)
series.loc["Day 3"] +=500
print(series.loc["Day 1"])
print(series.iloc[1])
print(series[series > 2000])
