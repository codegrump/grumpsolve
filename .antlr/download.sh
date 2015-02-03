#!/bin/bash

set -e

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
cd ${SCRIPT_DIR}

if [ ! -f "antlr-4.5-complete.jar" ]; then
curl http://www.antlr.org/download/antlr-4.5-complete.jar > antlr-4.5-complete.jar
fi

