def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
   
   sh "docker version"
   
    
    originalStage()
}

return this
