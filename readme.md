# GitHub Activity CLI

A command-line application that fetches and displays a user's recent GitHub activity using the GitHub API.

---

## 📌 Features

- Fetches public GitHub events for any user
- Supports multiple event types:
  - PushEvent
  - CreateEvent
  - ForkEvent
  - WatchEvent
  - IssuesEvent
- Formats output into a clean CLI view
- Handles empty, invalid, and unsupported data safely
- Built with Java 21 and Gradle
- Includes automated tests and CI pipeline

<br>

## 🚀 Installation

Clone the repository and install the CLI tool:

```bash
git clone https://github.com/st33ze/github-activity.git
cd github-activity
./install.sh
```
<br>

## 💻 Usage
```bash
github-activity <username>
```
<br>

## 📦 Example Output

```bash
Recent GitHub activity from st33ze

[2026-06-26 11:56] Pushed commits to st33ze/github-activity
[2026-06-26 11:46] Pushed commits to st33ze/github-activity
[2026-06-26 11:09] Pushed commits to st33ze/github-activity
[2026-06-26 10:50] Created a new repository st33ze/github-activity
```
