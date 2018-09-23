


pipeline {
  agent any
  
  tools {
        maven 'M3'
  }

  stages {
    
      stage ('Init') {
        steps {
            checkout scm
        }
      }
    
      stage('Build') {
        steps {
          sh "mvn clean install -DskipTests"
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
