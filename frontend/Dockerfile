FROM debian:bullseye-slim as builder

RUN apt-get update && \
    apt-get install -y nodejs npm

RUN mkdir -p /cloudacademy/frontend
WORKDIR /cloudacademy/frontend

COPY public public
COPY src src
COPY package.json package.json
COPY package-lock.json package-lock.json

RUN npm install
RUN npm run build

FROM debian:bullseye-slim

RUN apt-get update && \
    apt-get install -y nginx

COPY --from=builder /cloudacademy/frontend/build /var/www/html/

COPY .env /var/www/html/
COPY env-config.js /var/www/html/

COPY env.sh /tmp
RUN chmod +x /tmp/env.sh

EXPOSE 80

CMD ["/bin/bash", "-c", "cd /var/www/html && /tmp/env.sh && nginx -g \"daemon off;\""]