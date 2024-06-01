#!/usr/bin/env groovy
def call(){
//comment
    node ('docker'){
        checkoutFromRepo(branch: 'main', repoURL: 'https://github.com/Maniako97/cloudcamp-jenkins-lab', 'git-credentials')

        buildDockerfile("python-hello-world:1.0.0-beta.1")

        //pushDockerImage()
        
    }
}