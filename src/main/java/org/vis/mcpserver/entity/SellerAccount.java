package org.vis.mcpserver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "seller_account")
public class SellerAccount {
    @Id
    @Column
    private Long id;

    private String name;

    private String owner;

    private Boolean isTest;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Boolean getTest() {
        return isTest;
    }

    public String getType() {
        return type;
    }

    public Integer getStatus() {
        return status;
    }

    private String type;
    private Integer status;
}
