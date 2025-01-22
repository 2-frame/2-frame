package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.board.infrastructure.ProductReviewRepository;
import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.order.infrastructure.OrderDetailRepository;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.product.infrastructure.SaleProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final SaleProductRepository saleProductRepository;
    private final MemberRepository memberRepository;
    private final OrderDetailRepository orderDetailRepository;

    // 생성
    @Override
    public void productReviewCreate(ProductReviewRequest request, Long memberId, Long saleProductId) {
        Member member = memberRepository.findOne(memberId);
        SaleProduct saleProduct = saleProductRepository.findOne(saleProductId);
        OrderDetail orderDetail = orderDetailRepository.findOne(request.orderDetailId());

        ProductReview productReview = request.toEntity(member, saleProduct, orderDetail);
        productReviewRepository.save(productReview);
    }

    // 수정
    @Override
    public void productReviewModify(ProductReviewModifyRequest request, Long productReviewId) {
        productReviewRepository.findOne(productReviewId).updateProductReview(request.toEntity());
    }

    // 삭제
    @Override
    public void remove(Long id) {
        productReviewRepository.findOne(id).delete();
    }

    // 단건 조회
    @Override
    @Transactional(readOnly = true)
    public ProductReviewResponse getProductReview(Long productReviewId) {
        ProductReview productReview = productReviewRepository.findOne(productReviewId);
        return ProductReviewResponse.from(productReview);
    }

    // 한 상품에 대한 전체 리뷰 조회
    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewResponse> getProductReviewList(Long saleProductId) {
        List<ProductReview> productReviewList = productReviewRepository.findAllBySaleProductId(saleProductId);
        return productReviewList.stream()
                .map(ProductReviewResponse::from)
                .toList();
    }
}
