
#chatbotA
import PyPDF2
import requests
from bs4 import BeautifulSoup
def read_pdf(file_path):
    text = ""
    with open(file_path, "rb") as file:
        reader = PyPDF2.PdfReader(file)
        for page in reader.pages:
            if page.extract_text():
                text += page.extract_text() + "\n"
    return text
def scrape_website(url):
    headers = {
        "User-Agent": "Mozilla/5.0"
    }
    response = requests.get(url, headers=headers)
    soup = BeautifulSoup(response.text, "html.parser")
    paragraphs = soup.find_all("p")
    text = " ".join([p.get_text() for p in paragraphs])
    return text
def extract_keywords(question):
    stopwords = ["how", "to", "is", "the", "a", "an", "of", "for", "in", "on"]
    words = question.lower().split()
    keywords = [word for word in words if word not in stopwords]
    return keywords
def chatbot_response(question, pdf_text, web_text):
    keywords = extract_keywords(question)
    for line in pdf_text.split("\n"):
        for keyword in keywords:
            if keyword in line.lower():
                return "ChatBot (From PDF):\n" + line.strip()
    for line in web_text.split("."):
        for keyword in keywords:
            if keyword in line.lower():
                return "ChatBot (From Website):\n" + line.strip()

    return "Sorry, no relevant instruction found."
pdf_text = read_pdf("NT-Target Batch - XI.pdf")
web_text = scrape_website("https://www.millerperformancecars.com/store/show-by-car/e36/m5x-s5x-n-a-pistons-detail")
print("Chatbot is ready. Type 'exit' to quit.")
while True:
    user_input = input("You: ")
    if user_input.lower() == "exit":
        print("Bot: Goodbye!")
        break
    response = chatbot_response(user_input, pdf_text, web_text)
    print("Bot:", response)

    #chatbotB
    import os
import requests
from bs4 import BeautifulSoup
import google.generativeai as genai
from pypdf import PdfReader

os.environ["GOOGLE_API_KEY"] = "AIzaSyChi9qLwdjgDGkvvaMWOfOsl7X0YDBQCQU"
genai.configure(api_key=os.getenv("GOOGLE_API_KEY"))

def scrape_website(url):
    try:
        response = requests.get(url)
        soup = BeautifulSoup(response.text, "html.parser")
        paragraphs = soup.find_all("p")
        text = " ".join([p.get_text() for p in paragraphs])
        return text[:5000]
    except:
        return ""

def read_pdf(file_path):
    text = ""
    reader = PdfReader(file_path)
    for page in reader.pages:
        if page.extract_text():
            text += page.extract_text() + "\n"
    return text

def ai_answer(question, manual_text, web_text):
    model = genai.GenerativeModel("gemini-2.5-flash")
    prompt = f"""
You are an assistant.
Use ONLY the following text to answer the question.
If the answer is not present in the text, say:
"This information is not available."

PDF Text:
{manual_text}

Online Text:
{web_text}

Question:
{question}

Answer clearly in simple steps.
"""
    response = model.generate_content(prompt)
    return response.text.strip()

web_text = scrape_website("https://www.millerperformancecars.com/store/show-by-car/e36/m5x-s5x-n-a-pistons-detail")
manual_text = read_pdf("NT-Target Batch - XI.pdf")

print("Help Bot is ready")
print("Type 'exit' to quit.\n")

print("Website text preview:\n", web_text[:500])
print("\nPDF text preview:\n", manual_text[:500])

while True:
    user_input = input("You: ")
    if user_input.lower() == "exit":
        print("Bot: Goodbye!")
        break
    reply = ai_answer(user_input, manual_text, web_text)
    print("\nBot:", reply, "\n")
