


pipeline {
  agent any
  
  tools {
        maven 'M3'
  }

  stages {
    
      stage ('Init') {
        steps {
            checkout scm
            docker pull docker.io/dockerkyma/mockserverimage:latest
            docker run -p 3000:3000 dockerkyma/mockserverimage:latest
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
