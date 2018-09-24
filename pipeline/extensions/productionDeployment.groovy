def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     dockerExecute(script: this, dockerImage: 'lachlanevenson/k8s-kubectl'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
              }
    }
    
}

return this
