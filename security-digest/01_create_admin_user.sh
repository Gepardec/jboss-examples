#$JBOSS_HOME/bin/add-user.sh admin jboss_123 ManagementRealm --silent=true 

USERS_FILE=$JBOSS_HOME/standalone/configuration/mgmt-users.properties

USER="admin"
PWD="admin@123"

echo $USER=`echo -n $USER:ManagementRealm:$PWD | openssl dgst -md5 -hex | cut -d" " -f2` > $USERS_FILE

