package com.ChinaMaal.ECommerce.ResDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResDto {
    private String name;

    List<CardDto> cardDtos;
}
