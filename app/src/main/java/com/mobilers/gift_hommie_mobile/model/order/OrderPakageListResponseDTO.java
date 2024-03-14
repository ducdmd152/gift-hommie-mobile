package com.mobilers.gift_hommie_mobile.model.order;

import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;

import java.util.List;

public class OrderPakageListResponseDTO {
    private List<OrderPakageDTO> content;

    public List<OrderPakageDTO> getContent() {
        return content;
    }

    public void setContent(List<OrderPakageDTO> content) {
        this.content = content;
    }
}
