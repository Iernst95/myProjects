class Person:
    def __init__ (self, name):
        self.__name = name
        self.__money = 100
        self.__animals = []

    def get_name(self):
        return self.__name

    def get_money(self):
        return self.__money

    def get_animals(self):
        return self.__animals

    def add_animal(self, animal):
        self.__animals.append(animal)
        
    def new_day(self):
        self.__money = self.__money + 50
        print("\n")
        print("50$ was added to your balance from work today!")
        print("\n")

    def bought_animal(self, animal_type, money):
        self.__money = self.__money - int(money)
        print("\nThat ", animal_type, "cost ", money,  "$, so you now have ", self.__money, "$\n")
        

        
        
