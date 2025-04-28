# Fiverr Blog Project

A full-stack blog application built with **Spring Boot (Java)** on the backend and **React** on the frontend. It includes authentication, routing, email sending, and integration with a PostgreSQL database. The project is fully containerized using Docker and Docker Compose.

## 🌐 Live Demo

You can view the live application here:

🔗 **Frontend:** [https://blogsandresearch.netlify.app/](https://blogsandresearch.netlify.app/)

🔐 **Admin Panel:** [https://blogsandresearch.netlify.app/admin](https://blogsandresearch.netlify.app/admin)  
Login credentials:  
`Username:` admin  
`Password:` adminPass

> ⚠️ Please note: The database may take up to **5 minutes** to initialize after first opening the site.

---

## 🚀 Features

- User registration and login
- JWT-based authentication
- Protected routes
- Blog creation and listing
- PostgreSQL integration
- Email sending functionality
- Dockerized setup for easy deployment

## 🛠 Tech Stack

### Backend:
- Java 17
- Spring Boot 3.4.0
- Spring Security
- PostgreSQL
- Maven

### Frontend:
- React
- React Router DOM 6.28.0

### DevOps:
- Docker
- Docker Compose
- Render
- console.neon.tech
- netlify

---

## 📁 Project Structure

```
├── backend (integrated in root)
├── frontend (React UI integrated)
├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── package.json
```

---

## 🔧 Setup Instructions

### ✉️ Email Configuration
To enable email sending, add the following to your `.env` file:

```env
SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=your_email@gmail.com
SPRING_MAIL_PASSWORD=your_email_password
SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true
```

> ✅ You can use an [App Password](https://support.google.com/accounts/answer/185833) for Gmail if 2FA is enabled.


### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/fiverr-blog.git
cd fiverr-blog
```

### 2. Configure Environment Variables
Create a `.env` file in the project root with the following:

```env
DATASOURCE_URL=jdbc:postgresql://localhost:5432/securityApp
DATASOURCE_USER=your_db_user
DATASOURCE_PASSWORD=your_db_password
FRONTEND_URL=http://localhost:3000
```

### 3. Run with Docker
```bash
docker-compose up --build
```

This will start both backend and frontend containers along with a PostgreSQL instance.

---

## 📬 API Endpoints (example)

- `POST /api/auth/register` – Register a new user
- `POST /api/auth/login` – Authenticate user & receive JWT
- `GET /api/posts` – Get all blog posts
- `POST /api/email/send` – Send an email (e.g., contact form or notifications)

---

## 📌 To Do / Possible Improvements

⚠️ Note: This project is not fully finished. It still requires bug fixes, optimizations, and UI polishing.

- Add user profile pages
- Implement rich text editor for blog posts
- Add unit & integration tests
- Enable file/image upload

---

## 👤 Author

Developed by **Marcis A**

---
