### STAGE 1: Build ###
FROM node:12.7-alpine AS build
COPY package.json package-lock.json ./
WORKDIR /usr/src/app
COPY . .
EXPOSE 8081
RUN npm run ng build -- --prod --output-path=dist

### STAGE 2: Setup ###
FROM nginx:1.17.1-alpine
COPY nginx-converter-fe.conf /etc/nginx/conf.d/
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build /usr/src/app/dist /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]