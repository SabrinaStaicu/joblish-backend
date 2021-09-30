<!-- TABLE OF CONTENTS -->

# Joblish Backend

<summary><h2 style="display: inline-block">Table of Contents</h2></summary>
<ol>
<li>
    <a href="#about-the-project">About The Project</a>
    <ul>
    <li><a href="#built-with">Built With</a></li>
    </ul>
</li>
<li>
    <a href="#getting-started">Getting Started</a>
    <ul>
    <li><a href="#installation">Installation</a></li>
    </ul>
</li>

</ol>

<!-- ABOUT THE PROJECT -->

## About The Project

Joblish is a recruitment platform web application that is composed of 3 repositories.

- Two frontend apps
<ol>
    <li>
        <a href="https://github.com/bogdaniordan/joblish-frontend">User</a> 
    </li>
    <li>    
        <a href="https://github.com/bogdaniordan/joblish-companies-frontend">Company</a> 
    </li>
</ol>

- One backend app
<ol>
    <li>
        <a href="https://github.com/bogdaniordan/joblish-backend">Backend</a>
    </li>

</ol>

This repo in particular handles backend side.

All the api requests from both frontend apps access this app to send data or receive data from the database


### Built With

- Maven
- Spring Boot
- Java
- Postgresql

<!-- GETTING STARTED -->

## Getting Started

To get a local copy up and running follow these simple steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/github_username/repo_name.git
   ```
2. Create your own application.properies file in a new resources directory and write
   ```
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.datasource.url=databaseURL
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   spring.jpa.show-sql=false
   ```
3. Make sure your device can run all the technologies in the build section
