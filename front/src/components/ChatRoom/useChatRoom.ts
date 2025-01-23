import {useQuestionMutation} from "../../hooks";
import {useCallback, useMemo, useRef, useState} from "react";

const DEFAULT_MESSAGE = `안녕하세요. 2-frame AI 챗봇입니다. 무엇이 필요하신가요?`;

export const useChatRoom = () => {
  const [chatHistories, setHistories] = useState<ChatHistory[]>([]);
  const [selectedHistory, selectHistory] = useState<number | undefined>(undefined);
  const [text, setText] = useState<string>("");
  const [currentRoomStatus, setCurrentRoomStatus] = useState<RoomStatus>("HISTORY");
  const historyIdRef = useRef<number>(1); // historyId를 안전하게 관리
  const chatIdRef = useRef<number>(1); // historyId를 안전하게 관리

  const chatMutation = useQuestionMutation();

  const handleSubmit = useCallback(() => {
    appendChat(text, "USER");
    const waitChatId = appendChat(text, "BOT-WAIT")
    chatMutation.mutate({question: text}, {
      onSuccess: ({answer}) => {
        setHistories(histories => histories.map(history => history.id === selectedHistory ? {
          ...history,
          chatList: history.chatList.filter(h => h.id !== waitChatId)
        } : history))
        appendChat(answer, "BOT");
        setText("");
      }
    })
  }, [text, setText]);

  const handleSelectHistory = useCallback((historyId: number) => {
    setCurrentRoomStatus("CHAT");
    selectHistory(historyId);
  }, [setCurrentRoomStatus, selectHistory]);

  const appendHistories = useCallback(() => {
    const newHistoryId = historyIdRef.current++;
    setHistories((histories) => [
      ...histories,
      {
        id: newHistoryId,
        lastOpenDate: new Date(),
        lastMessage: DEFAULT_MESSAGE,
        chatList: [createNewChat(chatIdRef.current++, DEFAULT_MESSAGE, "BOT")],
      },
    ]);
    handleSelectHistory(newHistoryId);
  }, [setHistories, handleSelectHistory, historyIdRef]);

  const appendChat = useCallback((question: string, type: ChatType) => {
    if (selectedHistory === undefined) return;

    const newChat = createNewChat(chatIdRef.current++, question, type);
    setHistories((histories) =>
      histories.map((history) =>
        history.id === selectedHistory
          ? {
            ...history,
            chatList: [...history.chatList, newChat],
          }
          : history
      )
    );
    return newChat.id;
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
      chatList: [createNewChat(0, "안녕하세요~ 2-frame 운영 챗봇입니다. 무엇이 궁금하신가요?", "BOT")],
    },
  ];
};

const createNewChat = (id:number, message: string, type: ChatType): Chat => ({
  id,
  message,
  type,
  timestamp: new Date(),
});

export type ChatType = "BOT" | "USER" | "BOT-WAIT";
export type RoomStatus = "CHAT" | "HISTORY";

export interface Chat {
  id: number;
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