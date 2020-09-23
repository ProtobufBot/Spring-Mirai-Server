import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*

buildscript {
    repositories {
        mavenLocal()
        maven(url = "http://maven.aliyun.com/nexus/content/groups/public/")
        maven(url = "http://maven.aliyun.com/nexus/content/repositories/jcenter")
        mavenCentral()
        jcenter()
        maven(url = "http://repo.spring.io/plugins-release")
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.13")
    }
}
plugins {
    idea
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.google.protobuf") version "0.8.13"
    kotlin("jvm") version "1.4.0"
    kotlin("plugin.spring") version "1.4.0"
}

group = "net.lz1998"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenLocal()
    maven(url = "http://maven.aliyun.com/nexus/content/groups/public/")
    maven(url = "http://maven.aliyun.com/nexus/content/repositories/jcenter")
    mavenCentral()
    jcenter()
    maven(url = "http://repo.spring.io/plugins-release")
    maven(url = "https://plugins.gradle.org/m2/")
}

dependencies {
//    implementation("net.mamoe:mirai-core-qqandroid:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    implementation("com.google.protobuf:protobuf-java:3.12.2")
//    implementation("com.google.protobuf:protobuf-javalite:3.8.0")
    implementation("com.google.protobuf:protobuf-java-util:3.12.2")



    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}


protobuf {
    generatedFilesBaseDir = "$projectDir/src"
    println(generatedFilesBaseDir)
    protoc { artifact = "com.google.protobuf:protoc:3.7.0" }
}
sourceSets {
    main {
        proto {
            // 除了默认的'src/main/proto'目录新增proto文件的方法
            srcDir("onebot_idl")
            include("**/*.proto")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
