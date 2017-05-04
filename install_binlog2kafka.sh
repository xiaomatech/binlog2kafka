#!/usr/bin/env bash

wget https://raw.githubusercontent.com/xiaomatech/binlog2kafka/master/binlog2kafka.tar.gz -O /tmp/binlog2kafka.tar.gz
cd /opt && tar -zxvf /tmp/binlog2kafka.tar.gz

/opt/binlog2kafka/bin/startmy.sh
