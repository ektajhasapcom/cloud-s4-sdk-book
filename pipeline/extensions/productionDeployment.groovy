def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {  
    container('kubectl') {
      sh "kubectl get pods"
    }
}

return this
