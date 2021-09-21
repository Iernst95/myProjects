import Animals
import random

# Create a list for Animal objects
animals = list()

# Print a welcome message
print("Welcome to the animal generator!")
print("This program creates Animal objects")

while True:
    # Ask the user for the animal's type and name
    print("Would you like to create a mammal or bird? ")
    print("1. Mammal")
    print("2. Bird")
    num_check = True
    while(num_check == True):
        try:
            animal_choice = input("Which would you like to create? ")
            if  int(animal_choice) == 1:
                num_check = False
                break
            if  int(animal_choice) == 2:
                num_check = False
                break
        except ValueError:
            print("oops! that wasn't a number!")
        
        print("Please enter 1 or 2")
    
    if(int(animal_choice) == 1):
        animal_type = input("\nWhat type of mammal would you like to create? ")
        animal_name = input("What is the mammal's name? ")
        animal_attribute = input("What color is the mammal's hair? ")
        animal = Animals.mammal(animal_type, animal_name, animal_attribute)
        animals.append(animal)

    if(int(animal_choice) ==2):
        animal_type = input("\nWhat type of bird would you like to create? ")
        animal_name = input("What is the bird's name? ")
        animal_attribute = input("Can the bird fly? ")
        animal = Animals.bird(animal_type, animal_name, animal_attribute)
        animals.append(animal)

    choice = input("\nWould you like to add more animals (y/n)? ")
    if choice != "y":
        break


print("\nAnimal List")


for animal in animals:

    animal_name = animal.get_name()
    animal_type = animal.get_animal_type()
    animal_mood = animal.check_mood()

    print(animal_name, "the", animal_type, "is", animal_mood)
