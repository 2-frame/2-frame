import Typo from "../components/@common/Typo";
import {Button, Divider, Image, Select, SelectItem, Spacer, Tab, Tabs} from "@heroui/react";
import Flex from "../components/@common/FlexWrapper/Flex.tsx";
import {CiHeart, CiShoppingCart} from "react-icons/ci";
import ProductGuide from "./ProductDetails/ProductGuide.tsx";
import {useNavigate, useParams} from "react-router";
import {useQuery} from "@tanstack/react-query";
import {getProductDetails} from "../api/index.ts";
import ProductReview from "./ProductDetails/ProductReview.tsx";
import {ProductQnA} from "./ProductDetails/ProductQnA.tsx";


const description = `- 털 빠짐을 최소화한 논 퀼팅 패딩
- 트렌디한 세미 크롭 기장감
- 다양한 연출이 가능한 밑단 스트링
- 히든 손목 밴딩
- 포켓 보온 안감 적용`;

const ProductDetailsPage = () => {
  const navigator = useNavigate();
  const params = useParams();

  const id = params["id"]

  if (id === undefined) {
    navigator("/");
    return;
  }

  const {data} = useQuery({
    queryKey: ['product details' + id],
    queryFn: () => getProductDetails({id})
  })

  if (data === undefined) {
    return <div></div>
  }

  const computedColorList = data.options.map(product => product.color);
  const computedSizeList = data.options.map(product => product.size);


  return (
    <div className="flex flex-col w-full gap-10">
      <Image className="" src={data.mainImgURLs[0]}/>
      <div className="p-3">
        <Typo.H3 text={data.name}/>
        <Spacer y={6}/>
        <Typo.H6 text={data.description} className="whitespace-pre-line text-gray-300"/>
        <Spacer y={6}/>
        <Typo.H5 text={data.price} className="font-bold"/>
        <Spacer y={6}/>
        <Divider/>
        <div className="flex justify-between items-center gap-3 p-2">
          <Typo.H6 text="색상"/>
          <Select radius="none" className="w-[80%]">
            {computedColorList.map(color => <SelectItem>{color}</SelectItem>)}
          </Select>
        </div>

        <Spacer y={1}/>
        <Divider/>
        <Spacer y={1}/>

        <div className="flex justify-between items-center gap-3 p-2">
          <Typo.H6 text="사이즈"/>
          <Select radius="none" className="w-[80%]">
            {computedSizeList.map(size => <SelectItem>{size}</SelectItem>)}
          </Select>
        </div>
        <Spacer y={3}/>
        <Divider/>
        <Spacer y={3}/>
        <div className="flex gap-3 h-[70px]">
          <Button isIconOnly={true} variant="bordered" color="primary" className="h-[80px] w-[80px]">
            <CiHeart size={30}/>
          </Button>
          <Button isIconOnly={true} variant="bordered" color="primary" className="h-[80px] w-[80px]">
            <CiShoppingCart size={30}/>
          </Button>
          <Button className="flex-auto w-[200px] h-[80px] bg-black text-white">구매하기</Button>
        </div>
      </div>

      <div className="flex flex-col gap-1">
        <Tabs
          classNames={{
            tabList: "gap-6 w-full relative rounded-none p-0 border-b border-divider",
            cursor: "w-full bg-[#22d3ee]",
            tab: "px-0 h-12",
            tabContent: "group-data-[selected=true]:text-[#06b6d4]",
          }}
          color="primary"
          variant="underlined"
          fullWidth={true} size="lg">
          <Tab title="상품 상세">
            <Typo.H1 className="font-bold" text="DETAIL"/>
            {data?.detailURLs.map(img => <Image radius="none" src={img.replace("toffe", "toffee")}/>)}
          </Tab>
          <Tab title="상품 리뷰" className="p-2">

            <ProductReview productId={Number(id)}/>
          </Tab>
          <Tab title="상품 문의" className="p-2">
            <Flex.Between className="gap-3">
              <Button fullWidth={true} radius="none" className="h-[50px] bg-white" variant="bordered">모두 보기</Button>
              <Button fullWidth={true} radius="none" className="h-[50px] bg-black text-white" variant="bordered">문의 작성</Button>
            </Flex.Between>

            <ProductQnA productId={Number(id)}/>
          </Tab>
        </Tabs>
        <ProductGuide/>
      </div>
    </div>
  )
}

export default ProductDetailsPage;