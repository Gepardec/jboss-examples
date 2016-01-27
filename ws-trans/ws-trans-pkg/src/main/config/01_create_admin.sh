
if [  x$JBOSS_IS_INSTALLED != x ]; then
    echo "INFO: JBOSS_IS_INSTALLED ist gesetzt. Admin-User wird nicht installiert." 1>&2
    exit 0
fi


USERS_FILE=$JBOSS_HOME/standalone/configuration/mgmt-users.properties

USER="admin"
PWD="jboss"

echo $USER=`echo -n $USER:ManagementRealm:$PWD | openssl dgst -md5 -hex | cut -d" " -f2` > $USERS_FILE

