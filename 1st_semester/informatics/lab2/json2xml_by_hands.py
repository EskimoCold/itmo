from typing import TextIO, NoReturn


F1_NAME = "schedule.json"
F2_NAME = "schedule.xml"


def convert(f1: TextIO, f2: TextIO) -> NoReturn:
    pass


if __name__ == "__main__":
    f1 = open(F1_NAME, "r")
    f2 = open(F2_NAME, "w")

    try:
        convert(f1, f2)

    except Exception as e:
        print(f"An error occured: {e}")

    else:
        print("Done!")
