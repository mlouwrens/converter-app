#==================== Building Stage ================================================ 

# Create the image based on the official Node 8.9.0 image from Dockerhub
FROM node:13.3.0 as node

# Create a directory where our app will be placed. This might not be necessary
RUN mkdir -p /converter-fe

# Change directory so that our commands run inside this new directory
WORKDIR /converter-fe

# Copy dependency definitions
COPY package.json /converter-fe

# Install dependencies using npm
RUN npm install

# Get all the code needed to run the app
COPY . /converter-fe

# Expose the port the app runs in
EXPOSE 8081

#Build the app
RUN npm run build

#==================== Setting up stage ==================== 
# Create image based on the official nginx - Alpine image
FROM nginx:1.17.1-alpine

COPY --from=node /converter-fe/dist/ /usr/share/nginx/html

COPY ./nginx-converter-fe.conf /etc/nginx/conf.d/default.conf

# start app
CMD ng serve --host 0.0.0.0