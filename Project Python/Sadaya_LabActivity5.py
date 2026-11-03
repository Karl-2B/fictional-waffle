# Initial Inventory List

fruits = ["Apple", "Banana", "Mango", "Orange"]

# Loop Menu

while True:
    print("\n--- 🍎 FRUIT VENDOR MENU 🍌 ---")
    print("1. View Fruits")
    print("2. Add Fruit")
    print("3. Remove Fruit")
    print("4. Exit")
    
    choice = input("Enter your choice (1-4): ").strip()

# Option 1

    if choice == "1":
        print("\nAvailable Fruits:")
        if not fruits:
            print("Your inventory is empty!")
        else:
            for index, fruit in enumerate(fruits, start=1):
                print(f"{index}. {fruit}")

# Option 2

    elif choice == "2":
        new_fruit = input("Enter the name of the fruit to add: ").strip().title()
        if new_fruit:
            fruits.append(new_fruit)
            print(f"'{new_fruit}' has been added to inventory.")
        else:
            print("Fruit name cannot be empty.")

# Option 3

    elif choice == "3":
        remove_fruit = input("Enter the name of the fruit to remove: ").strip().title()
        if remove_fruit in fruits:
            fruits.remove(remove_fruit)
            print(f"'{remove_fruit}' has been removed from inventory.")
        else:
            print(f"'{remove_fruit}' is not in your inventory.")

# Exit

    elif choice == "4":
        print("Thank you for using Fruit Vendor Inventory! Goodbye!")
        break
        
    else:
        print("Invalid choice! Please enter a number between 1 and 4.")