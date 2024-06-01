def call (Map parameters){
        
    stage ('Build') {
        sh "exec docker build -t ${parameters.tag} ${parameters.path} ${parameters.context}"
    }
}