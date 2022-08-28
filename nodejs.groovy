job('NodeJsExample') {
  scm {
    git('https://github.com/hyojeong-jang/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
      node / gitConfigName('DSL User')
      node / gitConfigEmail('hyojeong@dable.io')
      }
    }
  triggers {
    scm('H/5 * * * *')
  }
  wrappers {
    nodejs('node16') // this is the name of the NodeJS installation in Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
  steps {
    dockerBuildAndPublish {
      repositoryName('helloworld')
      tag('${GIT_REVISION,length=7}')
      registryCredentials('ecr')
      dockerRegistryURL('https://116927014662.dkr.ecr.ap-northeast-2.amazonaws.com/helloworld')
      forcePull(false)
      forceTag(false)
      createFingerprints(false)
      skipDecorate()
    }
  }
}