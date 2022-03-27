# fastfoodshop
#postgres enviroment setup
(youtube link: https://www.youtube.com/watch?v=9lq74SafVcw)

    1. open terminal
    2. sudo apt update
        to update libraries, dont forget your password
    3. sudo apt install postgresql postgresql-contrib
    4.  sudo -i -u postgres
        to use with a postgres user
    5. psql
        command line interface
    OR instead of 4-5
    sudo -u postgres psql
    6. \q 
        quit
    7.after psql \conninfo (reply: You are connected to database "postgres" as user "postgres" via socket in "/var/run/postgresql" at port "5432".)
    8. \l 
        list of databases
    9. \c fastfoodshop
        to switch to db
#some more notes on postgres enviroment
    use this link: https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-20-04
    in the end, there should be a db named fastfoodshop user fastfoodshop password fastfoodshop, follow the steps. after this, it is ready to create 
    tables and insert data.

**jdk download command: sudo apt install openjdk-11-jdk-headless
    after that: intellij\file\project structure\sdk\ choose the found jdk folder
    in the end:run the command:readlink -f $(which java)
    the reply should be same with the external libraries in the project folder: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
    to test: javac MainTest.java and java MainTest commands, reply should be: hello hello world

 14-provide-a-gitignore-file
**about gitignore
    if you add a file and dont want to push, right click and add to gitignore, it is added to gitignore and push gitignore
=======
**database and tables setup

    create table products (id serial primary key, name varchar (50) not null, price varchar (50) not null);
    insert into products (name, price) values ('hamburger','10');

    create table customers (id serial primary key, name varchar (50) not null);
    insert into customers (name) values ('kenneth');

    create table orders (id serial primary key, product_id int, customer_id int, foreign key (product_id) references products(id), foreign key (customer_id) references customers(id));
    insert into orders (product_id, customer_id) values (1,1);
main
    
    !!(insert into orders (product_id, customer_id) values (1,2) would give an error, because customer2 doesnt exist.
    some useful commands:
        select * from orders;
        \dt (lists tables)
        drop table orders;

**MAVEN INSTALL: 
    
    sudo apt-get install maven
    after install, check the version: mvn -version

    project standard:

    Apache Maven 3.6.3
    Maven home: /usr/share/maven
    Java version: 11.0.14, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
    Default locale: en_US, platform encoding: UTF-8
    OS name: "linux", version: "5.13.0-35-generic", arch: "amd64", family: "unix"

**RUN PROJECT ON TERMINAL

    cd shop
    mvn package //this builds project, run tests and creates a jar file in target, 
    java -jar target/shop-0.0.1-SNAPSHOT.jar // run the jar file
    ctrl-c //IMPORTANT, kill the process with ctrl-c, otherwise it causes errors

**RUN APP ON TERMINAL

    cd shop
    mvn spring-boot:run //this runs the app on localhost 8080
    mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085 //this runs the app on q specific port; 8085

**PROVIDE LOMBOK

    Go to File > Settings > Plugins
    Click on Installed tab
    Look for Lombok Plugin
        If you can find it is already installed
            Click on box to enable Lombok
        If you cannot find it click on Marketplace tab
            Search for Lombok and click on install
            Click on box to enable Lombok

**JENKINS JENKINS JENKINS JENKINS JENKINS JENKINS

**HOW TO RUN A SQL FILE AND CREATE THE DATABASE

    change to fastfoodshop user
    go to the folder of the sql file
    run psql command
    run \i fastfoodshop.psql
    it drops, re-creates and fills the tables