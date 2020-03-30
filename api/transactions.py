#!/usr/bin/env python
# -*- coding: utf-8 -*-
import json
import random
import time
import requests

#PATH = '/Users/wagnercarvalho/Desktop/projeto/fiap/spring/springFiap/app/src/main/resources/base.txt'
PATH = '/usr/local/api/base.txt'
URL = 'http://localhost:5000/v1/fiap/create-transaction'
headers = {'Content-Type': 'application/json'}

def createPayload(doc):
    jsonRequest = {'user_doc': doc, 'description': "Processado pela FIAP",
                   'amount': float("{0:.2f}".format(random.uniform(10.0, 100.0)))}
    return jsonRequest

def post(doc):
    requests.post(URL, json=createPayload(doc))
    time.sleep(5)

class Job(object):

    def read(self):
        with open(PATH) as fp:
            for line in fp:
                if line[41:48].isdigit():
                    post(line[41:48])

def main():
    time.sleep(100)
    task = Job()
    task.read()

if __name__ == "__main__":
    main()