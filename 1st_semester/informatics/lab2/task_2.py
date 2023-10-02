import re


vasya_function = lambda x: 3 * x**2 + 5 

task_test = "20 + 22 = 42"
task_answer = "1205 + 1457 = 5297"

my_tests = [
    "123 + 321 - 456 = 789",
    "-1---123---4-",
    "nothing to correct",
    "2 + 2 = 4",
    "100101010100101",
    "1 0 1 0 1 1 1 0 1 0 0 1 0"
]
    
my_answers = [
    "45392 + 309128 - 623813 = 1867568",
    "-8---45392---53-",
    "nothing to correct",
    "17 + 17 = 53",
    "30060636669181567242120630608",
    "8 5 8 5 8 8 8 5 8 5 5 8 5"
]
    
pattern = r"\d+"

if __name__ == "__main__":
    task_numbers = list(map(int, re.findall(pattern, task_test)))
    numbers_after_vasya = list(map(vasya_function, task_numbers))
    result = re.sub(pattern, "{}", task_test).format(*numbers_after_vasya)

    print(f"task test:   {task_test}\nregex res:   {result}\ntask answer: {task_answer}\n\n")

    print("----------- My tests ------------")
    for i in range(len(my_tests)):
        task_numbers = list(map(int, re.findall(pattern, my_tests[i])))
        numbers_after_vasya = list(map(vasya_function, task_numbers))
        result = re.sub(pattern, "{}", my_tests[i]).format(*numbers_after_vasya)
        print(f"test [{i+1}/{len(my_tests)}]:   {my_tests[i]}\nregex answer: {result}\ntrue answer:  {my_answers[i]}\n")
