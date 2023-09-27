db.createUser(
	{
		user: "panamby",
		pwd: "123456",
		roles: [
			{
				role: "readWrite",
				db: "panamby"
			}
		]
	}
);

db.createCollection("backlog_manager_subscriber");