# BMI Calculator

while True:
    print("================================")
    print("          BMI Calculator")
    print("================================")
    print("1. Calculate BMI")
    print("2. View BMI Categories")
    print("3. Exit")
    print("================================")

    # Get user input for menu selection
    while True:
        choices = input("Select an option (1-3): ")
        if choices in ['1', '2', '3']:
            break
        else:
            print("Invalid choice. Please select 1, 2, or 3.\n")

    # Conditional statements to handle user selection
    if choices == '1':
        name = input("\nEnter your name: ")
        weight = float(input("Enter your weight in kilograms: "))
        height = float(input("Enter your height in meters: "))

        if height <= 0 or weight <= 0:
            print("Invalid input. Weight and height must be greater than zero.")
        else:
            bmi = weight / (height ** 2)

            print("\n================================")
            print("          BMI Results")
            print("================================")
            print(f"Name: {name}")
            print(f"Weight: {weight} kg")
            print(f"Height: {height} m")
            print(f"BMI: {bmi:.2f}")
            print(f"Category: ", end="")

            if bmi < 18.5:
                print("Underweight")
            elif 18.5 <= bmi < 25:
                print("Normal weight")
            elif 25 <= bmi < 30:
                print("Overweight")
            else:
                print("Obese")

    elif choices == '2':
        print("\nBMI Categories")
        print("Below 18.5   :  Underweight")
        print("18.5 - 24.9  :  Normal weight")
        print("25 - 29.9    :  Overweight")
        print("30 and above :  Obese")

    elif choices == '3':
        print("Thank you for using the BMI Calculator!")
        break

    input("Press Enter to return to the menu...")