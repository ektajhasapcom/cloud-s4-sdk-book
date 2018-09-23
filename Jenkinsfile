#!/usr/bin/env groovy

final def pipelineSdkVersion = 'master'

pipeline {
    agent any
    options {
        timeout(time: 120, unit: 'MINUTES')
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        skipDefaultCheckout()
    }
    stages {  
    
        
        stage('Docker Push') {
            steps {
                 withDockerRegistry([ credentialsId: "docker", url: "https://hub.docker.com/" ]) {
                        // following commands will be executed within logged docker registry
                          sh 'docker push ektajha/mockserver:v10'
                 }
            }
        }

    }
}
