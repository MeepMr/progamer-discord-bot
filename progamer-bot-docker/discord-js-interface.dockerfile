### STAGE 1: BUILD ###
FROM node:16-alpine as build

WORKDIR /app

COPY package*.json /app/
RUN npm install

COPY ./src /app/src/
COPY ./tsconfig.json /app/tsconfig.json
RUN npm run build

### STAGE 2: Run ###
FROM node:16-alpine

WORKDIR /app
COPY --from=build /app/package*.json /app
RUN npm install
COPY --from=build /app/dist/ /app

ENV DISCORD_INTERFACE_PORT=8081
ENV DISCORD_INTERFACE_BOT_TOKEN="<Bot-Token>"
EXPOSE 8081

CMD node index.js
