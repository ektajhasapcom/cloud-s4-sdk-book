def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     dockerExecute(script: this, dockerImage: 'docker:1.12.6-dind'){ 
        sh "docker version"
    }
}

return this
