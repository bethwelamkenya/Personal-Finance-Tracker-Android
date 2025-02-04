plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "org.example.personalfinancetracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.example.personalfinancetracker"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

//configurations.all {
//    resolutionStrategy.eachDependency { details ->
//        if (details.requested.group == "com.intellij" && details.requested.name == "annotations") {
//            // Redirect any request for com.intellij:annotations to org.jetbrains:annotations:23.0.0
//            details.useTarget 'org.jetbrains:annotations:23.0.0'
//        }
//    }
//}


dependencies {

    implementation("org.jetbrains:annotations:23.0.0")
    implementation(libs.androidx.core.ktx) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.lifecycle.runtime.ktx) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.activity.compose) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(platform(libs.androidx.compose.bom)) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.ui) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.ui.graphics) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.ui.tooling.preview) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.material3) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.firebase.common.ktx) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.firebase.firestore) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.navigation.runtime.ktx) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.navigation.compose) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.hilt.navigation.compose) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.hilt.compiler) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.androidx.hilt.common) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.firebase.auth.ktx) {
        exclude(group = "com.intellij", module = "annotations")
    }
    testImplementation(libs.junit) {
        exclude(group = "com.intellij", module = "annotations")
    }
    androidTestImplementation(libs.androidx.junit) {
        exclude(group = "com.intellij", module = "annotations")
    }
    androidTestImplementation(libs.androidx.espresso.core) {
        exclude(group = "com.intellij", module = "annotations")
    }
    androidTestImplementation(platform(libs.androidx.compose.bom)) {
        exclude(group = "com.intellij", module = "annotations")
    }
    androidTestImplementation(libs.androidx.ui.test.junit4) {
        exclude(group = "com.intellij", module = "annotations")
    }
    debugImplementation(libs.androidx.ui.tooling) {
        exclude(group = "com.intellij", module = "annotations")
    }
    debugImplementation(libs.androidx.ui.test.manifest) {
        exclude(group = "com.intellij", module = "annotations")
    }
}