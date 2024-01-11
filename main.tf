provider "aws" {
  region = "us-east-1"
}

resource "aws_security_group" "ec2_sg" {
  name        = "ec2-security-group"
  description = "Security group for EC2 instances"

  ingress {
    from_port = 0
    to_port   = 0
    protocol  = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port = 0
    to_port   = 0
    protocol  = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create an EC2 instance
resource "aws_instance" "web_instance" {
  ami           = "ami-0005e0cfe09cc9050"
  instance_type = "t2.micro"
  vpc_security_group_ids = [aws_security_group.ec2_sg.id]
  user_data = <<-EOF
        yum update -y
			  yum install git docker jq -y
			  systemctl start docker
			  wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
			  sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
			  yum install -y apache-maven
			  mvn --version
			  mkdir /home/ec2-user/accolite
			  cd /home/ec2-user/accolite
			  git clone https://github.com/Jayaram7/Accolite-Repo.git
			  cd Accolite-Repo
			  mvn clean install 
			  docker build -t accoliteapp .
			  docker run -p 8080:8080 -dit accoliteapp
              EOF

  tags = {
    Name = "WebInstance"
  }
}

resource "aws_lb" "web_elb" {
  name               = "web-elb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.ec2_sg.id]
  enable_deletion_protection = false
  enable_cross_zone_load_balancing = true
  tags = {
    Name = "WebELB"
  }
  dynamic "subnet_mapping" {
	for_each = tolist(data.aws_subnets.mysubnets.ids)
	content {
	subnet_id = subnet_mapping.value
	#allocation_id = 
	}
	}
}
data "aws_subnets" "mysubnets" {
filter {
    name   = "vpc-id"
    values = [data.aws_vpc.myvpc.id]
  }

}

data "aws_vpc" "myvpc" {
}


# Attach EC2 instances to the ELB
resource "aws_lb_target_group" "web_target_group" {
  name     = "web-target-group"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = data.aws_vpc.myvpc.id

  health_check {
    path = "/"
    port = 8080
  }
}

resource "aws_lb_target_group_attachment" "web_target_attachment" {
  target_group_arn = aws_lb_target_group.web_target_group.arn
  target_id        = aws_instance.web_instance.id
}

# Create a listener for the ELB
resource "aws_lb_listener" "web_listener" {
  load_balancer_arn = aws_lb.web_elb.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    target_group_arn = aws_lb_target_group.web_target_group.arn
    type             = "forward"
  }
}
