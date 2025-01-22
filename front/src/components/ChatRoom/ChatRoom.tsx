import {Avatar, Button, Card, CardBody, CardFooter, CardHeader, ScrollShadow} from "@heroui/react";
import Input from "../@common/Input";
import {IoMdSend} from "react-icons/io";
import Typo from "../@common/Typo";


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

const ChatRoom = () => {

  return <>
    <Card className={"h-[600px] w-[500px]"}>
      <CardHeader>챗봇</CardHeader>
      <CardBody>
        <ScrollShadow className="flex gap-3 flex-col h-full">
          <ChatMessage/>
          <UserMessage/>
          <ChatMessage/>
          <ChatMessage/>
          <ChatMessage/>
          <ChatMessage/>
          <ChatMessage/>
          <ChatMessage/>
          <ChatMessage/>
          <ChatMessage/>
        <ChatMessage/>
        </ScrollShadow>
      </CardBody>
      <CardFooter className="gap-2">
          <Input.Basic placeholder="채팅을 입력해주세요" className="p-5"/>
        <Button isIconOnly variant="light">
          <IoMdSend size={20}/>
        </Button>
      </CardFooter>
    </Card>
  </>

}
export default ChatRoom;