


pipeline {
  agent any
  
  tools {
        maven 'M3'
  }

  stages {
    
      stage ('Init') {
        steps {
            checkout scm
            sh "docker pull ektajha/mockserverimage:latest"
            sh "docker run -p 3000:3000 ektajha/mockserverimage:latest"
        }
      }
    
      stage('Build') {
        steps {
          sh "mvn clean install -Dmaven.test.failure.ignore=true"
        }
      }
      stage('Build and push image with Container Builder') {
        steps {
           container('docker') {
              sh "docker build -t java-image:v1 ."
           }
         }
      }
   }
}
