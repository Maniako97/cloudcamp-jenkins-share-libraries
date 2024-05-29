#!/usr/bin/env groovy
//comment
node ('docker'){
    checkoutFromRepo('main', 'https://github.com/Maniako97/cloudcamp-jenkins-lab', 'git-credentials')

    buildDockerfile("python-hello-world:1.0.0-beta.1")

    //pushDockerImage()
    
}
//metodos
def checkoutFromRepo(branch, repoURL, credentialsID)
{
    stage('Checkout') {
        checkout scmGit(
                        branches: 
                        [[name: branch]], 
                        extensions: [], 
                        userRemoteConfigs: [[url: repoURL, credentialsId: credentialsID]]
                        ) 
    }
}

def buildDockerfile(tag, context=".", fileArg="")
{
    if (fileArg=="")
    {
        path=""
    }
    else{
        path="-f ${fileArg}"
    }
    
    stage ('Build') {
        sh "exec docker build -t ${tag} ${path} ${context}"
    }
}

def pushDockerImage()
{
    stage ('Push') {
        sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 145675154975.dkr.ecr.us-east-1.amazonaws.com'
        sh 'docker tag python-hello-world:1.0.0-beta.1 145675154975.dkr.ecr.us-east-1.amazonaws.com/hello-world-python:1.0.0-beta.1'
        sh 'docker push 145675154975.dkr.ecr.us-east-1.amazonaws.com/hello-world-python:1.0.0-beta.1'
    }
}