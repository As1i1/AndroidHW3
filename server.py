import time
from database import USERS, DEBTS, DEBT_ITEMS
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
        if phone_number == user['phoneNumber'] and password == user['password']:
            return jsonify(user), 200
    return jsonify({'error': 'user not found'}), 404


@app.route('/v1/user/<int:id>/')
def user_me(id):
    for user in USERS:
        if id == user['id']:
            return jsonify(user), 200
    return jsonify({'error': 'user not found'}), 404


@app.route('/v1/user/<int:id>/debt/')
def get_debts(id):
    res = []
    for debt in DEBTS:
        summary = 0
        if id not in debt['ids_guests']:
            continue
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
                "summary": summary,
                "status": debt['status']
            }
        )
    return jsonify(res), 200


@app.route('/v1/debt/<int:debt_id>/items/')
def debt_items(debt_id: int):
    items = []
    owner_id = 0
    phone_number = ""
    for debt_item in DEBT_ITEMS:
        if debt_item["debt_id"] != debt_id:
            continue
        items.append(debt_item)
    for debt in DEBTS:
        if debt["id"] != debt_id:
            continue
        owner_id = debt["owner_id"]
    for user in USERS:
        if user["id"] != owner_id:
            continue
        phone_number = user["phoneNumber"]
    return jsonify(items), 200


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=8080)
