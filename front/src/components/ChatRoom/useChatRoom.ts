import {useState} from "react";

type ChatType = "BOT" | "USER";

type Chat = {
  id: number;
  message: string;
  type: ChatType
}

type ChatHistory = {
  id: number;
  lastOpenDate: Date;
  lastMessage: string;

  chatList: Chat[]
}

let historyId = 1;
let chatId = 1;


const DEFAULT_MESSAGE = `안녕하세요. 2-frame AI 챗봇입니다. 무엇이 필요하신가요?`;

const createNewChat = (question: string, type: ChatType ): Chat=> {
  return {
    id: chatId++,
    message: question,
    type,
  }
}

export const useChatRoom = () => {
  const [chatHistories, setHistories] = useState<ChatHistory[]>([]);

  const appendHistories = () => {
    setHistories(histories => ([...histories, {
      id: historyId++,
      lastOpenDate: new Date(),
      lastMessage: DEFAULT_MESSAGE,
      chatList: []
    }]));
  }

  const appendChat= (historyId: number, question: string, type: ChatType) => {
    setHistories(histories => histories.map(history => {
      if(history.id === historyId) return history;
      return {
        ...history,
        chatList: [...history.chatList, createNewChat(question, type)]
      }
    }));
  }


  return {
    chatHistories,
    appendHistories,
    appendChat
  }

}