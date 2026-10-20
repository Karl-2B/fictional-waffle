# Simple Calculator in Python

print("[ Simple Calculator ]")
print("MENU: ")
print("1. Addition")
print("2. Subtraction")
print("3. Multiplication")
print("4. Division")
print("5. Modulo")

# Get user input for operation and numbers
while True:
    operation = input("Select an operation (1-5): ")
    if operation in ['1', '2', '3', '4', '5']:
        break
    else:
        print("Invalid operation selected. Please choose a number between 1 and 5.")

num1 = int(input("Enter first number: "))
num2 = int(input("Enter second number: "))

# Conditional statements to perform the selected operation
if (operation == '1'):
    result = num1 + num2
    print(f"Result: {result}")


elif (operation == '2'):
    result = num1 - num2
    print(f"Result: {result}")


elif (operation == '3'):
    result = num1 * num2
    print(f"Result: {result}")


elif (operation == '4'):
    result = num1 / num2
    print(f"Result: {result}")


elif (operation == '5'): 
    result = num1 % num2
    print(f"Result: {result}")

while True:
    continue_calculation = input("Do you want to perform another calculation? (yes/no): ")
    if continue_calculation.lower() == 'yes':
        exec(open(__file__).read())
        
    elif continue_calculation.lower() == 'no':
        print("Thank you for using the calculator!")
        break
    else:
        print("Invalid input. Please enter 'yes' or 'no'.")