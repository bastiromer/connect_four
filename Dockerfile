#
# --Note
#
# Docker needs to be installed
#
# build image with: docker build -t connect_four-image .
# run docker container with: docker run -t connect_four-image
#
# To emulate the GUI properly make sure XMing(X Server) is installed and running (Windows only).
#


# base image for scala and sbt
FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

RUN apt-get update && apt-get install -y \
    libxrender1 libxtst6 libgl1-mesa-glx libgtk-3-0 \
    libcanberra-gtk-module libcanberra-gtk3-module

ENV DISPLAY=host.docker.internal:0
WORKDIR /connect_four
ADD . /connect_four

CMD sbt run