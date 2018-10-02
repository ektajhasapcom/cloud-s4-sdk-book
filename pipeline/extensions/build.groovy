def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     def  imageV = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
     
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"          
                 sh "helm upgrade --install --force buildconfiguration --set imageversion=$imageV buildconfiguration"            
              }
    }

}

return this
