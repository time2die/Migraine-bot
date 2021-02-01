#!/usr/bin/bash
export MIGRAINE_BOT_NAME="migrene_mvp_bot"
export MIGRAINE_BOT_TOKEN="1656841212:AAGiSeKxc_Z6J3_AeuOGn9DAzSafJy6CNYg"
export DB_PSWD="sx6sRgkE9ej8V2v"
export DB_HOST="91.235.136.131:15432"
#gradle fatJar
java -jar -Xms100m -Xmx100m ./build/libs/Migraine-bot-all-1.0.jar
