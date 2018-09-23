


pipeline {
  agent any
  
  tools {
        maven 'M3'
  }

  stages {    
       stage('Init') {
          steps {
            checkout scm
          }
        }

    
      stage('Build') {
                stage("Java Build") {
                   steps {
                       sh "mvn clean install -Dmaven.test.failure.ignore=true"
                   }
                }
      }
    
      stage('Build and push image with Container Builder') {
        steps {
           container('docker') {
              sh "docker login --username ektajha --password Mapapaji@99"
              sh "docker build -t java-image:v1 ."
              sh "docker tag java-image:v1 ektajha/java-image:v1"
              sh "docker push ektajha/java-image:v1"
           }
         }
       }
   }
}
