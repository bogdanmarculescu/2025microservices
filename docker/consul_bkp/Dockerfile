FROM hashicorp/consul:1.21.5
COPY ./kv_export.json /usr/src/consul/
WORKDIR /usr/src/consul
ENV CONSUL_HTTP_ADDR=consul:8500
ENTRYPOINT until consul kv import @kv_export.json; do echo "Waiting for Consul"; sleep 2; done