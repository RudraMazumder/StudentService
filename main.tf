provider "aws" {
  region     = "us-east-1"
  shared_credentials_file="C:\\Users\\RUMAZUMD\\.awscredentials"
}

resource "aws_apprunner_auto_scaling_configuration_version" "hello" {                            
  auto_scaling_configuration_name = "hello"
  # scale between 1-5 containers
  min_size = 1
  max_size = 5
}

resource "aws_apprunner_service" "hello" {                            
  auto_scaling_configuration_arn = aws_apprunner_auto_scaling_configuration_version.hello.arn                          
         
  service_name = "hello-app-runner"                          
                            
  source_configuration {                              
    image_repository {                                
      image_configuration {                                  
        port = "8000"                                
      }                                
      
      image_identifier       = "public.ecr.aws/o6s3y6x7/studentservice/hello-app-runner:latest"                                
      image_repository_type = "ECR_PUBLIC"                              
    }                          
                              
    auto_deployments_enabled = false                            
  }                          
}
output "apprunner_service_hello" {                            
  value = aws_apprunner_service.hello                          
}