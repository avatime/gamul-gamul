FROM node:16.15.0 as build-stage
WORKDIR /var/jenkins_home/workspace/deploytest/testproject_react
COPY package*.json ./
RUN npm i -y
COPY . .
CMD [ "npm", "run", "dev"]