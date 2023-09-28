package demo.elasticsearch.product.presentation.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProductSaveDto(
    @field:NotBlank(message = "상품 명은 필수 입니다.")
    val name: String?,

    @field:NotNull(message = "상품 가격은 필수 입니다.")
    @field:Min(0, message = "상품 가격은 0 이상 이어야 합니다.")
    val price: Int?,

    @field:NotBlank(message = "카테고리는 필수 입니다.")
    val category: String?,
)
