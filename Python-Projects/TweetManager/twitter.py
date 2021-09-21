import Tweet
import pickle
from datetime import datetime


def main():
    infile = open("tweets","rb")
    tweet_list = pickle.load(infile)
    infile.close()
    menu = True
    while(menu):
        print("Tweet Menu")
        print("----------")
        print("1. Make a Tweet")
        print("2. View Recent Tweets")
        print("3. Search Tweets")
        print("4. Quit")
        print("\n")
        
        while(True):
            try:
                choice = int(input("what would you like to do?:  "))
                
                print("\n")
                if  (choice == 1):
                    make_tweet(tweet_list)
                   
                    break
                if  (choice == 2):
                    view_recent_tweet(tweet_list)
                    break
                if (choice == 3):
                    search_tweets(tweet_list)
                    break
                if (choice == 4):
                    outfile = open("tweets", "wb")
                    pickle.dump(tweet_list, outfile)
                    outfile.close()
                    menu = False
                    break
                else:
                    raise ValueError
                       
            except ValueError:
                print("oops! that wasn't a number or it wasn't on the list!")
        

    
def make_tweet(tweet_list):
    loop = True
    while(loop):
        
        name = str(input("what is your name?: "))
        user_tweet = str(input("What would you like to tweet?: "))
        if ( len(user_tweet) == 0 or len(user_tweet)>140):
            print("Tweets can only be 140 characters max!")
            loop = True
        else:
            loop = False
            
    my_tweet = Tweet.Tweet(name,user_tweet)
    tweet_list.append(my_tweet)
    
    


def view_recent_tweet(tweet_list):
    length = len(tweet_list)
    length = length-1
    for x in range(5):
         print(tweet_list[length-x].get_author())
         print(tweet_list[length-x].get_text())
         print(tweet_list[length-x].get_age())
         print("\n")
         
    

def search_tweets(tweet_list):
    search_list = []
    list_hours = []
    list_minutes = []
    list_seconds = []
    
    if(len(tweet_list)!=0):
        search = input("What word would you like to search for?: ")
        for x in tweet_list:
            if x.get_text().find(search) != -1:
                search_list.append(x)

        if (len(search_list) == 0):
            print("no tweets matched your search")

        search_list.reverse()
        for x in search_list:
            print(x.get_author())
            print(x.get_text())
            print(x.get_age())
            print("\n")

    else:
        print("there are no tweets")


    
main()
