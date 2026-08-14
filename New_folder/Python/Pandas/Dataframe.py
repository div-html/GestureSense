# Dataframe is a data structure in a tabular format
import pandas as pd
data = {"Name" : ["Spongebob", "Patrick", "Squidward"],
        "Age" : [30, 35, 50]} # Name acts as the Column Header 
df=pd.DataFrame(data, index=["Character 1", "Character 2", "Character 3"])
print(df.loc["Character 1"])
print(df.iloc[1])
#adding a new column
df["Job"]=["Cook", "Unemployed", "Cashier"]
print(df)
#adding a new row


print(df)
new_rows = pd.DataFrame([{"Name":"Sandy", "Age": 18, "Job":"Engineer"},
                         {"Name":"Divyam", "Age":18,"Job":"JOJO"}],
                         index=["Character 4", "Character 5"])
df = pd.concat([df, new_rows])
print(df)