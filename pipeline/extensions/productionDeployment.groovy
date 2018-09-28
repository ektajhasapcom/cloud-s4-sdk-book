def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
     
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'K8s',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl get pods"
                 sh "helm init --upgrade"
                 sh "sleep 40"
                 sh "helm upgrade --install --force addrbook --set imageversion=$tag addressbook --namespace=production"            
              }
    }

}

return this
