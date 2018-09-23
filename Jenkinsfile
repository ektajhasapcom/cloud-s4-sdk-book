


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
         parallel {
                stage("Java Build") {
                   steps {
                       sh "mvn clean install -Dmaven.test.failure.ignore=true"
                   }
                }
                stage("Mockserver") {
                  steps {
                    container('docker') {
                          sh "docker login --username ektajha --password Mapapaji@99"
                          sh "docker stop \$(docker ps|grep ektajha/mockserver:v10|awk '{print \$1}')"
                          sh "docker pull docker.io/ektajha/mockserver:v10"
                          sh "docker run -p 3000:3000 ektajha/mockserver:v10"
                     }
                  }
                }
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
