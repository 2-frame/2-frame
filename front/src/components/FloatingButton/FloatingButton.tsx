import {Button, Tooltip} from "@heroui/react";
import ChatRoom from "../ChatRoom/ChatRoom.tsx";
import {useState} from "react";

const FloatingButton = () => {
  const [isOpen, setOpen] = useState(false);

  return (
    <div className="fixed bottom-[20px] right-[20px]">
      <Tooltip
        isOpen={isOpen}
        content={<ChatRoom/>}
        classNames={{
          content: ["p-0 shadow-xl", "text-black bg-none"],
        }}
      >
        <Button onPress={() => setOpen(!isOpen)}>AI에게 물어보기</Button>
      </Tooltip>
    </div>
  )
}

export default FloatingButton;