#!/bin/sh

error=0

if [ -z "$JBOSS_HOME" ]; then
	echo "Error: JBOSS_HOME ist nicht gesetzt!" 1>&2
	error=1
fi

if [  x$JBOSS_IS_INSTALLED != x ]; then
    echo "INFO: JBOSS_IS_INSTALLED ist gesetzt. JBoss wird nicht installiert." 1>&2
	exit $error
fi


if [ -z "$JBossPackage" ]; then
	JBossPackage=$1
fi

if [ ! -f "$JBossPackage" ]; then
    echo "ERROR: JBoss Package $JBossPackage existiert nicht! Setzen Sie JBossPackage oder verwenden Sie ein Argument" 1>&2
    error=1
fi

if [ -e "$JBOSS_HOME" ]; then
    echo "WARNING: JBOSS_HOME $JBOSS_HOME existiert und wird nicht ueberschrieben!" 1>&2
    error=1
fi

test $error = 0 || exit $error

TmpInstall=${JBOSS_HOME}_Tmp$$
test -d $TmpInstall || mkdir -p $TmpInstall

echo unzip $JBossPackage to $JBOSS_HOME
unzip -d $TmpInstall $JBossPackage > /dev/null
mv $TmpInstall/* $JBOSS_HOME
rm -r $TmpInstall

