![](https://i.postimg.cc/TYvp0SXB/commit-Gym-logo.png) 

**Project Overview:**

This project was completed within 12 weeks of Mindera Code Academy "MindSwap" Bootcamp and it's the final backend project, applying all the concepts learned so far. 

We were challenged to choose a realistic project to develop a restful API application and our project provides several features, including fitness tests to assess the user's fitness level, workout plans tailored to the user's goals and preferences, and progress tracking to monitor the user's achievements. In CommitGym users can register and create a profile, as well as search for personal trainers and fitness plans. Personal trainers can also register themselves as users, as well manage their clients and assign fitness plans and workouts.

**Languages and Technologies:**

This project is designed in Java, using Spring Boot framework. Other apps and tools are used such as Docker to run containers, Dbeaver to manage databases, Postman and Swagger to handle API requests.

**How to run the app:**

In order to run this app you will need to follow these steps:

- Run this Docker Compose in order to create the container to run the app:

services:
  postgres:
    container_name: commitGym
    image: postgres
    environment:
      POSTGRES_USER: username
      POSTGRES_PASSWORD: password
    volumes:
      - postgres:/data/commitGym_DB
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped

networks:
  postgres:
    driver: bridge

volumes:
  postgres:
  
- Clone this project from this GitHub repository;
  
- Run the CommitGymApplication class in the Idea;

- Create a database with name "commitGym" in Dbeaver or other similar app;

- Use Postman or Swagger to make API calls to manage database entries.

**Authors:**
- Daniela Spitzer - [@DaniSpitzer](https://github.com/DaniSpitzer) 
- Filipe Brandão - [@filbrandao](https://github.com/filbrandao) 
- João Gil - [@fjohnnyg](https://github.com/fjohnnyg) 

**MindSwap Bootcamp**

MindSwap is a 5 month bootcamp program created by Mindera (Portugal), a software development company, that aims to provide students with hands-on experience in the field of software development and that focuses on teaching  the fundamentals of software development, including programming languages, web development, and software architecture.

The program is designed for individuals who are looking to switch to a career in software development, as well as for those who want to enhance their existing programming skills.

If you wish to know more about it click [here](https://mindswap.academy/) .

