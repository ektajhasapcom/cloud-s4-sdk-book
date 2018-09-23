


pipeline {
  agent any
  
  tools {
        maven 'M3'
  }

  stages {
    
      stage ('Init') {
        steps {
            checkout scm
            container('docker') {
                  sh "docker login --username ektajha --password Mapapaji@99"
                  sh "docker pull docker.io/ektajha/mockserverimage:v10"
                  sh "docker run -p 3000:3000 ektajha/mockserverimage:v10"
            }
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
