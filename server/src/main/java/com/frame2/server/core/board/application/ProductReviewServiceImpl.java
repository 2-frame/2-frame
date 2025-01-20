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
    public ProductReview productReviewCreate(ProductReviewRequest request, Long memberId) {
        Member member = memberRepository.findOne(memberId);
        SaleProduct saleProduct = saleProductRepository.findOne(request.saleProductId());
        OrderDetail orderDetail = orderDetailRepository.findOne(request.orderDetailId());

        ProductReview productReview = request.toEntity(member, saleProduct, orderDetail);
        return productReviewRepository.save(productReview);
    }

    // 수정
    @Override
    public ProductReview productReviewModify(ProductReviewModifyRequest request) {
        ProductReview productReview = request.toEntity();
        ProductReview findProductReview = productReviewRepository.findOne(request.id());
        return findProductReview.updateProductReview(productReview);
    }

    // 삭제
    @Override
    public void remove(Long id) {
        productReviewRepository.deleteById(id);
    }

    // 단건 조회
    @Override
    @Transactional(readOnly = true)
    public ProductReviewResponse getProductReview(Long id) {
        ProductReview productReview = productReviewRepository.findOne(id);
        return ProductReviewResponse.from(productReview);
    }

    // 전체 조회
    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewResponse> getProductReviewList() {
        List<ProductReview> productReviewList = productReviewRepository.findAll();
        return productReviewList.stream()
                .map(ProductReviewResponse::from)
                .toList();
    }
}
