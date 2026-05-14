package com.kh.vira_dev.ecommerceapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ShippingAddressSnapshort {

    @Column(name = "full_name" , nullable = false)
    private String fullName;

    @Column(name = "phone" , nullable = false)
    private String phone;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "address_line")
    private String addressLine;

    @Column(name = "shipping_note")
    private String note;


}