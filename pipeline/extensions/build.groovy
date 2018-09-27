def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
  unstashFiles script: this, stage: stageName
  dockerExecute(script:this, dockerImage:'maven'){
       sh "mvn clean install"   
  }
   
    
  podTemplate(label     : 'pod-hugo-app',
            containers: [containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat')], volumes: [
                  hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]) {
             node('pod-hugo-app') {
                container(name: 'docker') {
                    try {
                        sh "docker version"
                        sh "docker build ."
                    } finally {
                        echo "Finally"
                     }
                }
            }
        }
}

return this
