import {useQuery} from "@tanstack/react-query";
import {getProductsReview} from "../../api/index.ts";
import {useState} from "react";
import {Accordion, AccordionItem, Button, Pagination} from "@heroui/react";
import {CenterWrapper} from "../../components/@common/CenterWrapper";
import Flex from "../../components/@common/FlexWrapper/Flex.tsx";

interface ProductReviewProps {
  productId: number
}

const ProductReview = ({productId}: ProductReviewProps) => {

  const [page, setPage] = useState(1);

  const {data} = useQuery({
    queryFn: () => getProductsReview({id: productId, page, size: 10}),
    queryKey: ['productReview', page, productId]
  })


  return <>
    <Flex.Between className="gap-3">
      <Button fullWidth={true} radius="none" className="h-[50px] bg-white" variant="bordered">모두 보기</Button>
      <Button fullWidth={true} radius="none" className="h-[50px] bg-black text-white" variant="bordered">후기 작성</Button>
    </Flex.Between>
    <Accordion variant="light">
      {data?.content?.map(review => <AccordionItem key={review.reviewId} title={review.title} subtitle={review.name}/>)}
    </Accordion>

    <CenterWrapper className="w-full">
      <Pagination color="secondary" variant="faded" isCompact showControls initialPage={1} total={data?.page?.totalPages} radius="none"
                  size="lg"/>
    </CenterWrapper>
  </>

}

export default ProductReview;