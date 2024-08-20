const bcrypt = require('bcryptjs');
const bodyParser = require('body-parser');
const db = require('./database');
const express = require('express');
const expressJwt = require('express-jwt');
const jwt = require('jsonwebtoken');

const PORT = 3080;
// const PORT = process.env.PORT || 3000;

/* Middlewaare for parsing bodies of JSON requests */
const app = express();
app.use(bodyParser.json());
/* Check the server is working */
// app.get('/', (req, res) => {
//     res.send("It works");
// });

// Store this value in the config.
const SECRET_KEY = 'your-secret-key';

/** User registration.
 */
app.post('/register', (req, res) => {
    const { username, password } = req.body;
    const hashedPassword = bcrypt.hashSync(password, 8);

    const query = 'INSERT INTO users (username, password) VALUES (?, ?)';
    db.run(query, [username, hashedPassword], function (err) {
        if (err) {
            return res.status(500).send('User already exists');
        }

        const token = jwt.sign({ id: this.lastID }, SECRET_KEY, { expiresIn: '1h' });
        res.status(200).send({ auth: true, token });
    });
});

/** User authentication.
 */
app.post('/login', (req, res) => {
    const { username, password } = req.body;

    if (username === 'admin' && password === 'password') {
        res.status(200).send({ message: 'Successful log in' });
    } else {
        res.status(401).send({ message: 'Wrong credentials' });
    }
});

app.post('/add-event', (req, res) => {
    const { event_type, additional_info } = req.body;
  
    const query = `INSERT INTO events (event_type, additional_info) VALUES (?, ?)`;
  
    db.run(query, [event_type, additional_info], function(err) {
      if (err) {
        res.status(400).send({ message: 'Ошибка добавления события', error: err.message });
      } else {
        res.status(201).send({ message: 'Событие успешно добавлено', eventId: this.lastID });
      }
    });
});

app.get('/events', (req, res) => {
    const query = `SELECT * FROM events ORDER BY event_timestamp DESC`;
  
    db.all(query, [], (err, rows) => {
      if (err) {
        res.status(500).send({ message: 'Ошибка получения событий', error: err.message });
      } else {
        res.status(200).send({ events: rows });
      }
    });
});

/* Running the server */
app.listen(PORT, () => {
    console.log(`The server is running http://localhost:${PORT}`);
});