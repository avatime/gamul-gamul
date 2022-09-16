FROM node:16.15.0 as build-stage
WORKDIR /var/jenkins_home/workspace/deploytest/testproject_react
COPY package*.json ./
RUN npm i -y
COPY . .
COPY --from=build-stage /var/jenkins_home/workspace/frontend/front/deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
CMD [ "npm", "run", "dev"]