import requests

url = "https://apis-sandbox.fedex.com/815038601593/v1/tcn"

payload = input # 'input' refers to JSON Payload
headers = {
    'Content-Type': "application/json",
    'X-locale': "en_US",
    'Authorization': "Bearer "
    }

response = requests.request("POST", url, data=payload, headers=headers)

print(response.text)