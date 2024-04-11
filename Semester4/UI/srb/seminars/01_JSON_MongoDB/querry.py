from pymongo.mongo_client import MongoClient
from bson.objectid import ObjectId
import pprint

uri = 'mongodb+srv://admin:Krutit_Vertit666@test.vvzonar.mongodb.net/?retryWrites=true&w=majority&appName=Test'

client = MongoClient(uri)

db = client.get_database("test")

collection = db.get_collection("test")

maleAvgAge = 0
femaleAvgAge = 0



client.close()