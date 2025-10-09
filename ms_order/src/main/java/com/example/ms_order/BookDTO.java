package com.example.ms_order;

import java.math.BigDecimal;

// Usaremos Lombok para menos código
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
}
