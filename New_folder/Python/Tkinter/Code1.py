import tkinter as tk
root = tk.Tk()
#geometry
root.geometry("500x500")#first x and then y
root.title("Registration Form")
#Widgets 
label = tk.Label(root, text="Registration Form", font=('Arial', 18))# first we pass the parent object, its going to be root
#we want to add this label into the window/root so we use gird pack or place
label.pack(padx=20,pady=20)
textbox = tk.Text(root, font=('Arial', 16), height=3)
textbox.pack(padx=10)
button = tk.Button(root, text="Submit", font=('Arial',10))
button.pack(pady=10)

# myEntry = tk.Entry(root) #only one line input type
# myEntry.pack()
# GRID LAYOUT Helpful with multiple buttons and rows
buttonFrame = tk.Frame(root)
buttonFrame.columnconfigure(0,weight=1)#three columns of the grid
buttonFrame.columnconfigure(1,weight=1)
buttonFrame.columnconfigure(2,weight=1)
btn1 = tk.Button(buttonFrame, text="1", font=('Arial',13))#btn1 is inside the buttonFrame thats y we are passing the button frame as the master
btn1.grid(row=0,column=0,sticky=tk.W+tk.E)
btn2 = tk.Button(buttonFrame, text='2', font=('Arial', 13))
btn2.grid(row=0,column=1,sticky=tk.W+tk.E)
btn3 = tk.Button(buttonFrame, text='3', font=('Arial', 13))
btn3.grid(row=0,column=2,sticky=tk.W+tk.E)
buttonFrame.pack(fill='x')#that means its going to stetch
root.mainloop()