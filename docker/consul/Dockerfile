FROM hashicorp/consul:1.21.5
COPY ./consul_kv_docker.json /usr/src/consul/
WORKDIR /usr/src/consul
ENV CONSUL_HTTP_ADDR=consul:8500
ENTRYPOINT until consul kv import @consul_kv_docker.json; do echo "Waiting for Consul"; sleep 2; done