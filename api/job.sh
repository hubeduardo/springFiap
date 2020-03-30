#!/bin/sh

echo "********************************************************"
echo "Wait for transaction by script python"
echo "********************************************************"

pip install easy_install pip
pip install python
pip install py-pip
pip install requests

exec python /usr/local/api/transactions.py