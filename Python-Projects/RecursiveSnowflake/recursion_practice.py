import random
from turtle import Turtle

def snowflake(turtle, sides,branches,length):
    turtle.color("cyan")
    if branches >0:
        for _ in range(sides):
            turtle.forward(length)
            snowflake(turtle, sides, branches - 1, length // 3)
            turtle.backward(length)
            turtle.left(360/sides)
        
    
    

    
        
def main():
    random.seed()
    ANIMATION_SPEED = 0
    leonardo = Turtle()
    leonardo.speed(ANIMATION_SPEED)
    
    #include the below section to randomize the snowflake
    #sides = random.randint(4,8)
    #branches = random.randint(2,4)
    #length = random.randint(100,300)
    
    snowflake(leonardo,7,3,200)

main()
