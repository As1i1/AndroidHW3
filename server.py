from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/v1/user/auth/')
def auth_user():
  return jsonify({"id": 1, "name": "John", "phoneNumber": "1234", "bankName": "Yandex"})

@app.route('/v1/debt/<int:id>/items/')
def debt_items(id):
  return jsonify(
    [{"id": 1, "name": "gol1", "summary": 52},
  {"id": 2, "name": "gol2", "summary": 53},
    {"id": 3, "name": "gol3", "summary": 54},
    {"id": 4, "name": "gol4", "summary": 55}]
  )

app.run(host="0.0.0.0", port=8080)