CREATE SCHEMA oauth;
COMMENT
ON schema oauth  IS 'schema tables auth server';

--ALTER ROLE admin SET search_path TO public,oauth;


CREATE TABLE oauth.clientdetails
(
    appid                  character varying(256) NOT NULL,
    resourceids            character varying(256),
    appsecret              character varying(256),
    scope                  character varying(256),
    granttypes             character varying(256),
    redirecturl            character varying(256),
    authorities            character varying(256),
    access_token_validity  integer,
    refresh_token_validity integer,
    additionalinformation  character varying(4096),
    autoapprovescopes      character varying(256),
    PRIMARY KEY (appid)
);


CREATE TABLE oauth.oauth_access_token
(
    token_id          character varying(256),
    token             bytea,
    authentication_id character varying(256) NOT NULL,
    user_name         character varying(256),
    client_id         character varying(256),
    authentication    bytea,
    refresh_token     character varying(256),
    expiresat         timestamp without time zone,
    etablissement_id  bigint,
    createdon         timestamp without time zone default now(),
    PRIMARY KEY (authentication_id)
);



CREATE TABLE oauth.oauth_approvals
(
    userid         character varying(256),
    clientid       character varying(256),
    scope          character varying(256),
    status         character varying(10),
    expiresat      timestamp without time zone,
    lastmodifiedat timestamp without time zone
);

CREATE TABLE oauth.oauth_client_details
(
    client_id               character varying(256) NOT NULL,
    resource_ids            character varying(256),
    client_secret           character varying(256),
    scope                   character varying(256),
    authorized_grant_types  character varying(256),
    web_server_redirect_uri character varying(256),
    authorities             character varying(256),
    access_token_validity   integer,
    refresh_token_validity  integer,
    additional_information  character varying(4096),
    autoapprove             character varying(256),
    PRIMARY KEY (client_id)
);



CREATE TABLE oauth.oauth_client_token
(
    token_id          character varying(256),
    token             bytea,
    authentication_id character varying(256) NOT NULL,
    user_name         character varying(256),
    client_id         character varying(256),
    PRIMARY KEY (authentication_id)
);


CREATE TABLE oauth.oauth_code
(
    code           character varying(256),
    authentication bytea
);


CREATE TABLE oauth.oauth_refresh_token
(
    token_id       character varying(256),
    token          bytea,
    authentication bytea
);