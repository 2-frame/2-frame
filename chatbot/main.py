from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates

from chatbot import get_ai_response
from models import MessageRequest, MessageResponse

app = FastAPI()
templates = Jinja2Templates(directory="templates")

@app.middleware("http")
async def log_request_body(request: Request, call_next):
    # 요청 본문 읽기 (body는 스트림이므로 복사해서 사용)

    body = await request.body()
    print(">>", request.headers)
    print("요청 본문 확인:", body.decode("utf-8"))  # 요청 본문 출력

    # 요청 처리 계속 진행
    response = await call_next(request)
    return response

# (1) 기본 라우트: 웹페이지 UI 제공
@app.get("/", response_class=HTMLResponse)
def index(request: Request):
    return templates.TemplateResponse("index.html", {"request": request})

# (2) POST /chat: 챗봇 API
@app.post("/chat", response_model=MessageResponse)
def chat_with_bot(request: MessageRequest):
    session_id = "demo-session"  # 데모용 세션
    user_question = request.question
    print(user_question)
    answer = get_ai_response(session_id, user_question)
    print(answer)
    return MessageResponse(answer=answer)