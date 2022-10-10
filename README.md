# Virtual power plant

Backend Code Challenge: virtual power plant system for aggregating distributed power sources into a single cloud based energy provider.


## Short description

This is a small spring-boot backend application for managing 
distributed power sources. Yhe only power source currently available is **Battery**.

Application provides two endpoints:\
1. First enpoint provides possibility of adding new batteries in the system. It can be accessed via resource **'/storeBatteries'**.
2. Second enpoint provides possiblity of selecting batteries for range of postcode, and also provides some interesting information related to selected equipment (average and total capacity). Resource for access is **/postcodeSelection/{upperBound}/{lowerBound}**

## Additional comments

Tests defined for this application are divided based on the component where belongs. Since the number of functionalities are small, test is organized based on the default components of the app which have more significant role in the current app logic. One test file reffers to the functionalities within repository, and another within controller. \
In the test procedures designed for testing repo component, every object was Mocked, since that is more clean approach in process of testing, and also because functionalities in all other components relies on the correctness of this part of app.
For testing methods defined in the controller, objects were not mocked, activities over the database were performed, and objects inserted during that were not deleted. Considering app configuration in the application.properties, this also may be considered as ok approach, since database will be recreated on every app start, and after testing is finished, all performed actions will be removed because of that. So, in this case, the side-effect will not be caused. In more serious application, in case when mocking of objects is not applied for some reason, after test is finished, all changes supposed to be removed within code.

## Endpoints for testing
```
curl --location --request POST 'https://jela-test-iofgtay2va-od.a.run.app/storeBatteries' \
--header 'Content-Type: application/json' \
--data-raw '[{
"name": "aw89",
"postcode": 11223,
"wattCapacity": 4563.0
},
{
"name": "yuste",
"postcode": 21300,
"wattCapacity": 1230.0
}]
'
```

```
curl https://jela-test-iofgtay2va-od.a.run.app/postcodeSelection/22000/11000
```
Application is available on [link](https://jela-test-iofgtay2va-od.a.run.app)