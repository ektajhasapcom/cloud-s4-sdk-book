def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     export ON_K8S=true
     dockerExecute(script: this, dockerImage: 'lachlanevenson/k8s-kubectl'){                
        sh "kubectl get pods"
    }
}

return this
