import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}

val localProperties = Properties()
localProperties.load(rootProject.file("local.properties").inputStream())

val publicKey : String = localProperties.getProperty("publicKey")
val privateKey : String = localProperties.getProperty("privateKey")

android {
    namespace = "com.mango.marvelworld"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mango.marvelworld"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"http://gateway.marvel.com/v1/\""
            )
            buildConfigField(
                type = "String",
                name = "API_PUBLIC_KEY",
                value = "\"${publicKey}\""
            )
            buildConfigField(
                type = "String",
                name = "API_PRIVATE_KEY",
                value = "\"${privateKey}\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"https://gateway.marvel.com/v1/\""
            )
            buildConfigField(
                type = "String",
                name = "API_PUBLIC_KEY",
                value = "\"${publicKey}\""
            )
            buildConfigField(
                type = "String",
                name = "API_PRIVATE_KEY",
                value = "\"${privateKey}\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    dataBinding {
        android.buildFeatures.dataBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    val hiltVersion = "2.45"
    val roomVersion = "2.5.1"
    val retrofitVersion = "2.9.0"

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")

    implementation("androidx.core:core-splashscreen:1.0.1")                         // Splash Screen

    implementation("com.google.dagger:hilt-android:$hiltVersion")                   // Dagger-Hilt
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")                    // Dagger-Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")                   // Dagger-Hilt

    implementation("androidx.room:room-runtime:$roomVersion")                       // Room
    ksp("androidx.room:room-compiler:$roomVersion")                                 // Room
    implementation("androidx.room:room-ktx:$roomVersion")                           // Room
    implementation("androidx.room:room-paging:$roomVersion")                        // Room

    implementation("io.coil-kt:coil-compose:2.3.0")                                 // Coil

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")              // Retrofit
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")        // Retrofit

    implementation("androidx.navigation:navigation-compose:2.6.0")                  // Navigation
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")            // Lifecycle Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")          // ViewModel Compose

    implementation("androidx.paging:paging-common-ktx:3.1.1")                       // Paging 3
    implementation("androidx.paging:paging-compose:3.2.0-beta01")                   // Paging 3

    implementation("androidx.compose.material:material-icons-extended")             // Extended Material Icons

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")               // Desugaring

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}