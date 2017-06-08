#!/bin/bash

current_path=`pwd`
case "`uname`" in
    Linux)
        bin_abs_path=$(readlink -f $(dirname $0))
        ;;
    *)
        bin_abs_path=`cd $(dirname $0); pwd`
        ;;
esac
cd ${bin_abs_path} && ./startup.sh testdb thetable 127.0.0.1:2181 127.0.0.1:9092
