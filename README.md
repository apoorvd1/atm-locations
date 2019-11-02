# ATM Locators
ATM Locators Service is use to get the location of all the existing ATM

##Overview

`ATM Locator Services` : ATM Locator Services use to get the location of atm
Which includes Address, Type and geolocation

Endpoint which get all the ATM detail is `https://www.ing.nl/api/locator/atms/`

##Build Setup and Accessing the service

```bash 
#clean gradle target
gradlew clean

#build gradle target and generate jar in /build/libs
gradlew build

#build gradle with sonar
gradlew sonarqube
```

###Access ATM Services
ATM Locator Service is running on default port 8080
To access the ATM Locators `http://localhost:8080/atm/api/v1/locations`