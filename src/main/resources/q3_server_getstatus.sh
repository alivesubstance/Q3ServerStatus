#!/bin/bash

HOST = $1
PORT = $2

# send 4 ff bytes with getstatus command
# -4 using IPv4 protocol
# -u by UDP
# - w 1 and wait for 1 second for response
echo -ne '\xff\xff\xff\xffgetstatus' | nc -4 -u -w 1 ${HOST} ${PORT}