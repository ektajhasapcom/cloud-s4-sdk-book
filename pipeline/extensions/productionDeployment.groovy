def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     def  imageV = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
     
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl get pods"
                 sh "helm init --upgrade"
                 sh "sleep 11"
                 sh "helm upgrade --install --force addrbook --set imageversion=$imageV addressbook"            
              }
    }

}

return this
