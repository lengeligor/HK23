REST API

localhost:8083/hk/api -> vsetky maju toto + zoznam dole

GET /person/{id} -> vrati detail osoby

GET /transaction/person/{id}?monthFilter=june -> vrati transakcie osoby, monthFilter nie je povinny ak ho budes zadavat tak cisla mesiacov 01,02, vratim 20 zaznamov

POST /bot/send -> requestBody = {"message": "tvoja otazka z fe"}

GET /transaction/person/{id}/get-report -> id osoby ako path variable a ako mozny RequestParam analysisDuration ako cislo ktore udava 
pocet mesiacov kolkych sa tyka analyza

GET /learn/get-themes -> temy pre learning section

GET /learn?theme={nazov kapitoly} -> zobrazenie kontentu k teme