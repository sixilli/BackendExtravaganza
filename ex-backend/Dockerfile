FROM alpine:latest

RUN apk update \
    && apk add postgresql --repository=http://dl-cdn.alpinelinux.org/alpine/edge/main \
    && apk add elixir \
    && apk add inotify-tools \
    && mix local.hex --force \
    && mix archive.install --force hex phx_new 1.5.7 \
    && mix local.rebar --force

WORKDIR /elixir/src

EXPOSE 12082
CMD ["mix", "phx.server"]

#docker exec -i -t tekkenhub_elixir_api_1 ash