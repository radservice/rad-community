README
======
For an introduction to RADS, please refer to 
https://docs.rads.purwana.net/RADS+Knowledge+Base

UPGRADING
=========
For details on upgrading from previous releases, please refer to 
https://docs.rads.purwana.net/Upgrade+Guide


Prerequisites:
==============
- Java 8 and above
- MySQL 5.5 and above


Installation for Linux:
=======================
1. Create a new directory (e.g. /opt/rads) and extract the tar.gz bundle into that directory
2. Install the Java Runtime Environment (JRE) or Java Development Kit (JDK) version 8 and above
3. Install MySQL Server version 5.5 and above
4. Create an empty database 'radsdb' in the MySQL server
5. Execute the setup script to create the required database tables: ./setup.sh
6. Execute the bundled Apache Tomcat application server: ./tomcat8.sh run
7. Access the App Center at http://localhost:8080/rad


Installation for Windows:
=========================
1. Create a new folder e.g. C:\RADS and extract the ZIP bundle
2. Install the Java Runtime Environment (JRE) or Java Development Kit (JDK) version 8 and above
3. Install MySQL Server version 5.5 and above
4. Create an empty database 'radsdb' in the MySQL server
5. Populate the radsdb database with the SQL script in data\radsdb-empty.sql
6. Edit rads\app_datasource-default.properties to match your database settings
7. Edit tomcat8-run.bat and change JAVA_HOME to your Java installation directory
8. Run tomcat8-run.bat to start the bundled Apache Tomcat application server
9. Access the App Center at http://localhost:8080/rad
