# EcoHome Manager

EcoHome Manager is a robust backend application designed to manage smart home ecosystems with a focus on energy efficiency. It provides a comprehensive API for tracking energy consumption, managing smart devices, handling alerts, and optimizing home automation schedules.

## 🚀 Features

-   **Device Management**: Register, update, and monitor various smart home devices (lights, thermostats, appliances).
-   **Energy Monitoring**: Track real-time and historical energy usage logs to identify consumption patterns.
-   **Alert System**: Intelligent alerting mechanism for device malfunctions, high energy usage, or security events.
-   **Room Organization**: Group devices by rooms for easier management and control.
-   **Scheduling**: Create automated schedules for devices to optimize energy usage.
-   **User Management**: Secure user registration and role-based access control.

## 🛠️ Tech Stack

-   **Language**: Java 17+
-   **Framework**: Spring Boot 3.x
-   **Build Tool**: Maven
-   **Database**: H2 (Default for dev) / PostgreSQL (Recommended for prod)
-   **API**: RESTful Architecture

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

-   Java Development Kit (JDK) 17 or higher
-   Maven 3.6+
-   Git

## 🏃‍♂️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/thanhauco/eco-home-manager.git
cd eco-home-manager
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## 🔌 API Endpoints (Overview)

| Resource | Method | Description |
|----------|--------|-------------|
| `/api/devices` | GET, POST | Manage smart devices |
| `/api/energy-logs` | GET, POST | Record and retrieve energy stats |
| `/api/alerts` | GET | Fetch system alerts |
| `/api/users` | POST | User registration and management |

## 🤝 Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## 📄 License

This project is licensed under the MIT License.
