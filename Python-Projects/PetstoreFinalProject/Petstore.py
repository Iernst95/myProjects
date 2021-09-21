import random

class Cat:
    def __init__(self):
        color_var = random.randint(0,4)
        size_var = random.randint(0,2)
        colors = ["Orange", "Black", "Brown", "Grey", "White"]
        sizes = ["Kitten", "Adult", "Senior"]
        self.__animal_type = "Cat"
        self.__name = "No Name Yet"
        self.__size = sizes[size_var]
        self.__color = colors[color_var]
        self.__cost = 80

   
    def get_type(self):
        return self.__animal_type

    def get_name(self):
        return self.__name

    def get_size(self):
        return self.__size

    def get_color(self):
        return self.__color

    def get_cost(self):
        return self.__cost
    
    def set_name(self, name):
        self.__name = name
    
class Dog:
    def __init__(self):
        color_var = random.randint(0,4)
        size_var = random.randint(0,2)
        colors = ["Brown and White ", "Black", "Brown", "Spotted Gray", "White with Black Dots"]
        sizes = ["Puppy", "Teen", "Adult"]
        self.__animal_type = "Dog"
        self.__name = "No Name Yet"
        self.__size = sizes[size_var]
        self.__color = colors[color_var]
        self.__cost = 150

    def get_type(self):
        return self.__animal_type

    def get_name(self):
        return self.__name

    def get_size(self):
        return self.__size

    def get_color(self):
        return self.__color

    def get_cost(self):
        return self.__cost
    
    def set_name(self, name):
        self.__name = name

class Frog:
    def __init__(self):
        color_var = random.randint(0,4)
        size_var = random.randint(0,1)
        colors = ["Orange and Black", "Neon Green", "Yellow", "Purple", "Electric Blue"]
        sizes = ["Baby", "Adult"]
        self.__animal_type = "Frog"
        self.__name = "No Name Yet"
        self.__size = sizes[size_var]
        self.__color = colors[color_var]
        self.__cost = 25

    def get_type(self):
        return self.__animal_type

    def get_name(self):
        return self.__name

    def get_size(self):
        return self.__size

    def get_color(self):
        return self.__color

    def get_cost(self):
        return self.__cost
    
    def set_name(self, name):
        self.__name = name
class Turtle:
    def __init__(self):
        color_var = random.randint(0,1)
        size_var = random.randint(0,2)
        colors = ["Green ", "Brown"]
        sizes = ["0-5 years", "5-20 years", "20-100 years", "100 + years"]
        self.__animal_type = "Turtle"
        self.__name = "No Name Yet"
        self.__size = sizes[size_var]
        self.__color = colors[color_var]
        self.__cost = 75

    def get_type(self):
        return self.__animal_type

    def get_name(self):
        return self.__name

    def get_size(self):
        return self.__size

    def get_color(self):
        return self.__color

    def get_cost(self):
        return self.__cost
    
    def set_name(self, name):
        self.__name = name


class Parrot:
    def __init__(self):
        color_var = random.randint(0,6)
        size_var = random.randint(0,1)
        colors = ["Rainbow", "Teal and yellow", "Purple", "Pink", "Green", "Orange", "White"] 
        sizes = ["Baby", "Adult"]
        self.__animal_type = "Parrot"
        self.__name = "No Name Yet"
        self.__size = sizes[size_var]
        self.__color = colors[color_var]
        self.__cost = 200

    def get_type(self):
        return self.__animal_type

    def get_name(self):
        return self.__name

    def get_size(self):
        return self.__size

    def get_color(self):
        return self.__color

    def get_cost(self):
        return self.__cost
    
    def set_name(self, name):
        self.__name = name
    

