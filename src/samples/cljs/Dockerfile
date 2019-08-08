FROM probe.base1

RUN curl -sL https://deb.nodesource.com/setup_11.x | sudo -E bash - && \
    sudo apt-get install -y nodejs 
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add - && \
    echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list && \
    sudo apt-get update && sudo apt-get -y install yarn

WORKDIR /opt/app



# COPY package.json deps.edn shadow-cljs.edn ./
# RUN yarn global add shadow-cljs && yarn info
    
EXPOSE 4001 9500 3449 7888 9000


# CMD ["bash","c", "nrepl_clj"]
CMD ["bash","c", "dev"]