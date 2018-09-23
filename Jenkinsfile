


pipeline {
  agent any
  
  tools {
        maven 'M3'
  }

  stages {    
       stage('Init') {
            parallel {
                stage("Checkout") {
                   steps {
                       checkout scm
                   }
                }
                stage("Mockserver") {
                  steps {
                    container('docker') {
                          sh "docker login --username ektajha --password Mapapaji@99"
                          sh "docker stop  $(docker ps -aq)"
                          sh "docker rm  $(docker ps -aq)"
                          sh "docker pull docker.io/ektajha/mockserver:v10"
                          sh "docker run -p 3000:3000 ektajha/mockserver:v10"
                     }
                  }
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
