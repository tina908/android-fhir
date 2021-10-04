plugins {
    id(Plugins.BuildPlugins.androidLib)
    id(Plugins.BuildPlugins.kotlinAndroid)
}

android {
    compileSdkVersion(Sdk.compileSdk)
    buildToolsVersion(Plugins.Versions.buildTools)

    defaultConfig {
        minSdkVersion(Sdk.minSdk)
        targetSdkVersion(Sdk.targetSdk)
        testInstrumentationRunner(Dependencies.androidJunitRunner)
        // Need to specify this to prevent junit runner from going deep into our dependencies
        testInstrumentationRunnerArguments(mapOf("package" to "com.google.android.fhir.workflow"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
        getByName("debug") { isTestCoverageEnabled = true }
    }
    compileOptions {
        // Flag to enable support for the new language APIs
        // See https://developer.android.com/studio/write/java8-support
        isCoreLibraryDesugaringEnabled = true
        // Sets Java compatibility to Java 8
        // See https://developer.android.com/studio/write/java8-support
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        // See https://developer.android.com/studio/write/java8-support
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    testOptions { unitTests.isIncludeAndroidResources = true }
}

dependencies {
    androidTestImplementation(Dependencies.AndroidxTest.extJunit)
    androidTestImplementation(Dependencies.AndroidxTest.extJunitKtx)
    androidTestImplementation(Dependencies.AndroidxTest.runner)

    api(Dependencies.HapiFhir.structuresR4) { exclude(module = "junit") }

    coreLibraryDesugaring(Dependencies.desugarJdkLibs)

    implementation(Dependencies.Kotlin.androidxCoreKtx)
    implementation(Dependencies.Kotlin.kotlinCoroutinesAndroid)
    implementation(Dependencies.Kotlin.kotlinCoroutinesCore)
    implementation(Dependencies.Kotlin.stdlib)
//    implementation("org.opencds.cqf.cql:engine:1.5.2-SNAPSHOT")
//    implementation("org.opencds.cqf.cql:engine.fhir:1.5.2-SNAPSHOT")
    implementation("org.opencds.cqf.cql:evaluator:1.2.1-SNAPSHOT")
    implementation("org.opencds.cqf.cql:evaluator.builder:1.2.1-SNAPSHOT")
    implementation("org.opencds.cqf.cql:evaluator.dagger:1.2.1-SNAPSHOT")
    implementation(project(":engine"))

    testImplementation(Dependencies.AndroidxTest.core)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.truth)
}