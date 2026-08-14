# Find:

# Total deposits
# Total withdrawals
# Highest transaction
# Most active customers
# Monthly transaction trends
# Customers with unusually large transactions
# Spending by category
import pandas as pd
df = pd.read_csv("bank_transactions.csv")
# print(df["TransactionAmount (INR)"].sum())
# print(df["TransactionAmount (INR)"].max())
# group = df.groupby("CustomerID")
# print(group["TransactionID"].count().to_string())
print(df[df["TransactionAmount (INR)"] > 100000][["CustomerID","TransactionAmount (INR)"]])