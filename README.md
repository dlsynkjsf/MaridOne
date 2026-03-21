# MaridOne

A Spring Boot–based Human Resource Information System (HRIS) REST API for managing employees, payroll, scheduling, leave, and more.

## Features

- **Authentication** — JWT-based login with refresh tokens and role-based access control (MANAGEMENT, HR, EMPLOYEE)
- **Employee Management** — Create, update, and manage employee records, positions, and employment status
- **Payroll Processing** — Automated payroll runs, earnings and deductions line items, payslip generation
- **Leave Management** — Leave request submissions, approval workflows, and balance tracking
- **Scheduling** — Template shift schedules, daily shift assignments, and company calendar with Philippine holiday support
- **Overtime** — Overtime request tracking and approval
- **Attendance & Activity Logs** — Log daily attendance and employee activity
- **Dispute Resolution** — Payroll dispute submission and management
- **Bank Accounts** — Employee bank account linking for payroll disbursement
- **Notifications** — In-app notification system
- **Document Storage** — AWS S3–backed document upload and retrieval

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Security | Spring Security + JWT (jjwt) |
| Database | PostgreSQL |
| Migrations | Liquibase |
| ORM | Spring Data JPA / Hibernate |
| Object Mapping | MapStruct |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| File Storage | AWS S3 |
| Build Tool | Maven |

## Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 14+
- (Optional) Docker & Docker Compose for a local database

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/dlsynkjsf/MaridOne.git
cd MaridOne
```

### 2. Start a local PostgreSQL instance

A `compose.yaml` is provided for convenience:

```bash
docker compose up -d
```

This spins up a PostgreSQL container accessible on port `5432` with:
- Database: `mydatabase`
- Username: `myuser`
- Password: `secret`

### 3. Configure environment variables

The application reads its configuration from environment variables. Copy and fill in the values below before running.

| Variable | Description | Example |
|----------|-------------|---------|
| `DB_URL` | JDBC connection URL | `jdbc:postgresql://localhost:5432/mydatabase` |
| `DB_USERNAME` | Database username | `myuser` |
| `DB_PASSWORD` | Database password | `secret` |
| `JWT_SECRET` | Base64-encoded JWT signing secret | `DBJLzOFR+2X80I/...` |
| `URL_HOST` | Frontend origin URL (for CORS) | `http://localhost:5173` |
| `CALENDARIFIC_API_KEY` | *(Optional)* API key for Philippine holiday data | — |

For local development the `dev` profile provides sensible defaults for all of the above except `CALENDARIFIC_API_KEY`.

### 4. Build and run

```bash
./mvnw spring-boot:run
```

Or build a JAR and run it:

```bash
./mvnw package -DskipTests
java -jar target/MaridOne-0.0.1-SNAPSHOT.jar
```

The application starts on **`http://localhost:8080`** by default.

### 5. Explore the API

Interactive Swagger UI is available at:

```
http://localhost:8080/swagger-ui.html
```

## API Overview

| Group | Base Path | Description |
|-------|-----------|-------------|
| Auth | `/api/auth` | Login, token refresh, logout |
| Employees | `/api/employees` | Employee CRUD and status management |
| User Accounts | `/api/users` | User account management |
| Bank Accounts | `/api/bank` | Employee bank account linking |
| Payroll Runs | `/api/payroll` | Payroll run creation and processing |
| Payslips | `/api/payslip` | Payslip retrieval |
| Payroll Items | `/api/items` | Earnings and deductions components |
| Disputes | `/api/dispute` | Payroll dispute submission and resolution |
| Leave | `/api/leave` | Leave requests and balance management |
| Overtime | `/api/overtime` | Overtime request management |
| Schedule | `/api/schedule` | Shift templates and daily assignments |
| Calendar | `/api/calendar` | Company calendar and holidays |
| Logs | `/api/log` | Activity and attendance logs |
| Notifications | `/api/notifications` | In-app notifications |
| Documents | `/api/documents` | Document upload and retrieval |

## Project Structure

```
src/
├── main/
│   ├── java/org/example/maridone/
│   │   ├── auth/               # JWT authentication & token management
│   │   ├── config/             # Security, async, and cloud configuration
│   │   ├── core/
│   │   │   ├── bank/           # Bank account management
│   │   │   ├── employee/       # Employee entity and management
│   │   │   └── user/           # User account management
│   │   ├── document/           # AWS S3 document storage
│   │   ├── exception/          # Global exception handling
│   │   ├── leave/              # Leave requests and balances
│   │   ├── log/                # Activity and attendance logging
│   │   ├── notification/       # Notification system
│   │   ├── overtime/           # Overtime request management
│   │   ├── payroll/
│   │   │   ├── dispute/        # Payroll dispute resolution
│   │   │   ├── item/           # Earnings/deductions line items
│   │   │   ├── payslip/        # Payslip generation
│   │   │   └── run/            # Payroll run processing
│   │   └── schedule/
│   │       ├── calendar/       # Company calendar and holidays
│   │       └── shift/          # Shift scheduling
│   └── resources/
│       ├── db/changelog/       # Liquibase migration scripts
│       ├── application.yaml
│       ├── application-dev.yaml
│       └── application-prod.yaml
└── test/
    └── java/org/example/maridone/
```

## Running Tests

```bash
./mvnw test
```

## License

See [LICENSE](LICENSE) for details.
