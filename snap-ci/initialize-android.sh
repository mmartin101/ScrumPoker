#!/bin/bash

# raise an error if any command fails!
set -e

INITIALIZATION_FILE="$ANDROID_HOME/.initialized-dependencies-$(git log -n 1 --format=%h -- $0)"

if [ ! -e ${INITIALIZATION_FILE} ]; then
  # fetch and initialize $ANDROID_HOME
  download-android
  # Use the latest android sdk tools
  echo y | android update sdk --no-ui --filter platform-tools > /dev/null
  echo y | android update sdk --no-ui --filter tools > /dev/null

  # The BuildTools version used by your project
  echo y | android update sdk --no-ui --filter build-tools-21.0.2 --all > /dev/null

  echo y | android update sdk --no-ui --filter extra-android-support --all > /dev/null
  echo y | android update sdk --no-ui --filter extra-google-m2repository --all > /dev/null
  echo y | android update sdk --no-ui --filter extra-android-m2repository --all > /dev/null

  # The SDK version used to compile your project
  echo y | android update sdk --no-ui --filter android-21 > /dev/null

  # Specify at least one system image if you want to run emulator tests
  echo y | android update sdk --no-ui --filter sys-img-x86-android-21 --all > /dev/null
  echo y | android update sdk --no-ui --filter sys-img-x86-android-19 --all > /dev/null
  echo y | android update sdk --no-ui --filter sys-img-x86-android-10 --all > /dev/null

  touch ${INITIALIZATION_FILE}
fi