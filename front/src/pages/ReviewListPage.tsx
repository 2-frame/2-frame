import Typo from "../components/@common/Typo";
import ProductReviewCard from "../components/ProductReviewCard/ProductReviewCard.tsx";
import {Spacer} from "@nextui-org/react";
import ProductReviewTable from "../components/ProductReviewTable/ProductReviewTable.tsx";
import SlideContainer from "../components/SlideContainer/SlideContainer.tsx";

const ReviewListPage = () => {
  return (
    <>
      <div className="flex flex-col items-center gap-1">
        <Typo.H2 text="REVIEW" className="font-light"/>
      </div>

      <SlideContainer>
        <ProductReviewCard/>
        <ProductReviewCard/>
        <ProductReviewCard/>
        <ProductReviewCard/>
        <ProductReviewCard/>
        <ProductReviewCard/>
        <ProductReviewCard/>
      </SlideContainer>

      <Spacer y={10}/>

      <div className="flex flex-col w-full gap-1">
        <Typo.H4 text="모든 리뷰" className="font-light"/>
      </div>

      <ProductReviewTable/>
    </>
  )
}
export default ReviewListPage;