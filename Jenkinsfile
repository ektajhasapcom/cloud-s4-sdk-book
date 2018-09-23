


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
          sh "mvn clean install -Dmaven.test.skip=true"
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
