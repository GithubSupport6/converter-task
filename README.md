# Simple currences converter
## How to start
- git clone
- set or create application.properties:
    - set to spring.datasource.url your database connection string
    - set spring.datasource.username and spring.datasource.password
    - set to cbrf.connection-string URL to xml document (http://www.cbr.ru/scripts/XML_daily.asp)
    - set next properties
      - spring.mvc.view.suffix = .html
      - spring.mvc.view.prefix = /html/
## Routes
 - GET /login = login page
 - GET / = converter page
 - GET /signup = registration page
## API Endpoints:
- POST: /signup = registration
```
{
  username:
  password:
}
```
- GET: /getNames - get list of current valutes
- POST: /convert
```
{
  from: = valute 1 char code
  to: = valute 2 char code
  amount: amount of valute 1
}
```
 - GET: /getHistory
 response example:
 {
  id:	1
  fromCode:	"AMD (Армянских драмов)",
  toCode:	"BYN (Белорусский рубль)",
  amount:	100,
  result:	0.53,
  date:	"2021-12-28",
  course: {	
    value:	15.6414,
    nominal:	100,
    date:	"2021-12-28"
    }
 }
```

