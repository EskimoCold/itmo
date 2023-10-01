from typing import Union, List


def process_errors(l: List[int]) -> Union[None, List[str]]:
    """
    check a code for errors

    Args:
        l (List[int]) 

    Returns:
        Union[None, List[str]]
    """

    names = ["r1", "r2", "i1", "r3", "i2", "i3", "i4"]

    table = {
        "001": 3,
        "010": 1,
        "011": 5,
        "100": 0,
        "101": 4,
        "110": 2,
        '111': 6
    }
    
    syndromes = [l[0] ^ l[2] ^ l[4] ^ l[6],
                 l[1] ^ l[2] ^ l[5] ^ l[6],
                 l[3] ^ l[4] ^ l[5] ^ l[6]]
    
    if not any(syndromes):
        return None
    
    syndromes = "".join(list(map(str, syndromes)))
    
    place = names[table[syndromes]]
    bit = l[table[syndromes]]
    fixed = l.copy()
    fixed[table[syndromes]] = (bit + 1) % 2
    fixed = "".join(list(map(str, fixed)))
    
    return place, bit, fixed
    

if __name__ == "__main__":
    s = input("Enter your code:\n\n>> ")
    
    try:
        l = list(map(int, s.replace(" ", "")))
        
    except ValueError as e:
        print(f"{e}\nInput must contain only integers")
        
    else:
        if len(l) != 7:
            print("length != 7")

        else:
            errors = process_errors(l)
            
            if errors is None:
                print("Correct!")

            else:
                place, bit, fixed = errors
                print(f"Error detected!\n\nPlace: {place}\nBit: {bit}\nFixed input: {fixed}")
