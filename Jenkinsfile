pipeline {
	agent any
	triggers {
   		 pollSCM('* * * * *') // every minute
  	}
	stages {
		stage("build") {
		
			steps {
			    echo 'Building..'
			    bat 'mvn clean package -DskipTests'
			}			
		}
		
		
		stage("test") {
				steps {
				  bat 'mvn clean package'
			}
			
			post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    jacoco( 
				      execPattern: 'target/*.exec',
				      classPattern: 'target/classes',
				      sourcePattern: 'src/main/java',
				      exclusionPattern: 'src/test*'
					)
                    
                }
            }
		}
		
		stage("deploy") {
				steps {
			    echo 'Deploying..'
			}
		}
	}

}