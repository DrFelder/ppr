Project Puerto Rico
=

[![Build Status](https://travis-ci.org/DrFelder/ppr.svg?branch=master)](https://travis-ci.org/DrFelder/ppr)
[![codecov](https://codecov.io/gh/DrFelder/ppr/branch/master/graph/badge.svg)](https://codecov.io/gh/DrFelder/ppr)
[![Maintainability](https://api.codeclimate.com/v1/badges/ed762af37129eacf31b9/maintainability)](https://codeclimate.com/github/DrFelder/ppr/maintainability)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6fdc611c8d1641998c6f93deef49a778)](https://www.codacy.com/app/DrFelder/ppr?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DrFelder/ppr&amp;utm_campaign=Badge_Grade)


This repository contains the famous Project Puerto Rico.

To get started with the database, you need to be able to use MySQL on the computer you are using.
If you can use the MySQL CLI, follow these steps:

```
$ mysql -u root -p
mysql> CREATE USER 'ppruser'@'localhost' IDENTIFIED BY '$PASSWORD';
mysql> CREATE DATABASE ppr;
mysql> GRANT ALL PRIVILEGES ON ppr.* TO 'ppruser'@'localhost';
mysql> FLUSH PRIVILEGES;
mysql> exit
```

Now you can use the database via the ppruser. To fill the database with tables and such, simply use the [MySQL script](mysqlscript.sql) provided by the repository.

If you want to use the application now, you'll have to specify the access data for your database.
To do that, edit the [configuration file src/main/resources/config/database.properties](src/main/resources/config/database.properties).

You can find the documentation under https://github.com/DrFelder/ppr-documentation
