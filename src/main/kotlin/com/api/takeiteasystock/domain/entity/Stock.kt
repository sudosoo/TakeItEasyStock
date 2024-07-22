package com.api.takeiteasystock.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.Getter
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
class Stock(
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    var id: UUID? = null,
    var productId: UUID? = null,
    var productName: String? = null,
    var quantity: Int = 0,
    var description: String? = null,
    ){
}