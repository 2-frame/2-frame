import {Button, Tooltip} from "@heroui/react";
import ChatRoom from "../ChatRoom/ChatRoom.tsx";

const FloatingButton = () => {
  return (
    <div className="fixed bottom-[20px] right-[20px]">
      <Tooltip
        isOpen={true}
        content={<ChatRoom/>}
        classNames={{
          content: ["p-0 shadow-xl", "text-black bg-none"],
        }}
      >
        <Button>AI에게 물어보기</Button>
      </Tooltip>
    </div>
  )
}

export default FloatingButton;