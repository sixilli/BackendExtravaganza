table! {
    blog_posts (id) {
        id -> BigInt,
        title -> Varchar,
        author -> Varchar,
        category -> Integer,
        link -> Varchar,
        text -> Varchar,
        created_at -> Timestamptz,
        updated_at -> Timestamptz,
    }
}

table! {
    tournaments (id) {
        id -> BigInt,
        title -> Varchar,
        description -> Varchar,
        attendees -> Integer,
        region -> Varchar,
        location -> Varchar,
        event_link -> Varchar,
        stream_link -> Varchar,
        start_time -> Timestamp,
        created_at -> Timestamptz,
        updated_at -> Timestamptz,
    }
}