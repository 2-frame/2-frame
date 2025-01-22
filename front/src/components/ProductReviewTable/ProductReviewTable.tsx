import {Accordion, AccordionItem, Image, Pagination} from "@nextui-org/react";
import {CenterWrapper} from "../@common/CenterWrapper";


const url = 'https://m.toffee.co.kr/file_data/toffee/2024/11/06/f8d7aab42430a368f0d24a3473eb4538.JPG'
const ProductReviewTable = () => {

  return (
    <>
      <Accordion variant="light">
        {new Array(30).fill(0).map((_, index) =>
          <AccordionItem key={index} title="잘받았습니다." subtitle="좋은데요"
                         startContent={<Image radius="none" src={url} height={50}/>}>
            너무 좋은데요
            <Image src={url}/>
          </AccordionItem>
        )}
      </Accordion>

      <CenterWrapper className="w-full">
        <Pagination color="secondary" variant="faded" isCompact showControls initialPage={1} total={10} radius="none"
                    size="lg"/>
      </CenterWrapper>

    </>

  )
}

export default ProductReviewTable;