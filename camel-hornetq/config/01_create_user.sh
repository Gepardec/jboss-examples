RoleFile=$JBOSS_HOME/standalone/configuration/application-roles.properties

$JBOSS_HOME/bin/add-user.sh admin admin@123 ManagementRealm
$JBOSS_HOME/bin/add-user.sh -a -u guest -p guest@123 -g guest

# sed -i 's/^.*guest=.*/guest=guest/' $RoleFile
#sed -i 's/^.*guest=/#&/' $RoleFile
#echo  >> $RoleFile
#echo guest=guest >> $RoleFile
