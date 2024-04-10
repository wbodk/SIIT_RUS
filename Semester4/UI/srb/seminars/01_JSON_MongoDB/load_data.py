from pymongo import MongoClient
import json

client = MongoClient('mongodb://admin:admin@147.91.177.197:443')

db = client['testDB']
collection = db['primer01']

json_data=json.load(open('sample1.json'))

#Upisivanje liste dokumenata
collection.insert_many(json_data)

# Citanje i ispis svih dokumenata
cursor = collection.find()
for doc in cursor:
    print(doc)

client.close()