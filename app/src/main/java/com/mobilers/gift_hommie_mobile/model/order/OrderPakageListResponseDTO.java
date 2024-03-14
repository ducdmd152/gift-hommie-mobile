package com.mobilers.gift_hommie_mobile.model.order;

import java.util.List;

public class OrderPakageListResponseDTO {
    private List<OrderPackageDTO> content;

    public List<OrderPackageDTO> getContent() {
        return content;
    }

    public void setContent(List<OrderPackageDTO> content) {
        this.content = content;
    }
}
