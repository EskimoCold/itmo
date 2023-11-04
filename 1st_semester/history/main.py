import os

import telebot
from dotenv import load_dotenv


load_dotenv("environments/app.env")

bot = telebot.TeleBot(os.environ["BOT_TOKEN"])