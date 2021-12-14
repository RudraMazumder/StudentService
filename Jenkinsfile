pipeline {
	agent any
	triggers {
   		 pollSCM('* * * * *') // every minute
  	}
	stages {
		stage("build") {
		
			steps {
			    echo 'Building..'
			    sh 'mvn clean package -DskipTests'
			}			
		}
		
		
		stage("test") {
				steps {
				  sh 'mvn clean test'
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
		
		stage("package") {
				steps {
			    	//sh 'mvn clean install -DskipTests'
			}
		}
	}

}