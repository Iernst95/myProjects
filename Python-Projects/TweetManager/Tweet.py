from datetime import datetime, date, timedelta
import time

#source 1: https://www.programiz.com/python-programming/datetime/current-time

class Tweet:
    def __init__(self, author, text):
        self.__author = author
        self.__text = text
        # the 2 lines below are from source 1
        now = datetime.now().time()
        self.__age = now
        
       

    def get_author(self):
        return self.__author

    def get_text(self):
        return self.__text

    def get_age(self):
        current_time= datetime.now().time()
        time_passed = (datetime.combine(date.min,current_time) -
                       datetime.combine(date.min,self.__age))
        time_passed = str(time_passed).split(":")
        if(int(time_passed[0]) == 0 and int(time_passed[1]) == 0):
            new_time = time_passed[2].split(".")
            translated_time = int(new_time[0])
            translated_time = str(translated_time)
            translated_time = translated_time + "s"
            return translated_time
        elif(int(time_passed[0]) == 0 and int(time_passed[1]) > 0):
            translated_time = int(time_passed[1])
            translated_time = str(translated_time)
            translated_time = translated_time + "m"
            return translated_time
        else:
            translated_time = int(time_passed[0])
            translated_time = str(translated_time)
            translated_time = translated_time + "h"
            return translated_time
            
            
