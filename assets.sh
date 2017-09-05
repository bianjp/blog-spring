#!/usr/bin/bash
# Build assets, or serve assets via Broccoli server

buildDir=build/assets

restartBroccoliServer() {
  pkill broccoli
  broccoli serve&
}

cleanAssets() {
  rm -rf ${buildDir}
}

if [[ "$1" == "build" ]]; then
  mkdir -p ${buildDir}
  cleanAssets
  broccoli build build/assets
elif [[ "$1" == "clean" ]]; then
  cleanAssets
else
  restartBroccoliServer
  # Restart when Brocfile.js changed. Require inotify-tools installed
  while inotifywait -e delete_self -e modify Brocfile.js; do
    restartBroccoliServer
   done
fi
