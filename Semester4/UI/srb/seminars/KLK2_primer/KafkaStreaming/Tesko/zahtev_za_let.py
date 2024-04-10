import requests
import json

# Poruka treba da bude dictionary 
def send(queue_name, message):
    req = {
        "ime_niza": queue_name,
        "poruka": message
    }
    requests.post("http://localhost:9000/send", data=json.dumps(req))

if __name__ == "__main__":
    
    zahtevi = [
        {
            "id_leta": "LXA25",
            "broj_piste": 2
        },
        {
            "id_leta": "MLT11",
            "broj_piste": 3
        },
        {
            "id_leta": "FTY58",
            "broj_piste": 4
        },
        {
            "id_leta": "LPO01",
            "broj_piste": 3
        }
    ]

    for zahtev in zahtevi:
        send('zahtevi_za_letove', zahtev)