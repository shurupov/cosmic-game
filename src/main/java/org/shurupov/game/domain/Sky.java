package org.shurupov.game.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Sky {
    private final StarLayer[] layers;
}
