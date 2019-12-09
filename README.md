# Alpha Cab

## Directories
### **Directory structure with descriptions**
    - Old - Temporary folder until official project is created on netbeans

    	- java - Source code folder for servlets, controllers, models and helper

            - com

                - org

                    - Controllers - Servlet Controllers

                    - Models - Database models (Using an ORM like approach)

                    - Helpers - Custom useful helper functions and classes

    	- webapp - HTML (Views), CSS and JS    

            - views - HTML Pages

            - img - Image assets for webapp

            - uploads - Upload directory

            - static - CSS and JS assets

            - WEB-INF - web.xml folder for servlet mappings


    - alphaCab - Working source code

        - data - sql file to populate database

            - alphacab.sql - db import file


        - src - Java package directories

            - org

                - Controllers - Servlet Controllers

                - Models - Database models

                - Helpers - Random helper functions


        - web - HTML (Views), CSS and Javascript

            - static - css, javascript and webassets (img)

            - views - JSP and HTML view files

            - WEB-INF - Deployment descriptor (web.xml)

### **Complete Directory Tree**
```
.
├── alphaCab
│   ├── build.xml
│   ├── nbproject
│   │   ├── ant-deploy.xml
│   │   ├── build-impl.xml
│   │   ├── genfiles.properties
│   │   ├── project.properties
│   │   └── project.xml
│   ├── src
│   │   ├── conf
│   │   │   └── MANIFEST.MF
│   │   ├── data
│   │   │   └── alphacab.sql
│   │   └── java
│   │       └── com
│   │           └── org
│   │               ├── Controllers
│   │               │   ├── AcceptDemand.java
│   │               │   ├── AddDemand.java
│   │               │   ├── AddUser.java
│   │               │   ├── AdminCancelDemand.java
│   │               │   ├── AdminDashboard.java
│   │               │   ├── AdminDemands.java
│   │               │   ├── AdminJourneys.java
│   │               │   ├── CancelDemand.java
│   │               │   ├── CompleteDemand.java
│   │               │   ├── CreateJourney.java
│   │               │   ├── CustomerDashboard.java
│   │               │   ├── Demands.java
│   │               │   ├── DriverDashboard.java
│   │               │   ├── GenerateReport.java
│   │               │   ├── Login.java
│   │               │   ├── Logout.java
│   │               │   ├── MakeDemand.java
│   │               │   ├── MakeTransaction.java
│   │               │   ├── RegisterAdmin.java
│   │               │   ├── RegisterCustomer.java
│   │               │   ├── RegisterDrivers.java
│   │               │   ├── ViewAcceptedDemand.java
│   │               │   ├── ViewCustomers.java
│   │               │   ├── ViewDashboard.java
│   │               │   ├── ViewDemand.java
│   │               │   ├── ViewDemands.java
│   │               │   ├── ViewDriverJourney.java
│   │               │   ├── ViewDriverJournies.java
│   │               │   ├── ViewDrivers.java
│   │               │   ├── ViewJourney.java
│   │               │   ├── ViewOutstandingDemand.java
│   │               │   ├── ViewOutstandingDemands.java
│   │               │   ├── ViewPendingDemand.java
│   │               │   ├── ViewTransaction.java
│   │               │   ├── ViewTransactions.java
│   │               │   └── ViewUnpaidJournies.java
│   │               ├── Helpers
│   │               │   ├── Configs.java
│   │               │   ├── Crayons.java
│   │               │   ├── Functions.java
│   │               │   ├── Message.java
│   │               │   └── SessionFilter.java
│   │               └── Models
│   │                   ├── Customer.java
│   │                   ├── Db.java
│   │                   ├── Demand.java
│   │                   ├── Driver.java
│   │                   ├── Journey.java
│   │                   ├── Report.java
│   │                   ├── Transaction.java
│   │                   └── User.java
│   └── web
│       ├── index.html
│       ├── index.jsp
│       ├── static
│       │   ├── css
│       │   │   ├── bootstrap.css
│       │   │   ├── bootstrap.css.map
│       │   │   ├── bootstrap-grid.css
│       │   │   ├── bootstrap-grid.css.map
│       │   │   ├── bootstrap-grid.min.css
│       │   │   ├── bootstrap-grid.min.css.map
│       │   │   ├── bootstrap.min.css
│       │   │   ├── bootstrap.min.css.map
│       │   │   ├── bootstrap-reboot.css
│       │   │   ├── bootstrap-reboot.css.map
│       │   │   ├── bootstrap-reboot.min.css
│       │   │   ├── bootstrap-reboot.min.css.map
│       │   │   ├── customCss.css
│       │   │   ├── material.css
│       │   │   ├── material.min.css
│       │   │   └── material.min.css.map
│       │   ├── img
│       │   │   └── background.jpg
│       │   └── js
│       │       ├── axios.min.js
│       │       ├── bootstrap.bundle.js
│       │       ├── bootstrap.bundle.js.map
│       │       ├── bootstrap.bundle.min.js
│       │       ├── bootstrap.bundle.min.js.map
│       │       ├── bootstrap.js
│       │       ├── bootstrap.js.map
│       │       ├── bootstrap.min.js
│       │       ├── bootstrap.min.js.map
│       │       ├── bower.json
│       │       ├── getDetailsForDriver.js
│       │       ├── getDistance.js
│       │       ├── getJourneyDetails.js
│       │       ├── jquery-3.2.1.min.js
│       │       ├── makeOrder.js
│       │       ├── material.js
│       │       ├── material.min.js
│       │       ├── material.min.js.map
│       │       ├── package.json
│       │       └── vue.js
│       ├── views
│       │   ├── admin
│       │   │   ├── admin
│       │   │   │   └── regsiter.jsp
│       │   │   ├── customers
│       │   │   │   ├── customer.jsp
│       │   │   │   └── customers.jsp
│       │   │   ├── demands.jsp
│       │   │   ├── drivers
│       │   │   │   ├── driver.jsp
│       │   │   │   ├── drivers.jsp
│       │   │   │   └── register.jsp
│       │   │   ├── header.jsp
│       │   │   ├── home.html
│       │   │   ├── home.jsp
│       │   │   ├── journeys.jsp
│       │   │   ├── report-generator.jsp
│       │   │   └── transactions
│       │   │       ├── transaction.jsp
│       │   │       └── transactions.jsp
│       │   ├── customer
│       │   │   ├── createJourney.jsp
│       │   │   ├── error.jsp
│       │   │   ├── home.html
│       │   │   ├── make-demand.jsp
│       │   │   ├── make-payment.jsp
│       │   │   ├── register.jsp
│       │   │   ├── view-demand.jsp
│       │   │   ├── view-journey.jsp
│       │   │   └── view-journies.jsp
│       │   ├── driver
│       │   │   ├── error.jsp
│       │   │   ├── home.jsp
│       │   │   ├── view-demand.jsp
│       │   │   ├── view-demands.jsp
│       │   │   ├── view-journey.jsp
│       │   │   └── view-journies.jsp
│       │   ├── login.jsp
│       │   └── tests
│       │       ├── demands-test.jsp
│       │       └── make-payment.jsp
│       └── WEB-INF
│           └── web.xml
├── Old
│   ├── data
│   │   └── Alphacab.sql
│   ├── java
│   │   └── com
│   │       └── org
│   │           ├── Controllers
│   │           │   ├── AdminDashboard.java
│   │           │   ├── CustomerDashboard.java
│   │           │   ├── DriverDashboard.java
│   │           │   └── Login.java
│   │           ├── Helpers
│   │           │   ├── Configs.java
│   │           │   ├── Crayons.java
│   │           │   ├── Functions.java
│   │           │   ├── Message.java
│   │           │   └── SessionFilter.java
│   │           └── Models
│   │               ├── Customer.java
│   │               ├── Db.java
│   │               ├── Demand.java
│   │               ├── Driver.java
│   │               ├── Journey.java
│   │               ├── Transaction.java
│   │               └── User.java
│   └── webapp
│       ├── index.jsp
│       ├── static
│       │   ├── css
│       │   │   ├── material.css
│       │   │   ├── material.min.css
│       │   │   └── material.min.css.map
│       │   └── js
│       │       ├── bower.json
│       │       ├── material.js
│       │       ├── material.min.js
│       │       ├── material.min.js.map
│       │       └── package.json
│       ├── views
│       │   ├── admin
│       │   │   └── home.html
│       │   ├── customer
│       │   │   └── home.html
│       │   ├── driver
│       │   │   └── home.html
│       │   ├── login.html
│       │   └── tests
│       │       └── demands-test.jsp
│       └── WEB-INF
│           └── web.xml
└── README.md

44 directories, 162 files - Generated using tree
```

## DB Configurations

**NOTE:** Feel free to edit src/java/com/org/Models/Db.java

```
Db flavor - JavaDB (Derby)

Db host - localhost

Db port - 1527

Db username - root

Db password - test123

Db name - alphacab

Db Connection URI - "jdbc:derby://localhost:1527/alphacab"
```

## Instructions

**1. Setup database according to the above configurations**

**2. Populate database using the sql file in src/data**

**3. Build and run**

## Some Logins To Get You Started

Customer logins

```
username: evasmith@example.com
password: test123

username: jimhunter@example.com
password: test123
```

Driver logins

```
username: johnsmith@example.com
password: driver123
```

Admin logins

```
username: zain@example.com
password: admin123

username: nasru@example.com
password: admin123
```

## Git Repo Links

- [GitHub](https://github.com/Reciforous/alphaCab)