@Library('github.com/lachie83/jenkins-pipeline@dev')

def pipeline = new io.estrado.Pipeline()

podTemplate(label: 'jenkinsslave', containers: [
    containerTemplate(name: 'jnlp', image: 's4sdk/jenkins-agent-k8s:latest', args: '${computer.jnlpmac} ${computer.name}', workingDir: '/home/jenkins', resourceRequestCpu: '200m', resourceLimitCpu: '300m', resourceRequestMemory: '256Mi', resourceLimitMemory: '512Mi'),
    containerTemplate(name: 'docker', image: 'docker:1.12.6', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'golang', image: 'golang:1.8.3', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'helm', image: 'lachlanevenson/k8s-helm:v2.6.0', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.4.8', command: 'cat', ttyEnabled: true)
],
volumes:[
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
]){

  node ('jenkins-pipeline') {

  




    stage ('publish container') {

      container('docker') {

        // perform docker login to container registry as the docker-pipeline-plugin doesn't work with the next auth json format
        withCredentials([[$class          : 'UsernamePasswordMultiBinding', credentialsId: 'git',
                        usernameVariable: 'ektajha', passwordVariable: 'Mapapaji@99']]) {
          sh "docker login -u ${env.USERNAME} -p ${env.PASSWORD} ${config.container_repo.host}"
        }
    }


  }
}
