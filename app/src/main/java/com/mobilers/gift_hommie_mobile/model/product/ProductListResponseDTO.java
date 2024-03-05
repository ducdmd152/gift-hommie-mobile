package com.mobilers.gift_hommie_mobile.model.product;

import com.mobilers.gift_hommie_mobile.model.PageableDTO;

import java.util.List;

public class ProductListResponseDTO {
    private List<Product> content;
    private PageableDTO pageable;

    public List<Product> getContent() {
        return content;
    }

    public void setContent(List<Product> content) {
        this.content = content;
    }

    public PageableDTO getPageable() {
        return pageable;
    }

    public void setPageable(PageableDTO pageable) {
        this.pageable = pageable;
    }
}
