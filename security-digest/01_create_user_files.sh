CONF_DIR=$JBOSS_HOME/standalone/configuration

PICKETBOX_JAR="$JBOSS_HOME/modules/system/layers/base/org/picketbox/main/picketbox-[1-9]*.jar"
DIGEST=org.jboss.security.auth.callback.RFC2617Digest

echo PICKETBOX_JAR=`ls $PICKETBOX_JAR`
echo DIGEST=$DIGEST

PWD=`java -cp $PICKETBOX_JAR $DIGEST erhard "Secure Application" erhard@123 | cut -d" " -f4` || exit 1

cat <<EOF >$CONF_DIR/myapps-users.properties
erhard=$PWD
EOF

cat <<EOF >$CONF_DIR/myapps-roles.properties
erhard=users
EOF
