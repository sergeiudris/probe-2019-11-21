FROM clojure:lein-2.8.3 
# FROM respace.base2

WORKDIR /opt/app
# ADD . /clj
ADD project.clj .
# VOLUME /opt/app



RUN lein deps
# RUN lein cljsbuild once
# CMD lein figwheel
# This command will start figwheel and open a repl socket
# This would need to change for a live environment -- you wouldn't want
# the figwheel socket open in production, but I'll leave that bit as an
# exercise for the reader.

# 3449 is default http and websocket port that figwheel uses to communicate
EXPOSE 3449 3559 7888 7889
# 7888 is the default nrepl port