import {Card, CardBody, CardFooter, Image} from "@heroui/react";
import Typo from "../@common/Typo";
import {BiSolidStar} from "react-icons/bi";
import {ProductReview} from "../../api/type.ts";


const url = "https://toffee.co.kr/file_data/toffee/2024/12/10/f51d93545f04bda4619c7513b0acbe88.jpg"

const ProductReviewCard = ({reviewId, productName, rate, title}: ProductReview) => {

  return (
    <Card className="min-w-[220px] max-w-[320px] h-[100%]" shadow="none">
      <CardBody>
        <Image src={url}/>
      </CardBody>
      <CardFooter className="flex flex-col items-start gap-4">
        <Typo.H5 text={productName}/>
        <Typo.H6 text={title} className="font-light text-wrap"/>
        <div className="flex">
          {new Array(rate).fill(0).map(() => <BiSolidStar/>)}
        </div>
      </CardFooter>
    </Card>
  )
}

export default ProductReviewCard;