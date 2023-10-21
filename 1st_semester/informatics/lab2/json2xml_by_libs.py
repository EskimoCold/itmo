import json
from time import time

from dicttoxml import dicttoxml

from json2xml_by_hands import *


F1_NAME = "schedule.json"
F2_NAME = "schedule.xml"


def convert_by_lib(f1, f2):
    data = f1.read()
        
    xml = dicttoxml(json.loads(data))
    
    f2.write(str(xml))
    

if __name__ == "__main__":
    f1 = open(F1_NAME, "r", encoding="utf8")
    f2 = open(F2_NAME, "w", encoding="utf8")

    try:
        start_time_hands = time()
        convert(f1, f2)
        end_time_hands = time()
                
        f1.close()
        f2.close()
        
        f1 = open(F1_NAME, "r", encoding="utf8")
        f2 = open(F2_NAME, "w", encoding="utf8")

        start_time_lib = time()
        convert_by_lib(f1, f2)
        end_time_lib = time()
        
        hands_time = end_time_hands - start_time_hands
        lib_time = end_time_lib - start_time_lib
        
        print(f"My parser time: {hands_time}")
        print(f"Lib parser time: {lib_time}")

    except Exception as e:
        print(f"An error occured: {e}")

    else:
        print("Done!")

    finally:
        f1.close()
        f2.close()
