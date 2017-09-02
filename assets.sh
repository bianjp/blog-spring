#!/usr/bin/bash
# Build assets, or serve assets via Broccoli server

restartBroccoliServer() {
  pkill broccoli
  broccoli serve&
}

if [[ "$1" == "build" ]]; then
  [[ -e build ]] || mkdir build
  rm -rf build/assets/ && broccoli build build/assets
else
  restartBroccoliServer
  # Restart when Brocfile.js changed. Require inotify-tools installed
  while inotifywait -e delete_self -e modify Brocfile.js; do
    restartBroccoliServer
   done
fi
