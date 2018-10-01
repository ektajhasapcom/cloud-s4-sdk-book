def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    withKubeConfig([credentialsId: 'k8s-credentials',
                    caCertificate: '<ca-certificate>',
                    serverUrl: ''
                    contextName: ''
                    ]) {
            sh 'kubectl get pods'
    }
}

return this
