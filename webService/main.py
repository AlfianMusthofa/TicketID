from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import mysql.connector

# Membuat koneksi ke database MySQL
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="ticket_db"
)

app = FastAPI()

# Mengaktifkan CORS (Cross-Origin Resource Sharing)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Model Pydantic untuk data registrasi
class UserRegistration(BaseModel):
    name: str
    username: str
    nik: str
    phone_number: str

# Model Pydantic untuk data login
class UserLogin(BaseModel):
    username: str
    password: str

# Endpoint untuk registrasi pengguna
@app.post("/register")
def register(user: UserRegistration):
    cursor = db.cursor()
    try:
        # Memeriksa apakah username sudah terdaftar
        cursor.execute("SELECT id FROM user WHERE username = %s", (user.username,))
        result = cursor.fetchone()
        if result:
            raise HTTPException(status_code=400, detail="Username already taken")

        # Menyimpan data pengguna ke database
        cursor.execute("INSERT INTO users (name, username, nik, nohp) VALUES (%s, %s, %s, %s)",
                       (user.name, user.username, user.nik, user.phone_number))
        db.commit()

        return {"message": "Registration successful"}

    except Exception as e:
        db.rollback()
        raise HTTPException(status_code=500, detail=str(e))

# Endpoint untuk login pengguna
@app.post("/login")
def login(user: UserLogin):
    cursor = db.cursor()
    try:
        # Memeriksa apakah username dan password sesuai
        cursor.execute("SELECT id FROM users WHERE username = %s AND password = %s",
                       (user.username, user.password))
        result = cursor.fetchone()
        if result:
            return {"message": "Login successful"}
        else:
            raise HTTPException(status_code=401, detail="Invalid username or password")

    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

