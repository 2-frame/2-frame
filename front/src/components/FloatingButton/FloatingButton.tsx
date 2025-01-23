import {Button, Tooltip} from "@heroui/react";
import ChatRoom from "../ChatRoom/ChatRoom.tsx";
import {useState} from "react";
import {IoChatbubbleEllipsesOutline} from "react-icons/io5";

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
        <Button color="danger" variant="shadow" className="w-[50px] h-[50px]" onPress={() => setOpen(!isOpen)} isIconOnly>
          {<IoChatbubbleEllipsesOutline size={30}/>}
        </Button>
      </Tooltip>
    </div>
  )
}

export default FloatingButton;