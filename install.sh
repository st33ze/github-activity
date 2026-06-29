#!/usr/bin/env sh

set -e

APP_NAME="github-activity"
INSTALL_DIR="/usr/local/lib/$APP_NAME"
BIN_DIR="/usr/local/bin"

echo "Installing $APP_NAME..."

 # Build fat jar
 ./gradlew shadowJar

 # Create install directory
 sudo mkdir -p "$INSTALL_DIR"

 # Copy jar
 sudo cp build/libs/*-all.jar "$INSTALL_DIR/github-activity.jar"

 # Copy CLI script
 sudo cp github-activity "$BIN_DIR/github-activity"
 sudo chmod +x "$BIN_DIR/github-activity"

 echo "Installed successfully!"
 echo "Run: github-activity <username>"