from Petstore import Cat,Dog,Turtle,Frog,Parrot
from Customer import Person
import pickle
import random

def main():
    todays_animals = []
    create_inventory(todays_animals)
    print("Welcome to our Pet Store!")
    print("Every day we have new and exciting animals to buy!\n")
    name = input("what is your name?: " )
    try:
        infile = open(name,"rb")
        customer = pickle.load(infile)
        infile.close()
        print("\n")
        print("Hello, ", customer.get_name())
        print("\n")
        customer.new_day()
        input_check(customer, todays_animals)
    except FileNotFoundError:
        print("\nLooks like you're a new customer! \n")
        customer = Person(name)
        print("\n")
        print("Hello, ", customer.get_name())
        print("\n")
        input_check(customer,todays_animals)

def input_check(customer, todays_animals):
    num_check = True
    while(num_check == True):
        try:
            print("What brings you in today?")
            print("1. I want to show you my pets! ")
            print("2. I want to buy a pet!" )
            print("3. Leave")
            choice = int(input("What would you like to do?: "))
          
            if  int(choice) == 1:
                animals = len(customer.get_animals())
                if(animals==0):
                    print("\nIt looks like you dont have any animals yet! \n")
                else:
                    show_animals(customer.get_animals())
                    print("\nIt Looks like you have" , animals, "animals!")
                    print("Very Nice!\n")
                  
                continue
            if  int(choice) == 2:
                print("Here is our current inventory: ")
                show_animals(todays_animals)
                print("\n")
                print("Your current balance is: ", customer.get_money(), "$")
                print("\n")
                shop_animals(customer, todays_animals)
                continue
            if int(choice) == 3:
                outfile = open(customer.get_name(), "wb")
                pickle.dump(customer, outfile)
                outfile.close()
                num_check = False
                break
                    
        except ValueError:
            print("oops! that wasn't an option!")
        
        print("Please enter 1, 2, or 3")
    

def create_inventory(todays_animals):
    amount = random.randint(5,10)
    for i in range(amount):
        animal_num = random.randint(0,4)
        if(animal_num ==0 ):
            animal = Cat()
            todays_animals.append(animal)
        elif(animal_num == 1):
            animal = Dog()
            todays_animals.append(animal)
        elif(animal_num == 2):
            animal = Frog()
            todays_animals.append(animal)
        elif(animal_num == 3):
            animal = Turtle()
            todays_animals.append(animal)
        elif(animal_num == 4):
            animal = Parrot()
            todays_animals.append(animal)
        
        
        
def show_animals(animal_list):
    
    for i in range(len(animal_list)):
        animal = animal_list[i]
        print("Animal #", i)
        print("Name: ", animal.get_name())
        print("Type: ", animal.get_type())
        print("Age: ", animal.get_size())
        print("Color: ", animal.get_color())
        print("Price: ", animal.get_cost(), "$")
        print("\n")

def shop_animals(customer, animal_list):
    animal_amount = len(animal_list)
    print("What animal would you like to buy? " )
    repeat = True
    while(repeat):
        try:
            selection = int(input("Select an animal #: "))
            repeat = False
        except TypeError:
            print("please enter a number! ")
        except ValueError:
            print("please enter a number! ")
        
    if(int(selection) < 0 or int(selection) > animal_amount):
        print("Sorry, we don't have that animal number! ")
        

    else:
        animal = animal_list[selection]
        if(animal.get_cost()>customer.get_money()):
            print("you don't have enough money for this animal!")
        else:
            name = input("What would you like to name your new pet?: ")
            animal.set_name(name)
            customer.add_animal(animal)
            customer.bought_animal(animal.get_type(), animal.get_cost())
            del animal_list[selection]
            
            
        
        
    
    


main()    
