import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
  `kotlin-dsl`
}

repositories {
  google()
  gradlePluginPortal()
  mavenCentral()
}

dependencies {
  // Necessary. See: https://youtrack.jetbrains.com/issue/KT-31643#focus=Comments-27-4818475.0-0
  implementation("com.android.tools.build:gradle:4.2.2")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.30")

  implementation("com.diffplug.spotless:spotless-plugin-gradle:5.12.5")
  implementation("app.cash.licensee:licensee-gradle-plugin:1.2.0")
}
