def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     
     dockerExecute(script: this, dockerImage: 'ektajha/kubectl:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl cluster-info"
                 sh "kubectl get pods"
          
              }
    }

}

return this