
const DEFAULT_MESSAGE = `안녕하세요. 2-frame AI 챗봇입니다. 무엇이 필요하신가요?`;
import {useCallback, useMemo, useRef, useState} from "react";

export const useChatRoom = () => {
  const [chatHistories, setHistories] = useState<ChatHistory[]>(createInit());
  const [selectedHistory, selectHistory] = useState<number | undefined>(undefined);
  const [text, setText] = useState<string>("");
  const [currentRoomStatus, setCurrentRoomStatus] = useState<RoomStatus>("CHAT");
  const historyIdRef = useRef<number>(1); // historyId를 안전하게 관리


  console.log(chatHistories);


  const handleSubmit = useCallback(() => {
    setText("");
  }, [setText]);

  const handleSelectHistory = useCallback((historyId: number) => {
    setCurrentRoomStatus("CHAT");
    selectHistory(historyId);
  }, [setCurrentRoomStatus, selectHistory]);

  const appendHistories = useCallback(() => {
    const newHistoryId = historyIdRef.current++;
    console.log(newHistoryId);
    setHistories((histories) => [
      ...histories,
      {
        id: newHistoryId,
        lastOpenDate: new Date(),
        lastMessage: DEFAULT_MESSAGE,
        chatList: [createNewChat(DEFAULT_MESSAGE, "BOT")],
      },
    ]);
    handleSelectHistory(newHistoryId);
  }, [setHistories, handleSelectHistory, historyIdRef]);

  const appendChat = useCallback((question: string, type: ChatType) => {
    if (selectedHistory === undefined) return;

    console.log(">>>>>", selectedHistory);

    setHistories((histories) =>
      histories.map((history) =>
        history.id === selectedHistory
          ? {
            ...history,
            chatList: [...history.chatList, createNewChat(question, type)],
          }
          : history
      )
    );
    handleSubmit();
  }, [selectedHistory, setHistories, handleSubmit]);



  const computedChatList = useMemo(() => {
    if (selectedHistory === undefined) return [];
    const selectedHistoryObj = chatHistories.find((history) => history.id === selectedHistory);
    return selectedHistoryObj ? selectedHistoryObj.chatList : [];
  }, [selectedHistory, chatHistories]);

  const backHistory = useCallback(() => {
    selectHistory(undefined);
    setCurrentRoomStatus("HISTORY");
  }, [selectHistory, setCurrentRoomStatus]);


  return {
    chatHistories,
    appendHistories,
    appendChat,
    computedChatList,
    handleSelectHistory,
    backHistory,
    isHistory: currentRoomStatus === "HISTORY",
    text,
    setText,
    handleSubmit,
  };
};

// 초기 상태 생성 함수 (샘플 구현)
const createInit = (): ChatHistory[] => {
  return [
    {
      id: 0,
      lastOpenDate: new Date(),
      lastMessage: "Welcome to the chat!",
      chatList: [createNewChat("Welcome to the chat!", "BOT")],
    },
  ];
};

const createNewChat = (message: string, type: ChatType): Chat => ({
  message,
  type,
  timestamp: new Date(),
});

export type ChatType = "BOT" | "USER";
export type RoomStatus = "CHAT" | "HISTORY";

export interface Chat {
  message: string;
  type: ChatType;
  timestamp: Date;
}

export interface ChatHistory {
  id: number;
  lastOpenDate: Date;
  lastMessage: string;
  chatList: Chat[];
}