import {Card, CardBody, CardFooter, Image} from "@nextui-org/react";
import {Product} from "../../types/product"
import {Link} from "react-router";

const url = "https://toffee.co.kr/web/product/big/202312/e8a0d7c6ed7ce9a69d53268f05a648e0.jpg";

interface ProductCardProps extends Product {
}

const ProductCard = ({productId, price, name, mainImgURLs}: ProductCardProps) => {
  return <Card className="min-w-[220px] max-w-[300px]" key={productId} shadow="none">
    <CardBody>
      <Link to={`/products/${productId}`}>
        <Image
          src={mainImgURLs[0]}/>
      </Link>
    </CardBody>
    <CardFooter className="flex flex-col">
      <span>{name}</span>
      <span>{price}원</span>
    </CardFooter>
  </Card>
}

export default ProductCard;