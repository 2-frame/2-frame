import Typo from "../../components/@common/Typo";
import SlideContainer from "../../components/SlideContainer/SlideContainer.tsx";
import ProductCard from "../../components/ProductCard/ProductCard.tsx";
import {useQuery} from "@tanstack/react-query";
import {pMock} from "../../api";
import {ProductCardListSkeleton} from "../../components/ProductCard/ProductCardListSkeleton.tsx";
import {SearchProductsResponse} from "../../api/type.ts";


const RecommendProductViewer = () => {
  const {data, isLoading} = useQuery({
    queryKey: ['get products recommend'],
    queryFn: () => pMock<SearchProductsResponse>(),
    // queryFn: () => getProducts({page: 2, size: 10})
  })

  return (
    <>
      <div className="flex flex-col w-full items-center gap-1">
        <Typo.H2 text="MD 추천" className="text-center"/>
        <Typo.H5 text="24SS에 활용하기 좋은 아이템 모음" className="text-center font-light"/>
      </div>
      <SlideContainer>
        {isLoading ? <ProductCardListSkeleton/> : (
          data?.content.map(product => <ProductCard {...product}/>)
        )}
      </SlideContainer>
    </>
  )
}
export default RecommendProductViewer;