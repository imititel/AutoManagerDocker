AutoManager

Asigură-te că proiectul este curat și compilat corect
Execută următoarea comandă pentru a curăța și recompila proiectul:

mvn clean install

Construirea și rularea proiectului Docker
Construiește imaginea Docker:
În directorul rădăcină al proiectului (unde se află Dockerfile), rulează următoarea comandă pentru a construi:

Ruleaza imaginea Docker:
docker build -t producing-web-service .
Rulează containerul Docker:
Rulează containerul Docker, mapând portul 8080 al containerului la portul 8080 al mașinii tale locale:

docker run --network my_network -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/proiect_sos -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=your_password -p 8080:8080 producing-web-service

Testarea endpoint-urilor API REST
Acum că containerul Docker este rulant și aplicația este disponibilă pe portul 8080, poți folosi comenzi curl pentru a testa API-urile REST.

Verificarea rutei și testarea cu comenzi cURL și jq
GET All Vehicles
curl http://localhost:8080/vehicles/all | jq
POST (Register a Vehicle)
curl -X POST -H "Content-Type: application/json" -d '{"vin":"1HGBH41JXMN109186","make":"Honda","model":"Civic","year":2022,"owner":"Alice"}' http://localhost:8080/vehicles/register | jq
PUT (Update a Vehicle)
curl -X PUT -H "Content-Type: application/json" -d '{"make":"Honda","model":"Civic","year":2023,"owner":"Bob"}' http://localhost:8080/vehicles/update/1HGBH41JXMN109186 | jq
DELETE (Delete a Vehicle)
curl -X DELETE http://localhost:8080/vehicles/delete/1HGBH41JXMN109186 | jq
GET (Get Vehicle by VIN)
curl http://localhost:8080/vehicles/1HGBH41JXMN109186 | jq

Pentru a verifica dacă aplicația se conectează corect la baza de date MySQL, asigură-te că:

Baza de date MySQL este rulantă și accesibilă la adresa specificată în application.properties.
 Poți rula MySQL într-un container Docker folosind următoarea comandă:

docker stop mysql-db
docker rm mysql-db
Crearea unui Nou Container MySQL:

docker run --network my_network --name mysql-db -e MYSQL_ROOT_PASSWORD=your_password -e MYSQL_DATABASE=proiect_sos -p 3306:3306 -it mysql:latest

Conectează-te la containerul MySQL folosind comanda următoare:

docker exec -it mysql-db mysql -u root -p
Introdu parola root când ți se solicită.

Verificarea Utilizatorilor MySQL și a Permisiunilor
Verificarea Utilizatorilor și a Permisiunilor:

Odată ce ești conectat la MySQL, poți verifica utilizatorii și permisiunile:

SELECT user, host FROM mysql.user;
Crearea și Configurarea Tabelului vehicles
Crearea Tabelului vehicles:

Crează baza de date și tabelul necesar:

CREATE DATABASE IF NOT EXISTS proiect_sos;

USE proiect_sos;

CREATE TABLE vehicles (
    vin VARCHAR(17) PRIMARY KEY,
    make VARCHAR(255),
    model VARCHAR(255),
    year INT,
    owner VARCHAR(255)
);
