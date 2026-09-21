# 🧪 Selenium Test Automation Framework — BDD + POM + Java

A robust end-to-end test automation framework built with **Selenium WebDriver**, **Java**, and **TestNG** following the **Page Object Model (POM)** design pattern. Tests cover complex e-commerce workflows including multi-currency validation, dynamic cart management, and advanced product filtering.

---

## 🚀 Projects Covered

| Project | URL | Type |
|---|---|---|
| SauceDemo | https://www.saucedemo.com | E-Commerce |
| Automation Test Store | https://automationteststore.com | E-Commerce |

---

## 🛠️ Tech Stack

| Tool | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution & assertions |
| Maven | Dependency management |
| Page Object Model (POM) | Maintainable design pattern |
| XPath & CSS Selectors | Element location strategies |
| Chrome / Firefox | Supported browsers |

---

## 📁 Project Structure

```
├── Configrations/
│   └── config.properties            # Base URLs and browser config
├── src/test/java/
│   ├── pageObjects/
│   │   ├── automationteststore/     # Page Object classes (XPath & CSS)
│   │   └── saucedemo/               # Page Object classes for SauceDemo
│   ├── testCases/
│   │   ├── automationteststore/     # Test cases for Automation Test Store
│   │   └── saucedemo/               # Test cases for SauceDemo
├── reports/                         # Test execution reports
├── logs/                            # Execution logs
├── testng.xml                       # TestNG suite configuration
└── pom.xml                          # Maven dependencies
```

---

## ⚙️ Prerequisites

- Java JDK 11+
- Maven 3.6+
- Chrome or Firefox browser
- ChromeDriver / GeckoDriver (included in `Drivers/` folder)

---

## ▶️ How to Run

**Clone the repository:**
```bash
git clone https://github.com/HammadAsif-997/SeleniumProjectWith-Bdd-Pom-Java.git
cd SeleniumProjectWith-Bdd-Pom-Java
```

**Run all tests:**
```bash
mvn test
```

**Run specific suite:**
```bash
mvn test -DsuiteXmlFile=testng.xml
```

**Switch browser** (edit `Configrations/config.properties`):
```properties
browser=chrome   # or firefox
```

---

## 📊 Test Scenarios

### 🛒 SauceDemo (saucedemo.com)

| # | Test Case | Description |
|---|---|---|
| TC1 | Invalid Login | Login with wrong password, verify exact error message |
| TC2 | Valid Login | Login with correct credentials, verify redirect to inventory page |
| TC3 | Sort Products Low to High | Sort all products by price ascending, verify sort order is correct |
| TC4 | Cart Quantity & Total Validation | Add top 2 items to cart, verify cart count, item names, and total price match |

### 🛍️ Automation Test Store (automationteststore.com)

| # | Test Case | Description |
|---|---|---|
| TC1 | Add Dove Brand Item | Select Dove brand, sort by date, add newest item, verify cart count & total ($7.20) |
| TC2 | Add T-Shirts & Shoes | Sort by price, select items by size (Medium), add shoes with quantity 2 |
| TC3 | Skincare Sale Items | Filter sale & in-stock items from skincare section, verify cart total ($309.00) |
| TC4 | Men's Items Ending With "M" | Add items whose names end with "M", verify correct items appear in cart |
| TC5 | Multi-Currency Cart Validation | Switch between €, $, £ — add sale items from Makeup & Men sections, validate totals match across currencies, ensure cart reaches 15 items |

---

## 🔑 Key Highlights

- **Dual selector strategy** — Both XPath and CSS Selectors used for maximum flexibility
- **Dynamic filtering** — Tests filter products by sale status, stock availability, name patterns
- **Multi-currency validation** — Cart totals verified across EUR, USD, GBP
- **Reusable Page Objects** — Separate POM classes per site reduce code duplication
- **Precise assertions** — Every test verifies exact error messages, URLs, cart counts, item names and amounts

---

## 📈 Results

- Reduced manual regression testing effort by **40%**
- Cart amount accuracy validated across **3 currencies**
- **9 end-to-end test scenarios** covering real-world shopping flows

## 📊 Live Test Report

🔗 [View Live Test Report](https://hammadasif-997.github.io/SeleniumProjectWith-Bdd-Pom-Java/reports/Test-Report-2024.08.29.23.26.02.html)

---

## 👤 Author

**Hammad Asif**
- 📧 hmmd97@gmail.com
- 🔗 [LinkedIn](https://linkedin.com/in/hammadasif)
- 💻 [GitHub](https://github.com/HammadAsif-997)
