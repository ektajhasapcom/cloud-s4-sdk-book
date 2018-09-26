def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
   
    
    dockerExecute(script: this, dockerImage: 'docker:18.06'){ 
         originalStage()
         sh "docker version"
    }
     
    docker.withRegistry('https://registry.hub.docker.com', 'dockerCredentialId') {
        
    }
    
}

return this
