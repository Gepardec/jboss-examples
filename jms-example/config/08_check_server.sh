let JBOSS_JSON_PORT=$JBOSS_ADMIN_PORT-9

curl="curl  -s --digest -u admin:admin@123"
base_url="http://localhost:$JBOSS_JSON_PORT/management"

show() {
	server_name=$1
	attribute=$2
	command="subsystem/messaging/hornetq-server/$server_name?operation=attribute&name=$attribute"
	echo "$server_name	$attribute	"`$curl "$base_url/$command"`
}

show default started
show default active
show backup started
show backup active
# show backup failover-on-shutdown

