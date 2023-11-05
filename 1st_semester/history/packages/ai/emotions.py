from warnings import simplefilter

from deepface import DeepFace


simplefilter("ignore")


def get_person_description(img_path: str) -> tuple:
    demographies = DeepFace.analyze(img_path=img_path)[0]
    return demographies


def structurize_for_gpt(demographies) -> str:
    age = demographies["age"]
    gender = demographies["gender"]
    race = demographies["race"]
    emotions = demographies["emotion"]

    strucutre = """
    Age: {}
    Gender: {}
    Race: {}
    Emotions: {}
    """
    
    strucutre = strucutre.format(age, gender, race, emotions)
    
    return strucutre
