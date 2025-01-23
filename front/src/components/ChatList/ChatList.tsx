import {Avatar, Button} from "@heroui/react";
import Typo from "../@common/Typo";
import {Chat} from "../ChatRoom/useChatRoom.ts";


const ChatMessage = () => {
  return (
    <div>
      <div
        className='ml-5 flex flex-col w-full max-w-[50%] leading-1.5 p-4 rounded-l-xl rounded-b-xl rounded-es-xl bg-gray-100'>
        <p className={"text-sm font-normal"}>
          message
        </p>
      </div>
      <div className="flex gap-3 items-center">
        <Avatar size="sm"/>
        <Typo.H6 text={"2-frame-bot"} className="text-sm"/>
        <Button variant="light" isLoading>생성중 잠시만 기다려주세요</Button>
      </div>
    </div>
  )
}

const UserMessage = () => {
  return (
    <div className="w-full flex flex-row-reverse">
      <div className='ml-5 flex flex-col w-[50%] leading-1.5 p-4 rounded-l-xl rounded-b-xl rounded-es-xl bg-blue-50'>
        <p className={"text-sm font-normal"}>
          message
        </p>
      </div>
    </div>
  )
}

const ChatList = ({chatList}: {chatList: Chat[]}) => {

  return (
    <>
      {chatList.map(chat=> chat.type === "BOT" ? <ChatMessage key={chat.id}/> : <UserMessage key={chat.id }/>)}
    </>
  )
}

export default ChatList;