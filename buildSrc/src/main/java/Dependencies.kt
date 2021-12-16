object BuildConfigVersions {
    const val applicationID ="com.app.bitcointicker"
    const val compileSdk = 31
    const val buildToolsVersion = "30.0.3"
    const val minSdk = 23
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions{
    const val coroutines = "1.3.9"
    const val kotlinVersion = "1.5.21"
    const val appcompat = "1.3.1"
    const val coreKtx = "1.6.0"
    const val material = "1.4.0"
    const val constraintLayout  = "2.1.0"
    const val navigation = "2.3.5"
    const val retrofit = "2.9.0"
    const val httpInterceptor = "4.4.0"
    const val recyclerview = "1.1.0"
    const val MPAndroidChart = "v3.1.0"
    const val lifecycle = "2.2.0"
    const val lifecycleExtension = "1.1.1"
    const val hilt  = "2.37"
    const val hiltActivity = "1.2.0-beta01"
    const val junit = "4.+"
    const val junitExt = "1.1.3"
    const val espresso = "3.4.0"
    const val gradleVersion = "7.0.2"
    const val coilVersion = "1.4.0"
    const val gmsVersion = "4.3.10"
    const val authVersion = "21.0.1"
    const val firestoreVersion = "24.0.0"
    const val lottieVersion = "3.1.0"
}

object Libs{
    const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx= "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat= "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout= "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt= "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso= "androidx.test.espresso:espresso-core:${Versions.espresso}"

    //navigation
    const val navigationFragment= "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationUi= "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx= "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    //Retrofit
    const val retrofit= "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter= "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val interceptor= "com.squareup.okhttp3:logging-interceptor:${Versions.httpInterceptor}"

    //Recyclerview
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"

    //Chart for graoh
    const val mpChart= "com.github.PhilJay:MPAndroidChart:${Versions.MPAndroidChart}"

    //Coroutines
    const val coroutinesCore= "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines= "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //Lifecycle
    const val lifecycleExtensionArch = "android.arch.lifecycle:extensions:${Versions.lifecycleExtension}"
    const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtension}"
    const val lifecycleViewmodelKtx= "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    //Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val activityKtx= "androidx.activity:activity-ktx:${Versions.hiltActivity}"

    //Coil
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"

    //Firebase
    const val googleService = "com.google.gms:google-services:${Versions.gmsVersion}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.authVersion}"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore:${Versions.firestoreVersion}"

    //lottie
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"


}