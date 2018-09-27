def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     docker run -t -i --privileged  docker:1.12.6-dind
     
     sh "docker version"
     
     dockerExecute(script: this, dockerImage: 'docker:1.12.6'){ 
        sh "docker version"
    }
}

return this
