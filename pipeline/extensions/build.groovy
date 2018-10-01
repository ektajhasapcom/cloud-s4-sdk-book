def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    withKubeConfig([credentialsId: 'k8s-credentials']) {
                    
            sh "kubectl get pods"
    }
}

return this
