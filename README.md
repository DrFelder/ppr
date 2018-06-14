Project Puerto Rico
=

[![Build Status](https://travis-ci.org/DrFelder/ppr.svg?branch=master)](https://travis-ci.org/DrFelder/ppr)
[![codecov](https://codecov.io/gh/DrFelder/ppr/branch/master/graph/badge.svg)](https://codecov.io/gh/DrFelder/ppr)
[![Maintainability](https://api.codeclimate.com/v1/badges/ed762af37129eacf31b9/maintainability)](https://codeclimate.com/github/DrFelder/ppr/maintainability)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6fdc611c8d1641998c6f93deef49a778)](https://www.codacy.com/app/DrFelder/ppr?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DrFelder/ppr&amp;utm_campaign=Badge_Grade)


This repository contains the famous Project Puerto Rico.

Installation
-

To get started with the database, you need to be able to use MySQL on the computer you are using.
If you can use the MySQL CLI, follow these steps (remember to provide your own passwords and such):

    $ mysql -u root -p
    mysql> CREATE USER 'ppruser'@'localhost' IDENTIFIED BY '$PASSWORD';
    mysql> CREATE DATABASE ppr;
    mysql> GRANT ALL PRIVILEGES ON ppr.* TO 'ppruser'@'localhost';
    mysql> FLUSH PRIVILEGES;
    mysql> exit

Now you can use the database via the ppruser. 
To fill the database with tables and such, simply use the [MySQL structure script](structure.sql) provided by the repository.
If you want to fill the database with testing data, you can use the [MySQL filler script](filler.sql) provided.

After you have created a database and a user for it, you can start modifying the [application properties](src/main/resources/application.properties).
You can follow along using the guidelines below:

    security.signing-key=
Add a signing key here for the authentication

    security.security-realm=PPR Realm
Add a realm for the authentication

    security.jwt.client-id=
    security.jwt.client-secret={noop}
Change these values for the basic authentication. Remember to also change the values inside the [Vuex Store](frontend/src/store.js).

    security.jwt.resource-ids=
You can set a resource id if you want to.

    ppr.database.username=
    ppr.database.password=
    ppr.database.url=
Change the database credentials according to your needs.


To install the application, you will need to run a bunch of gradle tasks first (you can find the necessary ones below, but you are free to take a look at our [travis.yml](.travis.yml)):

    $ ./gradlew npmSetup --console=plain
    $ ./gradlew npmInstall --console=plain
    $ ./gradlew nodeSetup --console=plain
    $ ./gradlew npm_run_build --console=plain
    $ ./gradlew clean assemble --console=plain
    
After that, you will find the built frontend inside [the fronted build folder](build/frontend/dist).
You will need to serve these files over a web server like nginx or apache.

The Java Project will be inside [the build folder](build/libs).
The war file that is being created can then be deployed to a Java Webcontainer (e.g. Tomcat, glassfish jetty).

To get an idea on how to deploy the application, you can take a look at our [travis.yml](.travis.yml), where we use rsync to copy the files.

Development
-

If you want to collaborate on the project you can follow along to get it set up ready for development:

You will need npm and node to run the project locally! 

You will need to open the project inside your favorite IDE and let gradle download the dependencies the project has.
After that, you can use the following commands to get the frontend started:

    $ cd frontend
    $ npm install
    $ npm run dev
    
The development server will now run on localhost.
To start the backend, you can use the following gradle task:

    $ ./gradlew bootRun

You can run the tests using the following task:

    $ ./gradlew check
    
Remember that you need to have to fill up your database first via the [MySQL filler script](filler.sql)!

You can find the documentation under https://github.com/DrFelder/ppr-documentation
