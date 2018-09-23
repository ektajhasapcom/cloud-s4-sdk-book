


pipeline {
  agent any
  

  stages {
    
    stage('Init') {
            steps {
               
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
