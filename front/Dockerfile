FROM node:16.16.0 as build-stage
WORKDIR /var/jenkins_home/workspace/frontend/front
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 3000
CMD["npm","run","dev"]
# FROM nginx:stable-alpine as production-stage

# COPY --from=build-stage /var/jenkins_home/workspace/frontend/front/ /usr/share/nginx/html
# #COPY --from=build-stage /var/jenkins_home/workspace/frontend/front/deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
# EXPOSE 80
# CMD ["nginx", "-g","daemon off;"]