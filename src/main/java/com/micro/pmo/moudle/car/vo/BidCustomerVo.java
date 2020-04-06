package com.micro.pmo.moudle.car.vo;

import java.math.BigDecimal;

public class BidCustomerVo {

    private String cusNick;

    private String phone;

    private BigDecimal price;



	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
