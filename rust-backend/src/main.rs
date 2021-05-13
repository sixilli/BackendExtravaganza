#![feature(proc_macro_hygiene, decl_macro)]
#![allow(proc_macro_derive_resolution_fallback)]

#[macro_use] extern crate rocket;
#[macro_use] extern crate rocket_contrib;
#[macro_use] extern crate log;
#[macro_use] extern crate serde_derive;
#[macro_use] extern crate diesel;
extern crate dotenv;
extern crate serde;
extern crate serde_json;

mod config;
mod schema;
mod api;
mod models;

fn main() {
    config::rocket().0.launch();
}
