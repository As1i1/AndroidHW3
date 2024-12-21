from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/v1/user/auth/')
def auth_user():
  return jsonify({"id": 1, "name": "John", "phoneNumber": "1234", "bankName": "Yandex"})

app.run(host="0.0.0.0", port=8080)