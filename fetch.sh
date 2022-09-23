#!/bin/sh
fetch() {
    curl -L "https://github.com/ndbaker1/Squares/blob/master/classes/${file}?raw=true" -o "$(echo "build/$file" | sed "s/%20/ /g")"
}

file="music/Digitalism_-_The_Pulse.wav"
fetch
file="music/dash.wav"
fetch
file="music/Nitro%20Fun-Final%20Boss.wav"
fetch

