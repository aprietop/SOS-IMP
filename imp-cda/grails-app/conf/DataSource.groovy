dataSource {
    pooled = true
    driverClassName = "org.hsqldb.jdbcDriver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
		dataSource {
//          dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//			url = "jdbc:hsqldb:mem:devDB"

	       // pooling = true
	        driverClassName = "com.mysql.jdbc.Driver"
	        url = "jdbc:mysql://localhost:3306/imp_dev?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8"
	        dbCreate = "update"
	        username = "root"
	        password = ""
               // dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'

            //loggingSql = true
		}
	}
    test {
        dataSource {
//          dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//			url = "jdbc:hsqldb:mem:devDB"

	       // pooling = true
	        driverClassName = "com.mysql.jdbc.Driver"
	        url = "jdbc:mysql://localhost:3306/imp_dev?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8"
	        dbCreate = "update"
	        username = "root"
	        password = "dogui7007"
               // dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'

            //loggingSql = true
		}
    }
    production {
        dataSource {
//          dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//			url = "jdbc:hsqldb:mem:devDB"

	       // pooling = true
	        driverClassName = "com.mysql.jdbc.Driver"
	        url = "jdbc:mysql://localhost:3306/imp_dev?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8"
	        dbCreate = "update"
	        username = "root"
	        password = "dogui7007"
               // dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'

            //loggingSql = true
		}
    }
}
