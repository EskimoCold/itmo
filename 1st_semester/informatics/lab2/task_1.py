import re


# :</

tests = [
    "smile face - :) my isu face - :</",
    "just nothing.",
    ":/()<>:</::()/.,.?.:</:</",
    ":<</ ::<<// :::</// :>/ :</",
    "':</' * 5 = ':</:</:</:</:</'"
]

answers = [
    1,
    0,
    3,
    2,
    6
]
    
pattern = r":</"

if __name__ == "__main__":
    for i in range(len(tests)):
        count = len(re.findall(pattern, tests[i]))
        
        print(f"test [{i+1}/{len(tests)}]: {tests[i]}\nregex answer: {count}\ntrue answer:  {answers[i]}\n")
