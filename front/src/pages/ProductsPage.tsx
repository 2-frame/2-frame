import ProductCard from "../components/ProductCard/ProductCard.tsx";
import {Pagination, Skeleton, Spacer} from "@heroui/react";
import Typo from "../components/@common/Typo/index.tsx";
import {CenterWrapper} from "../components/@common/CenterWrapper";
import {useSearchParams} from "react-router";
import {useQuery} from "@tanstack/react-query";
import {pipe} from "effect";
import {useEffect} from "react";
import {ProductCardListSkeleton} from "../components/ProductCard/ProductCardListSkeleton.tsx";
import {SearchProductsResponse} from "../api/type.ts";
import {pMock} from "../api";

const ProductsPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();

  const page = pipe(searchParams.get("page"),
    page => page == null ? 1 : Number(page),
    page => page <= 0 ? 1 : page
  )
  const {data, refetch, isLoading, isRefetching} = useQuery({
    queryKey: ['my interview'],
    queryFn: () => pMock<SearchProductsResponse>(),
    // queryFn: () =>  getProducts({page, size: 20})
  });

  useEffect(() => {
    refetch()
  }, [searchParams]);

  return (
    <>
      <div className="flex flex-col items-center gap-1">
        <Typo.H2 text="전체 상품" className="font-light"/>
      </div>

      <Spacer y={10}/>

      <div className="flex flex-wrap  w-full justify-center gap-1">
        {isLoading || isRefetching ? <ProductCardListSkeleton count={20} isRow={true}/> : data?.content?.map(product => <ProductCard {...product}/>)}
      </div>

      <CenterWrapper className="w-full">
        {isLoading ? <Skeleton className="w-[80%] h-[60px]"/>
          : <Pagination
            color="secondary"
            variant="faded"
            isCompact
            showControls
            initialPage={1} total={data?.page?.totalPages} radius="none" size="lg"
            onChange={page => setSearchParams({page: page.toString()})}
          />}

      </CenterWrapper>
    </>
  )
}

export default ProductsPage;