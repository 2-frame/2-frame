from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates

from chatbot import get_ai_response
from models import MessageRequest, MessageResponse

app = FastAPI()
templates = Jinja2Templates(directory="templates")

# (1) 기본 라우트: 웹페이지 UI 제공
@app.get("/", response_class=HTMLResponse)
def index(request: Request):
    return templates.TemplateResponse("index.html", {"request": request})

# (2) POST /chat: 챗봇 API
@app.post("/chat", response_model=MessageResponse)
def chat_with_bot(request: MessageRequest):
    session_id = "demo-session"  # 데모용 세션
    user_question = request.question
    answer = get_ai_response(session_id, user_question)
    return MessageResponse(answer=answer)