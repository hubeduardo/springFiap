#!/usr/bin/env python
# -*- coding: utf-8 -*-
from pymongo import MongoClient
import json

#client = MongoClient("mongodb://fiap-mongodb:27017/fiap")
client = MongoClient("mongodb://localhost/fiap")
db = client.fiap
col = db.user

class Api(object):

    def getMongo(self):
        print 'find mongo'
        resp = col.find().limit(5)
        for data in resp:
            print 'mongo document'
            print data

def main():

    mongoSelect = Api()
    mongoSelect.getMongo()

if __name__ == "__main__":
    main()