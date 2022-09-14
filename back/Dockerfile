FROM openjdk:8-jdk-alpine
WORKDIR /var/jenkins_home/workspace/backend/back
COPY requirements.txt ./

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
COPY . .
CMD ["gunicorn", "back.wsgi", "--bind", "0.0.0.0:8080"]
