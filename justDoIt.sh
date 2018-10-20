#!/bin/bash

SKIP_TESTS=""
CLEAN=""

usage="$(basename "$0") [-h] [-S] [-c] -- Script for compiling application

where:
    -h  show this help text
    -T  Without tests
    -c  clean"

while getopts ':hSc:' option; do
	case "$option" in
	h) echo "$usage"
		exit
		;;
	T) SKIP_TESTS="-DskipTests=true"
		;;
	c) CLEAN=" clean "
		;;
	\?) printf "Illegal option: -%s\n" "$OPTARG" >&2
		echo "$usage" >&2
		exit 1
		;;
	esac
done

MAVEN_OPTS="-Xms1024m"

mvn  $CLEAN install -T 4 $SKIP_TESTS
