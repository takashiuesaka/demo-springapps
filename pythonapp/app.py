from flask import Flask

app = Flask(__name__)


@app.route("/")
def main():
    return "<p>Hello, World! This is Flask App!</p>"


if __name__ == '__main__':
    app.run()
