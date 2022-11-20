
## Test Assumptions

Test dates are missing month and year, I'm assuming **June 2020** since that is where intervals apply.

Updated **START_DATE** and **END_DATE** database types to long. Those dates do not have a timezone, so I'm assuming **CET (UTC+1/+2)** time.

## Dates

I'm using **ZonedDateTime** at the infrastructure level because I think dealing with dates is part of the test, normally I would just use long epoch timestamps in milliseconds and let the client transform it to whatever it needs, which it's probably what it's going to do anyway.

I'm using **CET** as time zone for dates, so it's easier to compare the values with the data given in the test, 
I would usually just use **UTC** time if dealing with dates. You could change the property `application.date-time.zoneId: "CET"` to `application.date-time.zoneId: "UTC"` and the responses would be in **UTC** time.

Some dates are summer time +02:00 and others are not +01:00. 

## Notes

- Changes **PRICE_LIST** database field to **ID** 
- Tried using **RestControllerAdvice** just to test it, but does not seem to work, probably because I'm using a router function instead controller annotations. I usually use a **DefaultErrorAttributes** GlobalErrorMapper anyway so I used it here.
- **Price** could be a **record**, but I don't usually create entities as records because they should become mutable as the application progresses.
- Flyway doest not directly support R2DBC (https://github.com/flyway/flyway/issues/2502) so I had to add a **RunMigrationsOnStarted** class to start the migrations.