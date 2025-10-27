pipeline {
    agent any

    tools {
        maven 'maven 3.9.11'
        jdk 'JAVA_HOME'
        git 'git'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Prepare Artifacts') {
            steps {
                sh '''
                rm -rf deploy
                mkdir -p deploy/static
                mkdir -p deploy/templates

                cp Dockerfile deploy/
                cp target/demo-0.0.1-SNAPSHOT.jar deploy/app.jar

                # ✅ static 전체 복사 (기존 images 만 복사는 잘못된 방식)
                c
