CREATE EXTENSION pg_trgm;
CREATE EXTENSION btree_gin;

CREATE TABLE _entityTableName_ (
  tenant_id text NOT NULL,
  id text NOT NULL,
  first_name text NOT NULL,
  last_name text NOT NULL,
  age integer NOT NULL,
  created_on timestamp with time zone DEFAULT statement_timestamp(),
  updated_on timestamp with time zone DEFAULT statement_timestamp(),
  PRIMARY KEY( tenant_id, id )

);

CREATE INDEX idx__entityTableName__last_name_trgm_tenant ON _entityTableName_ USING gin ( last_name gin_trgm_ops, tenant_id );
