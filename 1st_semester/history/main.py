import os
from string import ascii_letters, digits
from random import choice
from warnings import simplefilter

import telebot
import openai
from loguru import logger
from dotenv import load_dotenv

from packages.bot.messages import messages
from packages.ai.emotions import get_person_description, structurize_for_gpt
from packages.ai.gpt import request_promt, GPT_PROMT


simplefilter("ignore")

load_dotenv("environments/app.env")

bot = telebot.TeleBot(os.environ["BOT_TOKEN"])
openai.api_key = os.environ["OPENAI_TOKEN"]


@bot.message_handler(commands=["start"])
def start_command(message):
    bot.send_message(message.chat.id, messages["start"])
    

@bot.message_handler(commands=["help"])
def start_command(message):
    bot.send_message(message.chat.id, messages["help"])
    

@bot.message_handler(content_types=['photo'])
def photo(message):
    file_info = bot.get_file(message.photo[-1].file_id)
    downloaded_file = bot.download_file(file_info.file_path)
    
    filename = "".join([choice(ascii_letters + digits) for _ in range(16)])
    
    with open(f"photos/{filename}.jpg", 'wb') as new_file:
        new_file.write(downloaded_file)
        
    reply_message = bot.reply_to(message, messages["got_photo"])
    
    try:
        demographies = get_person_description(f"photos/{filename}.jpg")
        
    except ValueError:
        bot.send_message(message.chat.id, messages["no_face"])
    
    else:
        age = demographies["age"]
        gender = demographies["dominant_gender"]
        race = demographies["dominant_race"]
        
        angry = round(demographies["emotion"]["angry"], 2)
        disgust = round(demographies["emotion"]["disgust"], 2)
        fear = round(demographies["emotion"]["fear"], 2)
        happy = round(demographies["emotion"]["happy"], 2)
        sad = round(demographies["emotion"]["sad"], 2)
        surprise = round(demographies["emotion"]["surprise"], 2)
        neutral = round(demographies["emotion"]["neutral"], 2)
        
        reply_message = bot.edit_message_text(messages["extracted_emotions"].format(age, gender, race, angry, disgust, fear, happy, sad, surprise, neutral),
                            message.chat.id, reply_message.message_id)
        
        user_message = structurize_for_gpt(demographies)
        gpt_answer = request_promt(GPT_PROMT, user_message)
        
        bot.edit_message_text(messages["gpt_answer"].format(gpt_answer),
                            message.chat.id, reply_message.message_id)


if __name__ == "__main__":
    logger.info("Bot started")
    
    bot.polling()
