package com.mobilers.gift_hommie_mobile.model.cart;

import java.util.List;

public class CartListResponseDTO {
    private List<CartDTO> content;

    public List<CartDTO> getContent() {
        return content;
    }

    public void setContent(List<CartDTO> content) {
        this.content = content;
    }
}
