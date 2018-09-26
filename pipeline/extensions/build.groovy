def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
    dockerExecute(script: this, dockerImage: 'docker:18.06'){ 
        
         sh "docker version"
    }
     
    docker.withRegistry('https://registry.hub.docker.com', 'dockerCredentialId') {
        
    }
      
    originalStage()
    
}

return this
