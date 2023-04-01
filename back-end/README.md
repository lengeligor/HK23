REST API

localhost:8083/hk/api -> vsetky maju toto + zoznam dole

GET /person/{id} -> vrati detail osoby

GET /transaction/person/{id}?monthFilter=june -> vrati transakcie osoby, monthFilter nie je povinny ak ho budes zadavat tak anglicke nazvy mesiacov malym pismom, ak ho nedas vratim poslednych 20 zaznamov

POST /bot/send -> requestBody = {"message": "tvoja otazka z fe"}