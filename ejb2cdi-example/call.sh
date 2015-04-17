#!/bin/sh

anz=10
count=1
funktion=normal
parallel=false

PRG=$0

#####################################################################
##								print_usage
#####################################################################
print_usage(){
cat <<EOF 1>&2
usage: $PRG [-h] [-a n] [-c n] [-p] [-f funktion]
Optionen:
    a n: Anzahl der Servlet-Aufrufe
    c n: Anzahl der Bean-Aufrufe pro Servlet-Aufruf
    p: Aufrufe parallel
    f funktion: Funktion im Servlet:
		normal  (default)
		cached
		injected
    h: Diese Hilfeseite

Ruft Servlet für Webeservicetests auf.
EOF
}

#####################################################################
##								call_http
#####################################################################
call_http(){
	curl -s "http://localhost:8080/ws-client-1.0.0-SNAPSHOT/ejbws?count=$count&function=$funktion" | grep -q OK || date >> tmp_fehler.txt
}

#####################################################################
##								MAIN
#####################################################################

######################   Optionen bestimmen ###################
 
while getopts "a:c:pf:h" option  
do 
    case $option in
      a) anz=$OPTARG;;
      c) count=$OPTARG;;
      p) parallel="true";;
      f) funktion=$OPTARG;;
      *) 
        print_usage
        exit 1
        ;;
    esac 
done

shift `expr $OPTIND - 1`

##################### Beginn #########################

:> tmp_fehler.txt
while [ $anz -gt 0 ]; do
	echo "--- $anz $count ---"
	let anz=$anz-1
	if  [ "true" = $parallel ]; then
		call_http &
	else
		call_http
	fi
done
wait
echo Fehler: `wc -l tmp_fehler.txt`

