# xcc2

## XCC Microservice

## Installation

1. Install [PGAdmin4](https://www.pgadmin.org/download/)
2. Install [Docker](https://docs.docker.com/get-docker/)

## Build

Run the following command to build the project:

mvn -s settings.xml clean install

## Database Setup
Run the following commands to set up the PostgreSQL database:

docker run --name xcc2-praktikanten -e POSTGRES_PASSWORD=postgres -p 5430:5432 -d postgres

docker exec -it xcc2-praktikanten createdb -U postgres xcc2-praktikanten-db
## PGAdmin Configuration
Host name/address: localhost

Port: 5430

Maintenance database: postgres

Username: postgres

Password: postgres

Database: xcc2-praktikanten-db

## Start Application
In the folders of the xcc microservice, run:

mvn -s absolutePath/settings.xml quarkus:dev
Access Swagger UI
Open the following URL in your browser:

http://localhost:8091/q/swagger-ui/

## Example Campaign
{
  "id": 0,
  "message": "string",
  "vins": [
    {
      "id": 0,
      "vin": "string",
      "campaigns": [
        "string"
      ]
    }
  ],
  "campaignType": "RECALL",
  "startDate":"2021-04-08T15:32:03Z",
  "endDate": "2021-04-08T15:32:03Z",
  "description": "string"
}
## Tasks
The endpoint /backend/hello should return "Hello there".

The endpoint backend/addVinToCampaign should create the Vin if it does not exist.

If a Vin already exists in the DB, it should not be added when calling createVin, instead a 400 BAD_REQUEST should be returned.

A new endpoint should be created that takes a list of Vins and adds them to the DB.

An endpoint that deletes a Vin based on the Vin number (the string) from the DB should be created.

Vins already connected to the campaign should not be able to be added.