const sqlite3 = require('sqlite3').verbose();

const db = new sqlite3.Database('./backend/talkingpet.db', (err) => {
    if (err) {
        console.error('Error opening database:', err.message)
    } else {
        console.log('Succesfully set up database.');
    }
});

db.serialize(() => {
    db.run(`
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT UNIQUE,
            password TEXT
        )
    `);

    db.run(`
       CREATE TABLE IF NOT EXISTS events (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_id INTEGER,
        event_type TEXT,
        event_timestamp TEXT DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY(user_id) REFERENCES users(id)
       )
    `);
});

module.exports = db;