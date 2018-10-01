def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    withKubeConfig([credentialsId: 'k8s-credentials',
                    serverUrl: 'https://api.kymatest.cloudpot.shoot.canary.k8s-hana.ondemand.com'
                    contextName: 'shoot--cloudpot--kymatest'
                    ]) {
            sh 'kubectl get pods'
    }
}

return this
