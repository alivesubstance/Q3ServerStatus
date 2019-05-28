#!/usr/bin/env bash

#1. Working example with application/x-www-form-urlencoded
#curl -X POST \
#    -x 185.124.86.10:3128 \
#    -H "Content-Type: application/x-www-form-urlencoded" \
#    -d "chat_id=239922878&text=/status q3.playground.ru:27961" \
#    https://api.telegram.org/bot735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4/sendMessage

curl -X POST \
    -x 185.124.86.10:3128 \
    -H "Content-Type: application/json" \
    -d '{"chat_id":"239922878","text":"Test JSON"}' \
    https://api.telegram.org/bot735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4/sendMessage

