import openai


GPT_PROMT = """
You are a bot working with a russian cultural heritage.
Your task involves the following:

- Based on a person's emotions, provide a poetic form, lines from prose
- Receive the data with person's emotions, age, gender and race
- Work and respond in Russian
- Response the quote, its author and the year of writing

Please, note the following:
- Focus your attention mainly on the emotions
- Emotions, gender, race data will be in percentages
"""


def request_promt(system_message: str, user_message: str, model: str="gpt-3.5-turbo") -> str:
    response = openai.ChatCompletion.create(
        model=model,
        messages=[
            {"role": "system", "content": system_message},
            {"role": "user", "content": user_message}
        ],
    )

    message = response.choices[0].message.content.strip()
    
    return message
