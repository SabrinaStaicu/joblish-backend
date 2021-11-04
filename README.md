<!-- TABLE OF CONTENTS -->

# Joblish Backend

<summary><h2 style="display: inline-block">Table of Contents</h2></summary>
<ol>
<li>
    <a href="#intro">Intro</a>
</li>
<li>
    <a href="#about-the-project">About The Project</a>
    <ul>
        <li><a href="#backend">Backend</a></li>
    </ul>
    <ul>
        <li><a href="#frontend">Frontend</a></li>
    </ul>
    <ul>
        <li><a href="#technologies">Technologies</a></li>
    </ul>
</li>
<li>
    <a href="#getting-started">Getting Started</a>
</li>
</ol>

## Intro
Joblish is a recruitment platform web application that is composed of 3 repositories.
- Two frontend apps
<ol>
    <li>
        <a href="https://github.com/SabrinaStaicu/joblish-frontend">    User</a> 
    </li>
    <li>    
        <a href="https://github.com/SabrinaStaicu/joblish-companies-frontend">    Company</a> 
    </li>
</ol>

- One backend app
<ol>
    <li>
        <a href="https://github.com/SabrinaStaicu/joblish-backend">    Backend</a>
    </li>

</ol>

This repo in particular handles backend side.

All the api requests from both frontend apps access this app to send data or receive data from the database

## About The Project
### Backend
- It is build using the Spring Boot framework, we use annotations for the components
- It uses Lombok to get rid of boilerplate code 
- At startup the database is populated with preset data by implementing the CommandLineRunner class in a component
- We use a Postgresql database and Hibernate for ORM
- It uses Spring Security to secure endpoints
- We check data with Javax Bean Validation
### Frontend
- It is developed using React hooks and and classes
- We use axios for creating http requests and obtaining data from the backend
- The design is made mostly in CSS with minimal inline styling
- For state management we chose Jotai

### Technologies
#### Backend
- Maven
- Spring Boot
- Lombok
- Spring Data JPA
- Spring Security
- Javax Validation
- Java
- Postgresql
- Hibernate
- H2 database
- Mockito
#### Frontend
- React
- JS
- HTML5
- CSS
- SCSS
- Jotai
- NodeJS
- NPM

<!-- GETTING STARTED -->

## Getting started!

### Backend
1. Install Open JDK
Make sure that the latest LTS (Long Term Support) at least Open Java Development Kit (JDK) is installed on your system.
Executing the java --version command in the shell shows at least the latest LTS version number.

2. Install an IDE that supports Maven and Java
This project was developed in IntelliJ IDEA Ultimate

3. Clone the repo to your local machine
You can do that by running the following command in your terminal, make sure you are in the right directory.
```git clone https://github.com/github_username/repo_name.git```

4. Create your own application.properies file in a new resources directory and write
   ```
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.datasource.url=databaseURL
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   spring.jpa.show-sql=false
   ```

5. Run the application from the ```TravelishApplication``` file.

### Frontend 
1. Install NodeJS
If you're using Ubuntu open up your terminal and run this commands:
```
curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash -
sudo apt-get install -y nodejs
```

2. Install Visual Studio Code or other IDE's that support React.

3. Open the project and run "npm install" in the terminal to install all the node modules.

4. After the modules are installed run "npm start" in the terminal and the application will automatically open in your browser.
