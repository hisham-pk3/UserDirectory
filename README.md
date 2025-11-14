# User Directory – Offline-First Android App

An offline-first **User Directory** application built using **Kotlin, Jetpack Compose, Room, Retrofit, Coroutines, and MVVM architecture**.  
The app fetches user data from the public JSONPlaceholder API, caches it locally, and displays users even when the device is offline.

---

## 🚀 Features

### ✔ Fetch Users from Public API (GET)
- Uses Retrofit to retrieve users from `https://jsonplaceholder.typicode.com/users`
- GET-only operation (no POST/PUT/DELETE)

### ✔ Local Caching with Room Database
- Users are stored in a Room table
- Insertions use `OnConflictStrategy.REPLACE` to keep data updated
- UI always reads from Room (single source of truth)

### ✔ Offline-First Architecture
- On app launch, Room data displays instantly  
- App attempts API refresh  
- If online → updates Room → UI auto-refreshes  
- If offline → cached data still appears

### ✔ Local Search (Room SQL Query)
- Search by **name** or **email**
- Uses SQL `LIKE` query inside DAO
- No API request triggered during search

### ✔ Jetpack Compose UI
- Simple, clean list layout
- Displays **id, name, email, phone**
- Includes search bar and loading state

---

## 📱 Screenshots

> Replace the placeholders below with your actual screenshots.

### 1️⃣ User List (Online Mode)
![User List Screenshot](screenshots/online_list.png)

### 2️⃣ Search Function
![Search Screenshot](screenshots/search.png)

### 3️⃣ Offline Mode (Cached Data)
![Offline Screenshot](screenshots/offline.png)

### 4️⃣ DAO or API Code Screenshot
![DAO Screenshot](screenshots/dao_api.png)

---

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Room Database**
- **Retrofit + Gson**
- **Coroutines (Flow / StateFlow)**
- **MVVM Architecture**

---

## 🧩 Architecture Overview

