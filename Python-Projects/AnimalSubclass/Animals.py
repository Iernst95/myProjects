import random

class Animal:
    def __init__(self, animal_type, animal_name):
        self.__animal_type = animal_type
        self.__name = animal_name

        random_number = random.randint(1, 3)

        if random_number == 1:
            self.__mood = "happy"
        elif random_number == 2:
            self.__mood = "hungry"           
        elif random_number == 3:
            self.__mood = "sleepy"
        
    def get_animal_type(self):
        return self.__animal_type

    def get_name(self):
        return self.__name

    def check_mood(self):
        return self.__mood

class mammal(Animal):
    def __init__(self, mammal_type, mammal_name,hair_color):
        self.__hair_color = hair_color
        super().__init__(mammal_type, mammal_name)

    def get_hair_color(self):
        return self.__hair_color


class bird(Animal):
    def __init__(self, bird_type, bird_name, can_fly):
        self.__can_fly = can_fly
        super().__init__(bird_type,bird_name)

    def get_can_fly(self):
        return self.__can_fly
        
        
    
        

    
