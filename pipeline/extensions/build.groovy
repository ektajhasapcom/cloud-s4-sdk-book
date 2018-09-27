def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
 
  podTemplate(label     : 'docker-node',
            containers: [containerTemplate(name: 'maven', image: 'maven', ttyEnabled: true, command: 'cat'),
                         containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat')], volumes: [
                  hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]) {
             node('docker-node') {
                container('maven') {
                    sh  "ls -lrt"
                    unstashFiles script: this, stage: stageName
                    sh "mvn clean install -Dmaven.test.failure.ignore=true" 
                }
                container(name: 'docker') {
                    try {
                         sh "docker version"
                         sh "ls -lrt"
                    } finally {
                        echo "Finally"
                     }
                }
            }
        }
}

return this
