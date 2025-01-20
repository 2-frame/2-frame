from pydantic import BaseModel

class MessageRequest(BaseModel):
    question: str

class MessageResponse(BaseModel):
    answer: str