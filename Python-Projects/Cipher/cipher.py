do_calculation = True

while(do_calculation):
    
    print("Welcome to the Secret Message Encoder/Decoder")
    
    print("1. Encode a message")
    print("2. Decode a message")
    print("3. Exit")

# the loop below error checks the input
    yes = True
    while(yes):
    
        try:
             answer = int(input("What would you like to do?"))
             yes = False
             
             if answer < 1 or answer > 3:
                 raise ValueError
        except ValueError:
            print("Please enter a number that is on the menu")
            yes = True
            continue
        except TypeError:
            print("Please enter a number that is on the menu")
            yes = True
            continue


    initial = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']
    encoded = ['0','1','2','3','4','5','6','7','8','9','!','@','#','$','%','^','&','*','(',')','-','+','<','>','?','=']
    
    if answer == 1:
        user_string = input("Enter a message to encode: ")
        user_string_list = list(user_string)
        final_string = ""

        for i in range(len(user_string)):
            character = user_string_list[i]
            if character == ' ':
                continue
            index_needed = initial.index(character)
            user_string_list[i] = encoded[index_needed]

        for i in user_string_list:
            final_string += i
        print("Encoded Message: " + final_string)
        
        
    if answer == 2:
        user_string = input("Enter a message to decode: ")
        user_string_list = list(user_string)
        final_string = ""

        for i in range(len(user_string)):
            character = user_string_list[i]
            if character == ' ':
                continue
            index_needed = encoded.index(character)
            user_string_list[i] = initial[index_needed]
            
        for i in user_string_list:
            final_string += i
        print("Encoded Message: " + final_string)
        
    if answer == 3:
        do_calculation = False
    
        
    

