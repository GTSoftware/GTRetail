#!/bin/bash

SKIP_TESTS=""
clean=" clean "
report=" surefire-report:report "
release=""

usage="$(basename "$0") [-h] [-T] [-c] [-r] [-R] -- Script for compiling application

where:
    -h  show this help text
    -T  without tests
    -c  no clean
    -r without surefire reports
    -R prepare the release
"

while getopts ':hTrcR :' option; do
	case "$option" in
	h) echo "$usage"
		exit
		;;
	T) SKIP_TESTS=" -DskipTests=true "
		;;
	c) clean=""
		;;
  r) report=""
    ;;
  R) release=" -Dresume=false release:prepare "
    ;;
	\?) printf "Illegal option: -%s\n" "$OPTARG" >&2
		echo "$usage" >&2
		exit 1
		;;
	esac
done

MAVEN_OPTS="-DXms2048m"

echo "Executing: mvn $MAVEN_OPTS $clean install -T 4 $SKIP_TESTS $report $release"
mvn $MAVEN_OPTS $clean install -T 4 $SKIP_TESTS $report $release