#!/usr/bin/bash
export MIGRAINE_BOT_NAME="migrene_mvp_bot"
export MIGRAINE_BOT_TOKEN=""
export DB_PSWD=""
export DB_HOST=""
gradle fatJar
java -jar ./build/libs/Migraine-bot-all-1.0.jar