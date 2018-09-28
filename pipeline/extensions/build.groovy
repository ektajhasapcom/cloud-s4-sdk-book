def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
  def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
  def imageName = "ektajha/addressbooklatest"

 
  podTemplate(label     : 'docker-node',
            containers: [containerTemplate(name: 'maven', image: 'maven', ttyEnabled: true, command: 'cat'),
                         containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat')], volumes: [
                  hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]) {
             node('docker-node') {
                container('maven') {
                                checkout scm
                                sh  "ls -lrt"
                                sh "mvn clean install"
                }
                container(name: 'docker') {
                    try {
                         withCredentials([usernamePassword(credentialsId: 'dockerCredentialId', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                                sh "ls -lrt"
                                sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                                sh "docker build -t ${imageName}:${tag} ."
                                sh "docker push ${imageName}:${tag}"   
                         }
                    } finally {
                        checkout scm
                        originalStage()
                        echo "Finally"
                     }
                }
            }
        }
}

return this
