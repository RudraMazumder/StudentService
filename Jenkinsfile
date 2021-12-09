pipeline {
	agent any
	stages {
		stage("build") {
		
			steps {
			    echo 'Building..'
			    sh 'mvn clean package -DskipTests'
			}			
		}
		
		stage("test") {
				steps {
				    echo 'Testing..'
			}
		}
		
		stage("Deploy") {
				steps {
			    echo 'Deploying..'
			}
		}
	}

}