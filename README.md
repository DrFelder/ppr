Project Puerto Rico
=

[![Build Status](https://travis-ci.org/DrFelder/ppr.svg?branch=master)](https://travis-ci.org/DrFelder/ppr)

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

You can find the documentation under https://github.com/DrFelder/ppr-documentation
