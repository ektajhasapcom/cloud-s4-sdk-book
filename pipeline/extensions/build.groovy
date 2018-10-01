def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl get pods"
                 sh "helm init --upgrade"
                 sh "sleep 10"
                 sh "helm upgrade --install --force addrbook  addressbook"            
              }
    }

}

return this