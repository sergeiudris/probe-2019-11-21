FROM probe.base1

WORKDIR /opt/app

# COPY ./.env /opt/
# ADD ./.m2/settings.xml /root/.m2/


# COPY .  /opt/code/app
# COPY project.clj  /opt/app
# RUN lein deps

# COPY .  .
# COPY c .
COPY project.clj .
# RUN bash c install_deps

EXPOSE 4001
EXPOSE 8080
EXPOSE 7878


# CMD ["bash","c", "nrepl_clj"]
CMD ["bash","c", "dev"]