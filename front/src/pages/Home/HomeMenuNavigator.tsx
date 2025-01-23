import {Tab, Tabs} from "@heroui/react";
import Typo from "../../components/@common/Typo";
import SlideContainer from "../../components/SlideContainer/SlideContainer";
import ProductCard from "../../components/ProductCard/ProductCard";
import {useQuery} from "@tanstack/react-query";
import {ProductCardListSkeleton} from "../../components/ProductCard/ProductCardListSkeleton";
import {pMock} from "../../api";


export const HomeMenuNavigator = () => {
  const {data, isLoading} = useQuery({
    queryKey: ['get products'],
    queryFn: () => pMock()
    // queryFn: () => getProducts({page: 1, size: 10})
  })

  return <>
    <div className="flex flex-col w-full items-center gap-5">
      <Typo.H1 text={"MENU"}/>

      <Tabs key={"light"} aria-label="Tabs variants" variant={"light"}>
        <Tab key="photos" title="신상품"/>
        <Tab key="music" title="특가"/>
        <Tab key="t1j" title="코디북"/>
        <Tab key="t2" title="뷰티"/>
      </Tabs>
    </div>
    <SlideContainer>
      {isLoading ? <ProductCardListSkeleton/> : (
        data?.content.map(product => <ProductCard {...product}/>)
      )}
    </SlideContainer>
  </>
}
