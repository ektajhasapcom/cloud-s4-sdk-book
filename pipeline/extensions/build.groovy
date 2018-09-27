def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
 
  podTemplate(label     : 'docker-node',
            containers: [containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat')], volumes: [
                  hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
             hostPathVolume(hostPath: '/home/jenkins/workspace/', mountPath: '/home/jenkins/workspace/')]) {
             node('docker-node') {
                container(name: 'docker') {
                    try {
                         sh  "ls -lrt"
                         unstashFiles script: this, stage: stageName
                         dockerExecute(script:this, dockerImage:'maven'){
                             sh "mvn clean install -Dmaven.test.failure.ignore=true"   
                         }
                         sh "docker version"
                         sh "ls -lrt"
                         sh "docker build -t javaim:v1 application/Dockerfile"
                    } finally {
                        echo "Finally"
                     }
                }
            }
        }
}

return this
