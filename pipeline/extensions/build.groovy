def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    
    podTemplate(label: 'pod-hugo-app', containers: [
    containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat',
        volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')
  ]) {

    node('pod-hugo-app') {

            container('docker') {
                stage('Docker Build & Push Current & Latest Versions') {
                    sh ("docker build -t ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} .")
                    sh ("docker push ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER}")
                    sh ("docker tag ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:latest")
                    sh ("docker push ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:latest")
                }
            }
        }        
    }
  }
}

return this
