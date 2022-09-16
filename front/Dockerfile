FROM node:16.15.0 as build-stage
WORKDIR /var/jenkins_home/workspace/frontend/front
COPY package*.json ./
RUN npm i -y
COPY . .
# COPY /deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
CMD [ "npm", "run", "dev"]