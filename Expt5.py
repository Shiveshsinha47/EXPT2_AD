# STEP 2 - Sentiment Function
from textblob import TextBlob

def analyze_sentiment(text):
    try:
        blob = TextBlob(text)
        polarity = blob.sentiment.polarity

        if polarity > 0.1:
            sentiment_category = "Positive"
        elif polarity < -0.1:
            sentiment_category = "Negative"
        else:
            sentiment_category = "Neutral"

        return {
            "text": text,
            "polarity": round(polarity, 4),
            "sentiment": sentiment_category
        }

    except Exception as e:
        return {"text": text, "error": str(e)}


print("✓ Sentiment analysis function created")

# Test
test_texts = [
    "I love this!",
    "This is awful",
    "The sky is blue"
]

print("\nTesting sentiment analysis:\n")
for text in test_texts:
    result = analyze_sentiment(text)
    print(f"Text: '{result['text']}'")
    print(f" Polarity: {result.get('polarity')}")
    print(f" Sentiment: {result.get('sentiment')}")
    print()

print("Shivesh Prateek Sinha-2330047")


# STEP 3 - DataFrame
import pandas as pd

sample_tweets = [
    {"text": "I absolutely love this new product! It's amazing!", "author": "user1"},
    {"text": "This is terrible. Worst experience ever.", "author": "user2"},
    {"text": "The weather is nice today.", "author": "user3"},
]

tweets_df = pd.DataFrame(sample_tweets)

print("✓ Sample tweets dataset created")
print(f"Total tweets: {len(tweets_df)}")
print(tweets_df.head())


# STEP 4 - Apply Sentiment
sentiment_results = []

for _, row in tweets_df.iterrows():
    sentiment_data = analyze_sentiment(row['text'])
    sentiment_data['author'] = row['author']
    sentiment_results.append(sentiment_data)

results_df = pd.DataFrame(sentiment_results)

print("\nSentiment Analysis Completed")
print(results_df)


# STEP 5 - FastAPI Setup
from fastapi import FastAPI
from pydantic import BaseModel
from typing import List, Optional

app = FastAPI(title="Sentiment API")

class TweetInput(BaseModel):
    text: str
    author: Optional[str] = "Anonymous"

class SentimentResult(BaseModel):
    text: str
    author: str
    sentiment: str
    polarity: float

class BulkAnalysisResponse(BaseModel):
    total_tweets: int
    results: List[SentimentResult]
    sentiment_distribution: dict


# STEP 6 - API Endpoint
@app.post("/analyze_tweets/", response_model=BulkAnalysisResponse)
def analyze_tweets(tweets_input: List[TweetInput]):
    results = []

    for tweet in tweets_input:
        sentiment_data = analyze_sentiment(tweet.text)

        result = SentimentResult(
            text=sentiment_data['text'],
            author=tweet.author,
            sentiment=sentiment_data['sentiment'],
            polarity=sentiment_data['polarity']
        )

        results.append(result)

    sentiments = [r.sentiment for r in results]

    sentiment_distribution = {
        "Positive": sentiments.count("Positive"),
        "Negative": sentiments.count("Negative"),
        "Neutral": sentiments.count("Neutral")
    }

    return BulkAnalysisResponse(
        total_tweets=len(results),
        results=results,
        sentiment_distribution=sentiment_distribution
    )


# STEP 7 - Run Server
if __name__ == "__main__":
    import uvicorn

    print("\nServer running at http://localhost:8000")
    uvicorn.run(app, host="0.0.0.0", port=8000)