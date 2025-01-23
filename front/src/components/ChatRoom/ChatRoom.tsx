import {Button, Card, CardBody, CardFooter, CardHeader, Listbox, ListboxItem, ScrollShadow} from "@heroui/react";
import Input from "../@common/Input";
import {IoMdSend} from "react-icons/io";
import ChatList from "../ChatList/ChatList.tsx";
import {ChatHistory, useChatRoom} from "./useChatRoom";
import Typo from "../@common/Typo";
import {IoArrowBack} from "react-icons/io5";


export const ChatIcon = (props) => {
  return (
    <svg height="1em" viewBox="0 0 24 24" width="1em" xmlns="http://www.w3.org/2000/svg" {...props}>
      <path
        d="M5 18v3.766l1.515-.909L11.277 18H16c1.103 0 2-.897 2-2V8c0-1.103-.897-2-2-2H4c-1.103 0-2 .897-2 2v8c0 1.103.897 2 2 2h1zM4 8h12v8h-5.277L7 18.234V16H4V8z"
        fill="currentColor"
      />
      <path
        d="M20 2H8c-1.103 0-2 .897-2 2h12c1.103 0 2 .897 2 2v8c1.103 0 2-.897 2-2V4c0-1.103-.897-2-2-2z"
        fill="currentColor"
      />
    </svg>
  );
};


const HistoryList = ({histories, select}: {histories: ChatHistory[], select: (historyId: number) => void}) => {
  return  <Listbox
    aria-label="User Menu"
    className="p-0 divide-y gap-5 divide-default-300/50 overflow-visible rounded-medium"
    emptyContent={"새 채팅을 눌러 시작해주세요."}
    itemClasses={{
      base: "px-3 border-1 gap-4 h-12 data-[hover=true]:bg-default-100/80",
    }}
    onAction={(key) => select(Number(key))}
  >
    {histories.map(history => (
      <ListboxItem
        key={history.id}
        // endContent={</>}
        startContent={
          <div className="bg-success/10 text-success flex items-center rounded-small justify-center w-7 h-7">
            <ChatIcon className="text-lg" />
          </div>
        }
      >
        {history.lastMessage}
      </ListboxItem>
    ))}
  </Listbox>
}

const ChatRoom = () => {

  const {isHistory, backHistory, handleSelectHistory, chatHistories, computedChatList,handleSubmit, text, setText, appendHistories} = useChatRoom();
  return <>
    <Card className={"h-[600px] w-[500px]"}>
      <CardHeader>
        <Button isIconOnly variant="light" onPress={backHistory}>
          <IoArrowBack size={20} />
        </Button>
        <Typo.H6 text={"챗봇"} className="text-small"/>
      </CardHeader>
      <CardBody>
        <ScrollShadow className="flex gap-3 flex-col h-full" offset={0}>
          {isHistory ? <HistoryList histories={chatHistories} select={handleSelectHistory}/> : <ChatList chatList={computedChatList}/>}
        </ScrollShadow>
      </CardBody>
      <CardFooter className="gap-1">
        {!isHistory && (
          <>
          <Input.Basic placeholder="채팅을 입력해주세요" value={text} className="p-5" onValueChange={setText}/>
          <Button isIconOnly variant="light"  onPress={() => handleSubmit()}>
            <IoMdSend size={20}/>
          </Button>
          </>
        )}
        {isHistory && <Button fullWidth variant={"solid"} color="danger" onPress={appendHistories}>새 대화</Button>}
      </CardFooter>
    </Card>
  </>

}
export default ChatRoom;