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

### MVP Architecture 
<img width="1028" height="495" alt="MVP drawio" src="https://github.com/user-attachments/assets/46e87961-0604-4643-bfbd-27c08140234f" />



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
    <tr>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Onboarding1</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Onboarding2</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Onboarding3</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Onboarding4</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Home</th>
    </tr>
    <tr>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Onboarding1" src="https://github.com/user-attachments/assets/5a3b372c-aba4-4dfc-b944-9f0d2956b309"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Onboarding2" src="https://github.com/user-attachments/assets/7563cd5f-1d62-436a-8f57-8d575224abc0"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Onboarding3" src="https://github.com/user-attachments/assets/af2cac0c-2ab2-4996-a599-7eeaf14f269b"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Onboarding4" src="https://github.com/user-attachments/assets/63562bd2-02e8-46ba-ad40-b039683acc4e"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Home" src="https://github.com/user-attachments/assets/b7b91206-2de4-4408-8c19-071513bbf8d5"></td>
    </tr>
    <tr>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">all charcter</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Character Details</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">buy Character</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Settings</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Buy Lives</th>
    </tr>
    <tr>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="all charcter" src="https://github.com/user-attachments/assets/3849db0e-5bb0-4cb2-886f-a74884554ad5"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Character Details" src="https://github.com/user-attachments/assets/2691bc7a-2b36-4acc-a65d-8ecd15248923"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="buy Character" src="https://github.com/user-attachments/assets/21ca95e3-adc6-4e76-9340-379874f9a84d"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Settings" src="https://github.com/user-attachments/assets/c573005b-a3d9-41f2-a329-caa3d23dfbb5"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Buy Lives" src="https://github.com/user-attachments/assets/b9096199-6d86-4a95-bce1-80cc7b059a53"></td>
    </tr>
    <tr>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">all rewards</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Reward Details</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Game Categories</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Select Level</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Game Questions</th>
    </tr>
    <tr>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="all rewards" src="https://github.com/user-attachments/assets/511b7033-910e-4a19-b126-750e6605fca5"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Reward Details" src="https://github.com/user-attachments/assets/46894c1f-7494-4276-b57f-a326c3478c78"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Game Categories" src="https://github.com/user-attachments/assets/19572514-87a4-42d2-b6df-f7e3ea7701d2"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Select Level" src="https://github.com/user-attachments/assets/75f03932-637c-4481-ad56-1d138536c114"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Game Questions" src="https://github.com/user-attachments/assets/900a139a-0f63-4521-a8aa-4696e4490de3"></td>
    </tr>
    <tr>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Correct Answer</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Incorrect Answer</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Quiz Results</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Game History</th>
      <th style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;">Home Data</th>
    </tr>
    <tr>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Correct Answer" src="https://github.com/user-attachments/assets/83bc7727-3bb2-415b-8979-d2ad62714243"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Incorrect Answer" src="https://github.com/user-attachments/assets/a88231a2-7cf5-4935-a7df-908527a94d98"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Quiz Results" src="https://github.com/user-attachments/assets/f86f4776-7a29-4503-af31-65e8b3d47da5"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Game History" src="https://github.com/user-attachments/assets/f768ebd4-376d-4b0a-87f0-ea3cb259633f"></td>
      <td style="width: 20%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="Home Data" src="https://github.com/user-attachments/assets/8996b4d8-249f-4c32-ab5d-797ebc108906"></td>
    </tr>
   </tbody>
</table>
