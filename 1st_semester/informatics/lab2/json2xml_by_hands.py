from typing import TextIO, NoReturn


F1_NAME = "schedule.json"
F2_NAME = "schedule.xml"


def delete_trash(data):
    start_idx = None
    end_idx = None
    
    for i in range(len(data)):
        if data[i] == "[":
            start_idx = i
            break
        
    for i in range(len(data)-1, -1, -1):
        if data[i] == "]":
            end_idx = i + 1
            break
        
    return data[start_idx:end_idx]


def parse_lessons_from_week(data):
    lessons = []
    
    start_idx = None
    end_idx = None
    
    for i in range(len(data)):
        if data[i] == "{":
            start_idx = i
        
        elif data[i] == "}":
            end_idx = i + 1
            lessons.append(data[start_idx:end_idx])
        
    return lessons


def parse_data_from_lesson(data):
    d = {}
    
    splitted = data[1:-1].replace(",", "").split('"')
    
    cleaned_list = []
    for el in splitted:
        if el != '' and el != ': ':
            cleaned_list.append(el)
            
    for i in range(0, len(cleaned_list), 2):
        d[cleaned_list[i]] = cleaned_list[i+1]
    
    return d


def parse_json(data):
    data = "".join([el.strip() for el in data.splitlines()])
    
    data = data.split("week")
    
    even_week = delete_trash(data[1])
    odd_week = delete_trash(data[2])
    
    even_week_lessons = parse_lessons_from_week(even_week)
    even_week_lessons_parsed = [parse_data_from_lesson(lesson) for lesson in even_week_lessons]
    
    odd_week_lessons = parse_lessons_from_week(odd_week)
    odd_week_lessons_parsed = [parse_data_from_lesson(lesson) for lesson in odd_week_lessons]

    schedule = {
        "even week": even_week_lessons_parsed,
        "odd week": odd_week_lessons_parsed
    }
    
    return schedule
    

def json_to_xml(json):
    xml = "<schedule>\n    <even_week>\n"
    
    for i in range(len(json["even week"])):
        xml += f"        <lesson_{i+1}>\n"
        
        for j in range(len(list(json["even week"][i].keys()))):
            xml += f"            <{list(json['even week'][i].keys())[j].replace(' ', '')}>{list(json['even week'][i].values())[j]}</{list(json['even week'][i].keys())[j].replace(' ', '')}>\n"
            
        xml += f"        </lesson_{i+1}>\n"
    
    xml += "    </even_week>\n"
        
    for i in range(len(json["odd week"])):
        xml += f"        <lesson_{i+1}>\n"
        
        for j in range(len(list(json["odd week"][i].keys()))):
            xml += f"            <{list(json['odd week'][i].keys())[j].replace(' ', '')}>{list(json['odd week'][i].values())[j]}</{list(json['odd week'][i].keys())[j].replace(' ', '')}>\n"
            
        xml += f"        </lesson_{i+1}>\n"
        
    xml += "    </odd_week>\n"
    
    xml += "</schedule>"
    
    return xml


def convert(f1: TextIO, f2: TextIO) -> NoReturn:
    data = f1.read()
    
    json = parse_json(data)
    
    xml = json_to_xml(json)
    
    f2.write(xml)


if __name__ == "__main__":
    f1 = open(F1_NAME, "r", encoding="utf8")
    f2 = open(F2_NAME, "w", encoding="utf8")

    try:
        convert(f1, f2)

    except Exception as e:
        print(f"An error occured: {e}")

    else:
        print("Done!")

    finally:
        f1.close()
        f2.close()
