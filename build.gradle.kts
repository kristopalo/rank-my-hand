plugins {
	kotlin("jvm") version "2.0.21"
	kotlin("plugin.spring") version "2.0.21"
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.bigstackbully"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude("org.mockito") // Use MockK instead of Mockito
	}
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	// Kotest core + assertions
	testImplementation("io.kotest:kotest-assertions-core:5.9.0")
	testImplementation("io.mockk:mockk:1.14.3")
	api("com.ninja-squad:springmockk:4.0.2")
	// SpringDoc OpenAPI (Swagger)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.test {
	useJUnitPlatform()
}
