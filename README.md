# Qurio Game - Fun question game

<div align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Kotlin-blue.svg" alt="Language">
  <img src="https://img.shields.io/badge/UI-%20Xml-orange.svg" alt="UI">
  <img src="https://img.shields.io/badge/Architecture-MVP-red.svg" alt="Architecture">
  <img src="https://img.shields.io/badge/DI-Dagger-yellow.svg" alt="DI">
</div>

A modern Android application created using XML to test your knowledge in various subjects by questions.

## 🛠️ Tech Stack

- **Language**: Kotlin
- **Android SDK**
- **UI Framework**: XML
- **Architecture**: MVP
- **Dependency Injection**: Dagger
- **Build System**: Gradle
- **Navigation**:  Jetpack Navigation
- **Networking**: Retrofit
- **Async Programming**: Coroutines 
- **Database**: Room, DataStore

## 🏗️ App Architecture
The app follows **MVP Architecture** with **Base View** and **Base Presenter** and **Repository Pattern** :

### Modular Architecture 
<img width="1270" height="1010" alt="MVP drawio" src="https://github.com/user-attachments/assets/46e87961-0604-4643-bfbd-27c08140234f" />



## 📋 Prerequisites

- Android Studio Narwhal (2025.1.2) or later
- JDK 11 or higher
- Android SDK 21 or higher
- Git
## 🔧 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/MadridSquad/Movio.git
cd movio-android
```
### 4. Build and Run
1. Open the project in Android Studio  
2. Sync the project with Gradle files  
3. Build the project (**Build > Make Project**)  
4. Run on device or emulator (**Run > Run 'app'**)
### 5.📱 Running the App
1. Enable USB debugging and connect your physical device  
2. Create an Android Virtual Device (AVD) with API level 24 or higher  
3. Choose between `debug` and `release` builds from the Build Variants panel
## 📸 Screenshots
<table style="width: 100%; border-collapse: collapse;">
  <tbody>
    <tr><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Onboarding</th><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Splash</th><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Login</th><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Home</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Search</th></tr><tr><td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Onboarding" src="https://github.com/user-attachments/assets/b8ff1da0-4bb0-456e-a9a4-6c160c509d1e"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Splash" src="https://github.com/user-attachments/assets/e04b11e4-decc-4db0-a4d6-1b29c9a4b463"></td>
        <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Login" src="https://github.com/user-attachments/assets/de75db68-6dda-4f7d-b7dc-26312a7e26bb"></td>
        <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Home" src="https://github.com/user-attachments/assets/abe069f2-7205-4489-ba30-ee5ac8be66ed"></td>
        <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Search" src="https://github.com/user-attachments/assets/1e784e30-7718-49d1-9455-be9cb870769d"></td></tr><tr><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Library</th>
          <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Profile</th>
          <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Movie details</th>
          <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Submit rating</th>
          <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Sharing</th></tr><tr>
            <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Library" src="https://github.com/user-attachments/assets/5a4abf47-a9d4-4017-a66c-87e1a3262de8"></td>
            <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Profile" src="https://github.com/user-attachments/assets/e5dd57cd-8b00-4c37-8d15-1c762a1a822b"></td>
            <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Movie details" src="https://github.com/user-attachments/assets/ad50ce2c-cafc-4f10-9206-2bd45acb7348"></td>
            <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Submit rating" src="https://github.com/user-attachments/assets/6f1f8b33-0e87-4a79-90a6-8f4e1776abe2"></td><td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Sharing" src="https://github.com/user-attachments/assets/9e7d1f85-7719-4593-b897-d03d6a467d5c"></td>
          </tr><tr><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Actor details</th>
            <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">loading data</th>
            <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Current seasons</th>
            <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">My ratings</th>
            <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Reviews</th></tr>
    <tr><td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Actor details" src="https://github.com/user-attachments/assets/7f0b3b17-fdee-429c-a695-b724e1aa813e"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="loading data" src="https://github.com/user-attachments/assets/a68e54b4-c51f-49f6-bfdb-6598e67935bb"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Current seasons" src="https://github.com/user-attachments/assets/7f91e31c-0c62-4bf6-aefd-e722abbfab52"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="My ratings" src="https://github.com/user-attachments/assets/549f3700-ce83-462d-a01a-4222d1da74b5"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Reviews" src="https://github.com/user-attachments/assets/7329f0bb-b674-4958-9edf-51642749e60c"></td></tr><tr>
        <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Series details</th><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Similer series</th>
        <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Top cast</th><th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Supporting arabic language</th>
        <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Light theme</th></tr><tr><td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Series details" src="https://github.com/user-attachments/assets/d6a7655e-8149-418f-bf62-1291540f8e8e"></td>
          <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Similer series" src="https://github.com/user-attachments/assets/99d0bd96-503d-44e7-99f5-a69995be89f4"></td><td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Top cast" src="https://github.com/user-attachments/assets/0e8807e5-7ca1-40e4-ab7a-59b6307e8221"></td>
          <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Supporting arabic language" src="https://github.com/user-attachments/assets/51e18152-8441-4208-bb4b-cfa5bc4b2ee9"></td>
          <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Light theme" src="https://github.com/user-attachments/assets/a35bc3e9-8b77-45eb-8e5e-224a43a94b1d"></td></tr></tbody></table>
