import {Avatar, Button} from "@heroui/react";
import Typo from "../@common/Typo";
import {Chat} from "../ChatRoom/useChatRoom.ts";


const ChatMessage = ({message}: {message: string}) => {
  return (
    <div>
      <div
        className='ml-5 flex flex-col w-full max-w-[50%] leading-1.5 p-4 rounded-l-xl rounded-b-xl rounded-es-xl bg-gray-100'>
        <p className={"text-sm font-normal"}>
          {message}
        </p>
      </div>
      <div className="flex gap-3 items-center">
        <Avatar size="sm"/>
        <Typo.H6 text={"2-frame-bot"} className="text-sm"/>
      </div>
    </div>
  )
}
const BotWaitMessage = ({message}: {message: string}) => {
  return (
    <div>
      <div className="flex gap-3 items-center">
        <Avatar size="sm"/>
        <Typo.H6 text={"2-frame-bot"} className="text-sm"/>
        <Button variant="light" isLoading>답변 준비중입니다.</Button>
      </div>
    </div>
  )
}

const UserMessage = ({message}: {message: string}) => {
  return (
    <div className="w-full flex flex-row-reverse">
      <div className='ml-5 flex flex-col w-[50%] leading-1.5 p-4 rounded-l-xl rounded-b-xl rounded-es-xl bg-blue-50'>
        <p className={"text-sm font-normal"}>
          {message}
        </p>
      </div>
    </div>
  )
}


const ChatList = ({chatList}: {chatList: Chat[]}) => {

  return (
    <>
      {chatList.map((chat, index)=> {
        switch(chat.type) {
          case "BOT":
            return (<ChatMessage key={index} message={chat.message}/>)
          case "USER":
            return (<UserMessage key={index} message={chat.message} />)
          case "BOT-WAIT":
            return (<BotWaitMessage key={index} message={chat.message}/>)
        }
      })}
    </>
  )
}

export default ChatList;