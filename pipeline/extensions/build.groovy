def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
  unstashFiles script: this, stage: stageName
  dockerExecute(script:this, dockerImage:'maven'){
       sh "mvn package"   
  }
   
  sh  "ls -lrt"
  podTemplate(label     : 'docker-node',
            containers: [containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat')], volumes: [
                  hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]) {
             node('docker-node') {
                container(name: 'docker') {
                    try {
                        sh "docker version"
                        sh "docker build -t javaim:v1 ."
                    } finally {
                        echo "Finally"
                     }
                }
            }
        }
}

return this
