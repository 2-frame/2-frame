import Typo from "../../components/@common/Typo";
import ProductReviewCard from "../../components/ProductReviewCard/ProductReviewCard.tsx";
import {Link} from "react-router";
import SlideContainer from "../../components/SlideContainer/SlideContainer.tsx";
import {useQuery} from "@tanstack/react-query";
import {ProductCardListSkeleton} from "../../components/ProductCard/ProductCardListSkeleton.tsx";
import {pMock} from "../../api/index.ts";


const ProductReviewViewer = () => {

  const {data, isLoading} = useQuery({
    queryKey: ['all review'],
    // queryFn: () => getAllReviews()
    queryFn: () => pMock()
  })

  return (
    <>
      <div className="flex flex-col w-full gap-1">
        <Typo.H2 text="REVIEW" className="font-semibold"/>
        <Typo.H5 text="구매후기" className="font-light"/>
      </div>

      {/*에러가 너무 많은데..?*/}
      <SlideContainer>
        {isLoading ? <ProductCardListSkeleton/> : data?.content?.map(review => <ProductReviewCard {...review}/>)}
      </SlideContainer>

      <Link to={"/reviews"}>
        <Typo.H6 text="리뷰 모두 보기" className="text-gray-400"/>
      </Link>
    </>
  )
}

export default ProductReviewViewer;