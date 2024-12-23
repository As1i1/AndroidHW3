import time
from backend.database import USERS, DEBTS, DEBT_ITEMS
from flask import Flask, jsonify, request

app = Flask(__name__)
sleep_time = 2


@app.before_request
def before_request():
    time.sleep(sleep_time)


@app.route('/v1/user/auth/')
def auth_user():
    phone_number = request.args.get('phoneNumber')
    password = request.args.get('password')
    for user in USERS:
        if phone_number == user['phone_number'] and password == user['password']:
            return jsonify(user), 200
    return jsonify({'error': 'user not found'}), 404


@app.route('/v1/user/me/')
def user_me():
    user_id = request.args.get('id')
    for user in USERS:
        if user_id == user['id']:
            return jsonify(user), 200
    return jsonify({'error': 'user not found'}), 404


@app.route('/v1/debt/')
def get_debts():
    res = []
    for debt in DEBTS:
        summary = 0
        for debt_item in DEBT_ITEMS:
            if debt_item["debt_id"] != debt["id"]:
                continue
            summary += debt_item["summary"]
        res.append(
            {
                "id": debt["id"],
                "owner_id": debt["owner_id"],
                "name": debt["name"],
                "guests": debt["guests"],
                "date": debt["date"],
                "summary": summary
            }
        )
    return jsonify(res), 200


@app.route('/v1/debt/<int:id>/items/')
def debt_items(id_):
    items = []
    owner_id = 0
    phone_number = ""
    for debt_item in DEBT_ITEMS:
        if debt_item["id"] != id_:
            continue
        items.append(debt_item)
    for debt in DEBTS:
        if debt["id"] != id_:
            continue
        owner_id = debt["owner_id"]
    for user in USERS:
        if user["id"] != owner_id:
            continue
        phone_number = user["phone_number"]
    return jsonify({"items": items, "owner_id": owner_id, "phone_number": phone_number}), 200


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=8080)
