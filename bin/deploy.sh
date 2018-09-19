#!/usr/bin/env bash

rsync -zrv --delete ../target/universal/stage/* kyle@chinook.databaseflow.com:~/apps/chinook.databaseflow.com
