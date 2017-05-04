#!/usr/bin/env bash
version=1.0.24

wget https://github.com/alibaba/canal/releases/download/canal-$version/canal.deployer-$version.tar.gz -O /tmp/canal.deployer-$version.tar.gz
mkdir -p /opt/canal.deployer-$version && cd /opt/canal.deployer-$version && tar -zxvf /tmp/canal.deployer-$version.tar.gz && ln -s /opt/canal.deployer-$version /opt/canal

cd /opt/canal && rm -rf ./conf/canal.properties
wget https://raw.githubusercontent.com/xiaomatech/binlog2kafka/master/canal/canal.properties ./conf/canal.properties
rm -rf ./conf/example && mkdir ./conf/instance1
wget https://raw.githubusercontent.com/xiaomatech/binlog2kafka/master/canal/instance1/instance.properties ./conf/instance1/instance.properties

/opt/canal/bin/startup.sh
