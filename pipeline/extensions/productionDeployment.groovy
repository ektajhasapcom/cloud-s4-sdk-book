def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
     def imageName = "ektajha/addressbooklatest"
     
     dockerExecute(script: this, dockerImage: 'lachlanevenson/k8s-kubectl'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl get pods"
              }
    }
    
     dockerExecuteOnKubernetes(script: this, containerMap: ['lachlanevenson/k8s-kubectl': 'kubectl', 'devth/helm': 'helm']){ 
           container('kubectl'){
                withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl get pods"
              }
           }
           container('helm'){
                sh "helm init --upgrade"
                sh "sleep 40"
                sh "helm upgrade --install --force addrbook --set imageversion=$tag addressbook"
           }
    }
    
}

return this
