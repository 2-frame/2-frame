import {useMemo, useState} from "react";

export type ChatType = "BOT" | "USER";

export type Chat = {
  id: number;
  message: string;
  type: ChatType
}


export type ChatHistory = {
  id: number;
  lastOpenDate: Date;
  lastMessage: string;
  chatList: Chat[]
}


export type RoomStatus = "HISTORY" | "CHAT";

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

const createNewHistory = () => {
  return (
    {
      id: historyId++,
      lastOpenDate: new Date(),
      lastMessage: DEFAULT_MESSAGE,
      chatList: [createNewChat(DEFAULT_MESSAGE, "BOT")]
    }
  )
}

const createInit = (): ChatHistory[] => {
  return [
    createNewHistory()
  ]
}


export const useChatRoom = () => {
  const [chatHistories, setHistories] = useState<ChatHistory[]>(createInit());
  const [selectedHistory, selectHistory] = useState<number>();
  const [text, setText] = useState<string>("");
  const [currentRoomStatus, setCurrentRoomStatus] = useState<RoomStatus>("CHAT");



  const appendHistories = () => {
    setHistories(histories => ([...histories, {
      id: historyId++,
      lastOpenDate: new Date(),
      lastMessage: DEFAULT_MESSAGE,
      chatList: [
        createNewChat(DEFAULT_MESSAGE, "BOT")
      ]
    }]));
    handleSelectHistory(historyId)
  }

  const appendChat= (question: string, type: ChatType) => {
    setHistories(histories => histories.map(history => {
      if(selectedHistory === historyId) return history;

      return {
        ...history,
        chatList: [...history.chatList, createNewChat(question, type)]
      }
    }));
    handleSubmit();
  }

  const handleSelectHistory = (historyId: number) => {
    setCurrentRoomStatus("CHAT");
    selectHistory(historyId);
  }

  const computedChatList = useMemo(() => {
    if (selectedHistory === undefined) return [];
    const findResult = chatHistories.filter(history => history.id == selectedHistory)
    if(findResult.length === 0) return [];
    return findResult[0].chatList;
  }, [selectedHistory, chatHistories, currentRoomStatus])

  const backHistory = () => setCurrentRoomStatus("HISTORY");

  const isHistory = currentRoomStatus === "HISTORY";

  const handleSubmit = () => {

    setText("");
  }


  return {
    chatHistories,
    appendHistories,
    appendChat,
    computedChatList,
    handleSelectHistory,
    backHistory,
    isHistory,
    text, setText, handleSubmit
  }

}