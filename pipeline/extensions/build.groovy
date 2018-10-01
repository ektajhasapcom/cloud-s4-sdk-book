def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
   podTemplate(label: 'build-node', yaml: """
 
    apiVersion: build.knative.dev/v1alpha1
    kind: Build
    metadata:
      name: addressbook
      namespace: default
    spec:
      serviceAccountName: knative-build-service-account
      source:      
        git:
          url: https://github.com/ektajhasapcom/cloud-s4-sdk-book.git
          revision: master
      steps:
      - name: maven-build
        image: maven
        args: ['mvn','install']
      - name: docker-build-and-push
        image: gcr.io/kaniko-project/executor:v0.1.0
        args:  #See kaniko docs for more info about the arguments passed
        - --dockerfile=/workspace/Dockerfile
        - --destination=docker.io/ektajha/addressbook 
 
         """
		 ) {
             node('build-node') {
                sh "Build and push"
            }
        }
}

return this
