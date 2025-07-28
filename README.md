# NodeJSReact_JavaPlaywright_Project

This project demonstrates full-stack automation testing for a simple web application with a **React frontend** and a **Node.js backend API**. It includes both **UI automation using Playwright (Java)** and **API testing using REST-assured**.

---

## 📌 Project Overview

- **Frontend:** React CRUD App (Goals/Workouts)
- **Backend:** Node.js + Express + MongoDB API
- **UI Testing:** Java + Playwright + JUnit + ExtentReports
- **API Testing:** Java + REST-assured
- **CI/CD:** GitHub Actions
- **Code Coverage:** JaCoCo

---

## ✅ Test Coverage

### UI Automation (Playwright)
- Login with valid & invalid credentials
- Create a new workout
- Edit an existing workout
- Delete a workout
- Validate UI state post-actions

### API Automation (REST-assured)
- `POST /login` (positive & negative)
- `GET /api/workouts`
- `POST /api/workouts`
- `PUT /api/workouts/:id`
- `DELETE /api/workouts/:id`

---

## 🧪 Tools Used

| Category        | Tool/Library        |
|----------------|---------------------|
| UI Automation  | Playwright (Java), JUnit |
| API Automation | REST-assured        |
| Reporting      | ExtentReports       |
| CI/CD          | GitHub Actions      |
| Code Coverage  | JaCoCo              |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- Node.js & npm (for running the app locally)
- MongoDB (local or cloud)

### Clone the Repo

```bash
git clone https://github.com/PradnyaBahadure/NodeJSReact_JavaPlaywright_Project.git
cd NodeJSReact_JavaPlaywright_Project
Run Backend (Node.js API)
bash
Copy
Edit
cd backend
npm install
npm run dev
Run Frontend (React)
bash
Copy
Edit
cd frontend
npm install
npm start
Run Tests
bash
Copy
Edit
# Run UI Tests
mvn test -Dgroups="ui"

# Run API Tests
mvn test -Dgroups="api"
💡 To run all tests, simply use mvn test.

📄 Test Report
After test execution, open:
target/extentReports/index.html

🔧 CI/CD & Code Coverage
GitHub Actions is set up to run tests on each push.

JaCoCo generates coverage report in target/site/jacoco/index.html.
If report not generated, run "mvn jacoco:report" in terminal, so it will generate report.

🛠️ Future Enhancements
Data-Driven Testing: Add support for parameterization using Excel or CSV files to drive multiple test scenarios dynamically.
Test Data Management: Externalize test data (e.g., credentials, workout details) for better maintainability and scalability.
Environment Support: Enable switching between environments (dev, QA, staging) via configuration files or command-line options.
Parallel Execution: Enhance the test framework to support parallel UI/API test execution for faster feedback.
Advanced Reporting: Integrate with tools like Allure or add historical trend analysis to ExtentReports.
Dockerization: Package the entire stack (React, Node.js, MongoDB, tests) into Docker containers for isolated CI runs.

📑 Documentation
See TestPlan_Automation.docx for test strategy, tools, and assumptions.

🤝 Contributing
Pull requests are welcome! For major changes, please open an issue first.

📬 Contact
Created by Pradnya Bahadure
GitHub: @PradnyaBahadure
