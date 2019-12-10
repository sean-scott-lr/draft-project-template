TODO: (Dec 6th) Json Logging support  https://github.com/quarkusio/quarkus/pull/5585

Tokens I am currently using in the template
```
_serviceName_ : The service name, used by postman and swagger
_entityName_ : The Entity name, used by postman and swagger
_artifactName_ : The Maven artifact name in pom.xml
_dbSchemaName_ : "beverage_service"  ( Or whatever scheme we want to use )
/_serviceRoute_/v0/tenants/{tenantId}/_entityRoute_ :  /beverage/v0/tenants/1/beers
_servicePackage_ : "beverage" The package name to use for the service
_serviceClass_ : "Beverage" Root Class name for the service implementation, and Resource
_serviceinstance_ : "beverageService"
_entityClass_ : "Beer", "BeerReview" Root class name for the entity and the Dao
_entityInstance_ : "beer" root used when referencing instances of Dao and the entity itself
_entityTableName_ : "beer", "beer_review" The db table name for the entity
_entityEntitlement_ : "beer", used in the RBAC roles, and the generated token
TODO: tools/grafana/provisioning/dashboards/Quarkus PoC-1570041033532.json
TODO: tools/prometheus/prometheus.yml
```
To Generate all the tokens
`./mvnw exec:java -Dexec.mainClass=com.logrhythm.svc._servicePackage_.util.jwt.GenerateToken -Dexec.classpathScope=test`

To Generate a specific Token
`./mvnw exec:java -Dexec.mainClass=com.logrhythm.svc._servicePackage_.util.jwt.GenerateToken -Dexec.args="/jwt/token-definitions/_entityName_-view.json" -Dexec.classpathScope=test`

Questions:

* Should we make a docker stack where a schema can be added to an existing postgres ( if there is one ) and the schema is removed when a clean is performed.  This allows a single postgres instance if multiple services are being developed. - YES
* Same as above, but for grafana. - WE WILL CONSOLIDATE ALL SERVICES' GRAFANA DASHBOARDS INTO ONE GRAFANA CONFIG ON DEPLOY AND WILL NEED TO SUPPORT THE SAME IN DEV
* Again, but for Prometheus. - SAME AS ABOVE
* And Again, but for Infinispan. - LIKELY SAME AS ABOVE
* How do we go about Publishing the proto to clients?  How is that managed.  We should probably create a library which contains the proto ( and maybe the generated code too ).  Then clients and the services can both use it. - UNSURE RIGHT NOW
* Should we create "service roles" instead of entitlements?  If so, update the RolesAllowed and the token defs.
* What about roles per tenant in the JWT?
* What about roles per entity in the tenant?
* What about content Level security? Do we put extra garbage into the jwt?
* Core... What about JWT caching?
...

Note: The Core library is embedded in this project and will be moved out when ready.

JEFF NOTES FROM DRIVE:

Access Control
* What is the annotation needed on an endpoint?
* Is there a relationship between the RolesAllowed annotation and the tenantId in the call? YES
* Does it assume some relationship between tags or entities?

Use-cases
* Log ingestion - Service user
* Enrichment - Service user (Thought: do enrichment sources need to be different based on log source [enclave that pushed the data])
* Alarm generation (which is nothing but synthetic signal aka log ingestion) - Service user
* Log searching - End user
* Case management - End user

Questions
* What if we changed tenantId to just "Partition"?
* Would each service be able to decide whether partitions had relationships? or would we support partition sharding by SIP by forcing all cross-partition stuff to be orchestrated?