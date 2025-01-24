🪙 Tossing App
A fun and interactive Android application that simulates the tossing of a coin. The Tossing App features realistic animations, sound effects, and a unique cheat mode activated by shaking your device! With a sleek and dynamic design, this app is perfect for deciding between heads or tails with style.

🚀 Features
🎮 Core Features:
* Realistic Coin Toss Animation: Watch the coin flip with smooth 3D rotation and realistic transitions.
* Dynamic Toss Result: The result is randomized between heads and tails (unless cheat mode is activated 😉).
* Shake to Activate Cheat Mode: Cheat mode can be toggled by shaking your device, ensuring you always get heads when you want it.
* Interactive UI: Clickable coin and button to trigger a flip manually.
🌈 Design and Aesthetics:
* Seamless Background Color Transitions: The background smoothly transitions through a palette of vibrant colors for an engaging experience.
* Transparent Coin Integration: The coin seamlessly blends into the animated background, removing harsh borders and enhancing visual appeal.
🔊 Audio-Visual Effects:
* Sound Effects: A satisfying coin flip sound plays during every toss.
* Vibration Feedback: A subtle vibration when a toss is triggered or when cheat mode is toggled.
📱 Screenshots (Optional)
Include screenshots of your app in action, showcasing:

The coin toss animation.
The background color transition.
The cheat mode notification (e.g., "Cheat Mode Activated").
🛠️ Technologies Used
* Kotlin: Programming language for Android development.
* Android SDK: Framework for building and running Android apps.
* ConstraintLayout: For responsive and dynamic UI design.
* Android Sensors: Accelerometer for shake detection.
* MediaPlayer API: For sound effects during coin flips.
* ValueAnimator: For smooth background color transitions.

 📂 Project Structure

 ├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/tossingapp/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   ├── drawable/
│   │   │   │   │   └── heads.png
│   │   │   │   │   └── tails.png
│   │   │   │   ├── raw/
│   │   │   │   │   └── coin_flip_sound.mp3
│   │   │   │   ├── values/
│   │   │   │   │   └── themes.xml
│   │   │   │   │   └── colors.xml


⚙️ How to Run the App
1. Clone the Repository:

https://github.com/sarthakkumar12027/CoinToss.git
cd CoinToss

2. Open in Android Studio:
Launch Android Studio and open the project directory.

3.Build the Project:
Let Gradle sync and download the required dependencies.

4.Run on Emulator/Device:
Connect a physical device or set up an emulator and run the app.


✨ Cheat Mode Details
How to Activate:
* Shake the device to toggle cheat mode.
* You’ll see a toast notification indicating whether cheat mode is activated or deactivated.
What It Does:
* Ensures the result is always Heads when activated.


🛡️ Future Enhancements
* Add a settings page to configure cheat mode and shake sensitivity.
* Customize coin appearance (e.g., custom logos for heads/tails).
* Add multi-language support.
* Support for landscape mode.  
