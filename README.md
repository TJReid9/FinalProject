# FinalProject

# EventTrackerProject

# Overview
## Create A Full Stack App with CRUD implementation of theories taught in class up to this point in a group enviroment. 

# Technologies Used

- MySQL
- MySQL WorkBench
- MAMP
- Gradle
- JDBC
- Java
- Eclipse/Spring Tool Suite
- Git
- GitHub
- Git Branching
- Sublime
- Spring Boot
- JPA
- HTML
- CSS
- Bootstrap
- JSP
- Google
- Figma
- CanBan
- EC2
- Trello
- Google Slides
- JavaScript
- Angular

# Lessons Learned

- Make Packages
- Make Classes
- Make Main
- Make Methods
- Declare and Initilize Variables
- Create for else Statements
- Create do/while Statements
- Create switch Statements
- Used \n, System.err.println, System.out.println, System.out.print and System.out.printf to properly format output
- Passed Varriables to Methods
- Returned Data From Methods to Main
- Create and Instatiate Objects
- Use of private and protected data modifiers
- Use of static and non-static methods
- Use of getters
- Use of setters
- Use of Overrides
- Use of Overloaded Methods
- Use of InstanceOf
- Use of Implementation
- Use of Extends
- Use of Abstract Classes and Methods
- Use of SQL Queries - SELECT Statements, WHERE Clause, Predicates, Functions, and Joins
- Use of Prepared Statements and Statement Parameters
- Use of JPQL
- Use of MySQL Workbench
- Creation and use of Controllers
- Creation and use of DAO and DAO Impl
- Use of HTML, CSS, and Bootstrap techniques
- Use of Model And View
- Use of Request Mapping
- Use of Params
- Use of Composite Tables/Keys
- Use of Hashcode/Equals
- Use of AGILE 
- Use of SCRUM
- Creation of wireframe models
- Creation of canban boards
- Use of Git Branching and Merging
- Creation and Use of Components, Models, and Services in Angular
- Use of ngModel, ngFor, and ngIf
- Use of click events (in JavaScript and Angular)
- Use of Routing in Angular

### Endpoints

//TODO Update to FP endpoints

| HTTP Verb | URI                                        | Request Body               | Response Body            | Status           |
|-----------|--------------------------------------------|----------------------------|--------------------------|------------------|
| GET       | `/api/seasons/{seasonYear}/games`          |                            | List of games by season  | 200              |
| GET       | `/api/games/{gameId}`                      |                            | Single game              | 200 or 404       |
| POST      | `/api/seasons/{seasonYear}/games`          | JSON of new game           | JSON of created game     | 201 or 400       |
| PUT       | `/api/seasons/{seasonYear}/games/{gameId}` | JSON for updating game     | JSON of updated game     | 200, 404, or 400 |
| DELETE    | `/api/games/{gameId}`                      |                            |                          | 204, 404, or 400 |
| GET       | `/api/games/locations`                     |                            | List of locations        | 200              |
| GET       | `/api/games/locations/{locationId}`        |                            | Single location          | 200 or 404       |
| POST      | `/api/games/locations`                     | JSON of new location       | JSON of created location | 201 or 400       |
| PUT       | `/api/games/locations/{locationId}`        | JSON for updating location | JSON of updated location | 200, 404, or 400 |
| DELETE    | `/api/games/locations/{locationId}`        |                            |                          | 204, 404, or 400 |
| GET       | `/api/seasons`                             |                            | List of seasons          | 200              |
| GET       | `/api/seasons/{seasonYear}`                |                            | Single season            | 200 or 404       |
| POST      | `/api/seasons`                             | JSON of new season         | JSON of created season   | 201 or 400       |
| PUT       | `/api/seasons/{seasonYear`                 | JSON for updating season   | JSON of updated season   | 200, 404, or 400 |
| DELETE    | `/api/seasons/{seasonYear}`                |                            |                          | 204, 404, or 400 |


#### JSON

//TODO Update to FP JSON

Season: 
	{
   	"year": 1994,
    "record": "13-0",
    "confChamp": true,
    "natChampAp": true,
    "natChampCoach": true
}

Game:
	{
    "id": 1,
    "gameDate": "1994-08-28",
    "dayOfWeek": "Sunday",
    "homeGame": false,
    "opponent": "(24) West Virginia",
    "oppTeamName": "Mountaineers",
    "oppLogoUrl": "https://cdn.ssref.net/req/202310031/tlogo/ncaa/west-virginia.png",
    "conference": "Big East",
    "win": true,
    "points": 31,
    "oppPoints": 0,
    "televised": true,
    "network": "NBC",
    "bowlGame": false,
    "location": {
        "id": 2,
        "stadium": "Giants Stadium",
        "city": "East Rutherford",
        "state": "New Jersery"
    },
    "season": {
        "year": 1994,
        "record": "13-0",
        "confChamp": true,
        "natChampAp": true,
        "natChampCoach": true
    }
}

Location:
	{
    "id": 1,
    "stadium": "Memorial Stadium",
    "city": "Lincoln",
    "state": "Nebraska"
}

### HTML and JavaScript Front End

* TODO




